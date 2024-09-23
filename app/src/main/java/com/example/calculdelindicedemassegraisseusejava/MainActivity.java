package com.example.calculdelindicedemassegraisseusejava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtTaille, txtPoids, txtAge;
    RadioButton rdbHomme, rdbFemme;
    Button btnCalculImg;
    TextView ib1Resultat, ib1Interpretation;
    RadioGroup genderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtTaille = findViewById(R.id.txtTaille);
        txtPoids = findViewById(R.id.txtPoids);
        txtAge = findViewById(R.id.txtAge);
        rdbHomme = findViewById(R.id.rdbHomme);
        rdbFemme = findViewById(R.id.rdbFemme);
        btnCalculImg = findViewById(R.id.btnCalculImg);
        ib1Resultat = findViewById(R.id.ib1Resultat);
        ib1Interpretation = findViewById(R.id.ib1Interpretation);
        genderGroup = findViewById(R.id.genderGroup);


        btnCalculImg.setOnClickListener(view -> {

            String tailleStr = txtTaille.getText().toString();
            String poidsStr = txtPoids.getText().toString();
            String ageStr = txtAge.getText().toString();

            if (!tailleStr.isEmpty() && !poidsStr.isEmpty() && !ageStr.isEmpty()) {

                float taille = Float.parseFloat(tailleStr) / 100;
                float poids = Float.parseFloat(poidsStr);
                int ageN = Integer.parseInt(ageStr);


                float IMC = poids / (taille * taille);


                int selectedGenderId = genderGroup.getCheckedRadioButtonId();
                if (selectedGenderId == R.id.rdbHomme || selectedGenderId == R.id.rdbFemme) {
                    float IMG;
                    if (ageN >= 16) {

                        if (selectedGenderId == R.id.rdbHomme) {
                            //hommes
                            IMG = (float) ((1.20 * IMC) + (0.23 * ageN) - (10.8 * 1) - 5.4);
                            if (IMG < 15) {
                                ib1Interpretation.setText("Trop maigre");
                            } else if (IMG >= 15 && IMG < 20) {
                                ib1Interpretation.setText("Normal");
                            } else {
                                ib1Interpretation.setText("Surpoids/Obésité");
                            }
                        } else {
                            // femmes
                            IMG = (float) ((1.20 * IMC) + (0.23 * ageN) - (10.8 * 0) - 5.4);
                            if (IMG < 25) {
                                ib1Interpretation.setText("Trop maigre");
                            } else if (IMG >= 25 && IMG < 30) {
                                ib1Interpretation.setText("Normal");
                            } else {
                                ib1Interpretation.setText("Surpoids/Obésité");
                            }
                        }
                    } else {

                        if (selectedGenderId == R.id.rdbHomme) {
                            //garçons
                            IMG = (float) ((1.51 * IMC) + (0.70 * ageN) - (3.6 * 1) + 1.4);
                            if (IMG < 15) {
                                ib1Interpretation.setText("Trop maigre");
                            } else if (IMG >= 15 && IMG < 20) {
                                ib1Interpretation.setText("Normal");
                            } else {
                                ib1Interpretation.setText("Surpoids/Obésité");
                            }
                        } else {
                            //filles
                            IMG = (float) ((1.51 * IMC) + (0.70 * ageN) - (3.6 * 0) + 1.4);
                            if (IMG < 25) {
                                ib1Interpretation.setText("Trop maigre");
                            } else if (IMG >= 25 && IMG < 30) {
                                ib1Interpretation.setText("Normal");
                            } else {
                                ib1Interpretation.setText("Surpoids/Obésité");
                            }
                        }
                    }
                    ib1Resultat.setText(IMG+"%");



                }
            } else {
                Toast.makeText(this, "Veuillez entrer votre taille, poids et âge", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

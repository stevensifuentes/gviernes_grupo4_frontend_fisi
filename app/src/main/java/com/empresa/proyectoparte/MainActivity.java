package com.empresa.proyectoparte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Button irventana2;
    private Spinner spin1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spin1=(Spinner)findViewById(R.id.spinCategoria);
        String [] opc = {"Alumno","Profesor","Trabajador","General"};
        ArrayAdapter <String> adap = new ArrayAdapter<String>(this, R.layout.spinner_atributo,opc);
        spin1.setAdapter(adap);
    }
    public void siguiente(View view){
        Intent sig = new Intent(this,MainActivity2.class);
        startActivity(sig);
    }
}
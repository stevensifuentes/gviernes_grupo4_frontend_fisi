package com.empresa.proyectoparte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity {

    private Spinner spin1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        spin1=(Spinner)findViewById(R.id.spinnerFecha);
        String [] opc = {"Enero, 2022","Febrero, 2022","Marzo, 2022","Abril, 2022","Mayo, 2022","Junio, 2022",
                "Julio, 2022","Agosto, 2022","Septiembre, 2022","Octubre, 2022","Noviembre, 2022","Diciembre, 2022",
                "Enero, 2023","Febrero, 2023","Marzo, 2023","Abril, 2023","Mayo, 2023","Junio, 2023","Julio, 2023",
                "Agosto, 2023","Septiembre, 2023","Octubre, 2023","Noviembre, 2023","Diciembre, 2023"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, R.layout.spinner_atributo,opc);
        spin1.setAdapter(adap);
    }
}
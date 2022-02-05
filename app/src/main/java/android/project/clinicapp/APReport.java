package android.project.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class APReport extends AppCompatActivity {

    ImageButton back;
    String especialidad, fecha, hora;
    TextView tvEspecialidad, tvFecha, tvHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apreport);

        // Recuperar valores de la actividad de Pasarela de Pago
        especialidad = getIntent().getStringExtra("es");
        fecha = getIntent().getStringExtra("fe");
        hora = getIntent().getStringExtra("ho");

        tvEspecialidad = (TextView) findViewById(R.id.especialidadAP);
        tvFecha = (TextView) findViewById(R.id.fechaAP);
        tvHora = (TextView) findViewById(R.id.horaAP);

        tvEspecialidad.setText(especialidad);
        tvFecha.setText(fecha);
        tvHora.setText(hora);

        back = findViewById(R.id.btnBackAP);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), ScheduleAppointment.class);
                startActivity(inte);
            }
        });
    }
}
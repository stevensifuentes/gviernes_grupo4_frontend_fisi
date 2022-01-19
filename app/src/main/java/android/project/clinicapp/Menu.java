package android.project.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Menu extends AppCompatActivity {

    ImageButton btn_prog, btn_citas_prog, btn_recetas, btn_historial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_prog = findViewById(R.id.btnProgramarCita);
        btn_prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), ScheduleAppointment.class);
                startActivity(inte);
            }
        });

        btn_citas_prog = findViewById(R.id.btnCitasProgramadas);
        btn_citas_prog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Appointments.class);
                startActivity(inte);
            }
        });

        btn_recetas = findViewById(R.id.btnRecetasMedicas);
        btn_recetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Recipes.class);
                startActivity(inte);
            }
        });

        btn_historial = findViewById(R.id.btnHistorialMedico);
        btn_historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), RequestMH.class);
                startActivity(inte);
            }
        });
    }
}
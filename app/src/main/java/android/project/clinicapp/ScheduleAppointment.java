package android.project.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.project.clinicapp.API.ClinicAPI;
import android.project.clinicapp.models.Cita;
import android.project.clinicapp.models.CitaProgramada;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScheduleAppointment extends AppCompatActivity {

    private Spinner spin1;
    private ImageView back;
    //private ChipGroup especialidadChip, fechaChip, horaChip;
    //private Button btnRegistrar;

    private static final String TAG = "CITA PROGRAMADA";
    public static final String BASE_URL = "https://reqres.in/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        spin1 = (Spinner)findViewById(R.id.spinnerFecha);
        String [] opc = {"Enero, 2022","Febrero, 2022","Marzo, 2022","Abril, 2022","Mayo, 2022","Junio, 2022",
                "Julio, 2022","Agosto, 2022","Septiembre, 2022","Octubre, 2022","Noviembre, 2022","Diciembre, 2022",
                "Enero, 2023","Febrero, 2023","Marzo, 2023","Abril, 2023","Mayo, 2023","Junio, 2023","Julio, 2023",
                "Agosto, 2023","Septiembre, 2023","Octubre, 2023","Noviembre, 2023","Diciembre, 2023"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, R.layout.spinner_atributo,opc);
        spin1.setAdapter(adap);

        // Capturar los datos


        // Agregamos evento de click para guardar la cita
        /*btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Logica de validación de campos seleccionados


                // Llamamos al método para enviar los datos a la API
                sendAppointmentToAPI("Médicina", "2022-01-30", "18:00:00", 3);
            }
        });*/

        back = (ImageView) findViewById(R.id.imgBackProg);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Menu.class);
                startActivity(inte);
            }
        });
    }

    /*private void sendAppointmentToAPI(String especialidad, String fecha, String hora, Integer historialId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClinicAPI service = retrofit.create(ClinicAPI.class);

        // Instanciamos la cita
        CitaProgramada cita = new CitaProgramada(especialidad, fecha, hora, historialId);

        Call<CitaProgramada> citaProgramadaCall = service.saveAppointments(cita);

        citaProgramadaCall.enqueue(new Callback<CitaProgramada>() {
            @Override
            public void onResponse(Call<CitaProgramada> call, Response<CitaProgramada> response) {
                Toast.makeText(ScheduleAppointment.this, "Su cita ha sido programada", Toast.LENGTH_SHORT).show();

                CitaProgramada responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code()
                        + "\nEspecialidad : " + responseFromAPI.getEspecialidad()
                        + "\n" + "Fecha : " + responseFromAPI.getFecha()
                        + "\n" + "Hora : " + responseFromAPI.getHora()
                        + "\n" + "Historial id : " + responseFromAPI.getHistorial_id();

                Log.i(TAG, responseString);
            }

            @Override
            public void onFailure(Call<CitaProgramada> call, Throwable t) {
                Log.e(TAG, " Error, no se pudo guardar la cita: "+ t.getMessage());
            }
        });
    }*/
}
package android.project.clinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.project.clinicapp.API.ClinicAPI;
import android.project.clinicapp.models.Solicitud;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestMH extends AppCompatActivity {

    private ImageButton btn_back_request;
    private EditText edtnombre, edtapellidos, edtdni, edtcorreo;
    private Button btnEnviar;
    private ProgressBar loadingPB;

    private static final String TAG = "SOLICITUD DE HISTORIAL";
    public static final String BASE_URL = "https://clinicauniversitaria.herokuapp.com/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_mh);

        // Capturamos los datos
        edtnombre = (EditText) findViewById(R.id.txtNombre);
        edtapellidos = (EditText) findViewById(R.id.txtApellidos);
        edtdni = (EditText) findViewById(R.id.txtdni);
        edtcorreo = (EditText) findViewById(R.id.txtCorreo);
        btnEnviar = (Button) findViewById(R.id.btnEnviarSolicitud);
        loadingPB = (ProgressBar) findViewById(R.id.idLoadingPB);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtnombre.getText().toString().isEmpty() && edtapellidos.getText().toString().isEmpty()
                        && edtdni.getText().toString().isEmpty() && edtcorreo.getText().toString().isEmpty()) {
                    Toast.makeText(RequestMH.this, "Ingrese los campos, por favor.", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendRequestHMToAPI(edtnombre.getText().toString(), edtapellidos.getText().toString(), edtdni.getText().toString(), edtcorreo.getText().toString());
            }
        });

        btn_back_request = findViewById(R.id.btnBackRequest);
        btn_back_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Menu.class);
                startActivity(inte);
            }
        });
    }

    private void sendRequestHMToAPI(String nombre, String apellidos, String dni, String correo_electronico) {
        loadingPB.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClinicAPI service = retrofit.create(ClinicAPI.class);

        Solicitud solicitud = new Solicitud(nombre, apellidos, dni, correo_electronico);
        Call<Solicitud> solicitudCall = service.sendRequestMH(solicitud);

        solicitudCall.enqueue(new Callback<Solicitud>() {
            @Override
            public void onResponse(Call<Solicitud> call, Response<Solicitud> response) {
                Toast.makeText(RequestMH.this, "¡Solicitud enviado con éxito!", Toast.LENGTH_SHORT).show();

                loadingPB.setVisibility(View.GONE);
                edtnombre.setText("");
                edtapellidos.setText("");
                edtdni.setText("");
                edtcorreo.setText("");

                Solicitud responseFromAPI = response.body();
                String responseString = "Response Code : " + response.code()
                        + "\nNombre : " + responseFromAPI.getNombre()
                        + "\n" + "Apellidos : " + responseFromAPI.getApellidos()
                        + "\n" + "DNI : " + responseFromAPI.getDni()
                        + "\n" + "Correo : " + responseFromAPI.getCorreo_electronico();

                Log.i(TAG, responseString);
            }

            @Override
            public void onFailure(Call<Solicitud> call, Throwable t) {
                Log.e(TAG, " Error, no se pudo enviar la solicitud.: "+ t.getMessage());
            }
        });
    }
}
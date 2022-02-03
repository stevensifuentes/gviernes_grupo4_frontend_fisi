 package android.project.clinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.project.clinicapp.API.ClinicAPI;
import android.project.clinicapp.models.Cita;
import android.project.clinicapp.models.CitaResultado;
import android.project.clinicapp.models.Rick;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class Appointments extends AppCompatActivity {

    ImageButton back;

    private static final String TAG = "CLINICA";
    private Retrofit retrofit;
     //public static final String BASE_URL = "https://pokeapi.co/api/v2/";
    //public static final String BASE_URL = "https://rickandmortyapi.com/api/";
    public static final String BASE_URL = "https://clinicauniversitaria.herokuapp.com/api/";

    private RecyclerView recyclerView;
    private ListaAdapter listaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaAdapter = new ListaAdapter(this);
        recyclerView.setAdapter(listaAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();
        solicitarData();

        back = findViewById(R.id.btnBackAp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(v.getContext(), Menu.class);
                startActivity(inte);
            }
        });
    }

    public void solicitarData() {
        ClinicAPI service = retrofit.create(ClinicAPI.class);
        Call<CitaResultado> citaResultadoCall = service.findAppointments();


        citaResultadoCall.enqueue(new Callback<CitaResultado>() {
            @Override
            public void onResponse(Call<CitaResultado> call, Response<CitaResultado> response) {
                if (response.isSuccessful()){
                    CitaResultado Respuesta = response.body();
                    // Se pasa todos los resultados a la lista de Ricks
                    ArrayList<Cita> listaCitas = Respuesta.getResults();

                    listaAdapter.adicionarListaPokemon(listaCitas);

                    for (int i=0; i<listaCitas.size(); i++) {
                        Cita p = listaCitas.get(i);
                        Log.i(TAG, "Especialidad: "+ p.getEspecialidad());
                        Log.i(TAG, "Hora: "+ p.getHora());
                        Log.i(TAG, "Fecha: "+ p.getFecha());
                        //Log.i(TAG, "Disponible: "+ p.getDisponible());
                    }
                }else{
                    Log.e(TAG, " onResponse: "+response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<CitaResultado> call, Throwable t) {
                Log.e(TAG, " onFailure: "+ t.getMessage());
            }
        });
    }
}
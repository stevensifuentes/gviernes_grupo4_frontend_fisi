package android.project.clinicapp.API;

import android.project.clinicapp.models.CitaProgramada;
import android.project.clinicapp.models.CitaResultado;
import android.project.clinicapp.models.Solicitud;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClinicAPI {

    //@GET("citas/listUser")
    @GET("character")
    Call<CitaResultado> findAppointments();

    @POST("citas/save")
    Call<CitaProgramada> saveAppointments(@Body CitaProgramada cita);

    @POST("api/historial/enviar")
    Call<Solicitud> sendRequestMH(@Body Solicitud solicitud);

    //@GET("/citas/list/{id}")
}


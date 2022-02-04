package android.project.clinicapp.API;

import android.project.clinicapp.models.CitaProgramada;
import android.project.clinicapp.models.CitaResultado;
import android.project.clinicapp.models.FechaResultado;
import android.project.clinicapp.models.HorasResultado;
import android.project.clinicapp.models.Solicitud;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClinicAPI {

    @GET("citas/listUser/1")
    //@GET("character")
    Call<CitaResultado> findAppointments();

    @POST("citas/save")
    Call<CitaProgramada> saveAppointments(@Body CitaProgramada cita);

    @POST("historial/enviar")
    Call<Solicitud> sendRequestMH(@Body Solicitud solicitud);



    @GET("citas/filterEspecialidad/{especialidad}")
    Call<FechaResultado> filterDate(@Path(value = "especialidad", encoded = true) String especialidad);

    @GET("citas/filterFecha/{especialidad}/{fecha}")
    Call<HorasResultado> filterTimes(@Path(value = "especialidad", encoded = true) String especialidad, @Path(value = "fecha", encoded = true) String fecha);

    /*@GET("citas/filterFecha")
    Call<HoraResultado> filterTime();*/



}


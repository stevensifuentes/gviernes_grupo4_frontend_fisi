package android.project.clinicapp.API;

import android.project.clinicapp.models.CitaResultado;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClinicAPI {

    @GET("citas/listUser")
    Call<CitaResultado> findAppointments();

    @FormUrlEncoded
    @POST("citas/save")
    Call<CitaResultado> saveAppointments();
}

package android.project.clinicapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CitaResultado {
    @SerializedName("results")
    @Expose
    private ArrayList<Cita> results;

    public ArrayList<Cita> getResults() {
        return results;
    }

}

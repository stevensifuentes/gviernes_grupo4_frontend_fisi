package android.project.clinicapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HorasResultado {
    @SerializedName("horas")
    @Expose
    private ArrayList<Hora> results;

    public ArrayList<Hora> getResults() {
        return results;
    }
}

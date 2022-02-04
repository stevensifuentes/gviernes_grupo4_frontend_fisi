package android.project.clinicapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FechaResultado {
    @SerializedName("fechas")
    @Expose
    private ArrayList<Fecha> results;

    public ArrayList<Fecha> getResults() {
        return results;
    }
}

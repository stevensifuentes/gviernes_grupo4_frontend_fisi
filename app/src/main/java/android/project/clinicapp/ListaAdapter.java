package android.project.clinicapp;

import android.content.Context;
import android.project.clinicapp.models.Cita;
import android.project.clinicapp.models.Rick;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder>{
    private ArrayList<Rick> dataset;
    private Context context;

    public ListaAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Rick p = dataset.get(position);
        Glide.with(context)
                .load(p.getImage())
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.citaImageView);
        holder.especialidadTextView.setText(p.getName());
        holder.horaTextView.setText(p.getStatus());
        holder.fechaTextView.setText(p.getSpecies());
        //holder.disponibleTextView.setText(p.getDisponible());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Rick> listaElement) {
        dataset.addAll(listaElement);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView especialidadTextView;
        private TextView horaTextView;
        private TextView fechaTextView;
        private TextView disponibleTextView;
        private ImageView citaImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            especialidadTextView = (TextView) itemView.findViewById(R.id.especialidadTextView);
            horaTextView = (TextView) itemView.findViewById(R.id.horaTextView);
            fechaTextView = (TextView) itemView.findViewById(R.id.fechaTextView);
            disponibleTextView = (TextView) itemView.findViewById(R.id.disponibleTextView);
            citaImageView = (ImageView) itemView.findViewById(R.id.citaImageView);
        }
    }
}

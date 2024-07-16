package com.sistemaelite.portatilplus_2.network;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sistemaelite.portatilplus_2.R;
import java.util.List;

public class AccesorioAdapter extends RecyclerView.Adapter<AccesorioAdapter.AccesorioViewHolder> {

    private List<Accesorio> accesorios;

    public AccesorioAdapter(List<Accesorio> accesorios) {
        this.accesorios = accesorios;
    }

    @NonNull
    @Override
    public AccesorioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accesorio, parent, false);
        return new AccesorioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccesorioViewHolder holder, int position) {
        Accesorio accesorio = accesorios.get(position);
        holder.idAccesorioTextView.setText(String.valueOf(accesorio.getIdAccesorio()));
        holder.numeroAccesorioTextView.setText(String.valueOf(accesorio.getNumeroAccesorio()));
        holder.nombreAccesorioTextView.setText(accesorio.getNombreAccesorio());
        holder.estadoTextView.setText(accesorio.getEstado());
    }

    @Override
    public int getItemCount() {
        return accesorios.size();
    }

    public static class AccesorioViewHolder extends RecyclerView.ViewHolder {
        TextView idAccesorioTextView, numeroAccesorioTextView, nombreAccesorioTextView, estadoTextView;

        public AccesorioViewHolder(@NonNull View itemView) {
            super(itemView);
            idAccesorioTextView = itemView.findViewById(R.id.id_accesorio);
            numeroAccesorioTextView = itemView.findViewById(R.id.numero_accesorio);
            nombreAccesorioTextView = itemView.findViewById(R.id.nombre_accesorio);
            estadoTextView = itemView.findViewById(R.id.estado);
        }
    }
}

package com.sistemaelite.portatilplus_2.network;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sistemaelite.portatilplus_2.R;

import java.util.List;

public class ComputerAdapter extends RecyclerView.Adapter<ComputerAdapter.ComputerViewHolder> {
    private List<Computer> computers;

    public ComputerAdapter(List<Computer> computers) {
        this.computers = computers;
    }

    @NonNull
    @Override
    public ComputerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_computer, parent, false);
        return new ComputerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ComputerViewHolder holder, int position) {
        Computer computer = computers.get(position);
        holder.textViewID.setText("ID: " + computer.getIdcomputador());
        holder.textViewMarca.setText("Marca: " + computer.getMarca());
        holder.textViewModel.setText("Modelo: " + computer.getModelo());
        holder.textViewEstado.setText("Estado: " + computer.getEstado());
        holder.textViewArea.setText("Área: " + computer.getArea());
    }

    @Override
    public int getItemCount() {
        return computers.size();
    }

    static class ComputerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewComputer;
        TextView textViewMarca, textViewModel, textViewEstado, textViewArea, textViewID;

        ComputerViewHolder(View itemView) {
            super(itemView);
            imageViewComputer = itemView.findViewById(R.id.imageViewComputer);
            textViewMarca = itemView.findViewById(R.id.textViewMarca);
            textViewModel = itemView.findViewById(R.id.textViewModel);
            textViewEstado = itemView.findViewById(R.id.textViewEstado);
            textViewArea = itemView.findViewById(R.id.textViewArea);
            textViewID = itemView.findViewById(R.id.textViewID);
        }
    }
}

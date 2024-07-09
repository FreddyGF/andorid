package com.sistemaelite.portatilplus_2.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sistemaelite.portatilplus_2.R;
import com.sistemaelite.portatilplus_2.network.Computer;
import com.sistemaelite.portatilplus_2.network.ComputerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ReservaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComputerAdapter adapter;
    private List<Computer> computers = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserva, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewComputers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ComputerAdapter(computers);
        recyclerView.setAdapter(adapter);

        fetchComputers();

        return view;
    }

    private void fetchComputers() {
        String url = "https://apiportatilplus.onrender.com/admin/computador";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray bodyArray = response.getJSONArray("body");
                        for (int i = 0; i < bodyArray.length(); i++) {
                            JSONObject computerObject = bodyArray.getJSONObject(i);
                            Computer computer = new Computer();
                            computer.setIdcomputador(computerObject.getInt("idcomputador"));
                            computer.setMarca(computerObject.getString("marca"));
                            computer.setModelo(computerObject.getString("modelo"));
                            computer.setEstado(computerObject.getString("estado"));
                            computer.setArea(computerObject.getString("area"));
                            computers.add(computer);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    // Manejar el error
                    Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                });

        // Asumiendo que tienes una instancia de RequestQueue llamada requestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(jsonObjectRequest);
    }
}
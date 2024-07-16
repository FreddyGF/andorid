package com.sistemaelite.portatilplus_2.Fragment;

import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sistemaelite.portatilplus_2.R;
import com.sistemaelite.portatilplus_2.network.Accesorio;
import com.sistemaelite.portatilplus_2.network.AccesorioAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class AccesorioFragment extends Fragment {

    private RecyclerView recyclerView;
    private AccesorioAdapter adapter;
    private List<Accesorio> accesorios = new ArrayList<>();

    private static final String TAG = AccesorioFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accesorio, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAccesorio);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AccesorioAdapter(accesorios);
        recyclerView.setAdapter(adapter);

        fetchAccesorios();

        return view;
    }

    private void fetchAccesorios() {
        String url = "https://portatilplusapi.onrender.com/admin/accesorio";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray bodyArray = response.getJSONArray("body");
                            JSONArray accesoriosArray = bodyArray.getJSONArray(0); // Obtén el primer elemento del array body
                            for (int i = 0; i < accesoriosArray.length(); i++) {
                                JSONObject accesorioObject = accesoriosArray.getJSONObject(i);
                                Accesorio accesorio = new Accesorio();
                                accesorio.setIdAccesorio(accesorioObject.getInt("id_accesorio"));
                                accesorio.setNumeroAccesorio(accesorioObject.getInt("numero_accesorio"));
                                accesorio.setNombreAccesorio(accesorioObject.getString("nombre_accesorio"));
                                accesorio.setEstado(accesorioObject.getString("estado"));
                                accesorios.add(accesorio);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error fetching data: " + error.getMessage());
                        Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(jsonObjectRequest);
    }
}

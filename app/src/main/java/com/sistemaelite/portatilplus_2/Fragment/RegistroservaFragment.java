package com.sistemaelite.portatilplus_2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sistemaelite.portatilplus_2.R;
import com.sistemaelite.portatilplus_2.network.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroservaFragment extends Fragment {

    private EditText editTextNombre, editTextNumber, editTextNumber2, editTextTelefono;
    private Button btnConfirmar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registroserva, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextNumber = view.findViewById(R.id.editTextNumber);
        editTextNumber2 = view.findViewById(R.id.editTextNumber2);
        editTextTelefono = view.findViewById(R.id.editTextTelefono);
        btnConfirmar = view.findViewById(R.id.btn_confirese);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeReservation();
            }
        });
    }

    private void makeReservation() {
        String nombre = editTextNombre.getText().toString().trim();
        String idAccesorio = editTextNumber.getText().toString().trim();
        String idComputador = editTextNumber2.getText().toString().trim();
        String fecha = editTextTelefono.getText().toString().trim();

        if (nombre.isEmpty() || idComputador.isEmpty() || fecha.isEmpty()) {
            Toast.makeText(getActivity(), "Todos los campos obligatorios deben ser llenados", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://portatilplusapi.onrender.com/user/reserva";

        JSONObject postData = new JSONObject();
        try {
            postData.put("nombre", nombre);
            postData.put("id_accesorio", idAccesorio);
            postData.put("id_registro_computador", idComputador);
            postData.put("estado", "Activo");
            postData.put("fecha", fecha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getActivity(), "Reservación exitosa!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }
}

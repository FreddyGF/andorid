package com.sistemaelite.portatilplus_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.sistemaelite.portatilplus_2.network.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroActivity extends AppCompatActivity {

    private TextInputEditText editTextNombres, editTextApellidos, editTextEmail, editTextTelefono, editTextPassword, editTextConfirmPassword;
    private Button btnInicio, btnCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNombres = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellido);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        btnInicio = findViewById(R.id.btn_inicio);
        btnCrear = findViewById(R.id.btn_crear);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String nombres = editTextNombres.getText().toString().trim();
        String apellidos = editTextApellidos.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String telefono = editTextTelefono.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Validar si los campos están vacíos
        if (nombres.isEmpty() || apellidos.isEmpty() || email.isEmpty() || telefono.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato del correo electrónico
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar longitud del teléfono
        if (telefono.length() < 10) {
            Toast.makeText(this, "El teléfono debe tener al menos 10 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar longitud de la contraseña
        if (password.length() < 4) {
            Toast.makeText(this, "La contraseña debe tener al menos 4 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que las contraseñas coinciden
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://portatilplusapi.onrender.com/registro";

        JSONObject postData = new JSONObject();
        try {
            postData.put("nombre", nombres);
            postData.put("apellido", apellidos);
            postData.put("telefono", telefono);
            postData.put("correo", email);
            postData.put("contrasena", password);
            postData.put("rol", "usuario");
            postData.put("estado", "Activo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.length() > 0) {
                            Toast.makeText(RegistroActivity.this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String errorMessage = response.optString("message", "¡Registro fallido!");
                            Toast.makeText(RegistroActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Registro", "Error de registro: " + error.toString());
                Toast.makeText(RegistroActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    // Método para validar el formato del correo electrónico
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

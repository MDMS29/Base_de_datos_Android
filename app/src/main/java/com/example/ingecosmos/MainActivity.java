package com.example.ingecosmos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText etPnombre, etSnombre, etPapellido, etSapellido, etRol, etTipoDoc, etNiden;
    Button btnGuardar;

    RequestQueue requestQueue;
    private static final String URLI = "http://127.0.0.1/ingecosmo-android/saveUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = findViewById(R.id.btnGuardar);
        etPnombre = findViewById(R.id.etPnombre);
        etSnombre = findViewById(R.id.etSnombre);
        etPapellido = findViewById(R.id.etPapellido);
        etSapellido = findViewById(R.id.etSapellido);
        etRol = findViewById(R.id.etRol);
        etTipoDoc = findViewById(R.id.etTipoDoc);
        etNiden = findViewById(R.id.etNiden);

        btnGuardar.setOnClickListener(view -> {
            String nombreP = etPnombre.getText().toString().trim();
            String nombreS = etSnombre.getText().toString().trim();
            String apellidoP = etPapellido.getText().toString().trim();
            String apellidoS = etSapellido.getText().toString().trim();
            String rol = etRol.getText().toString().trim();
            String tipoDoc = etTipoDoc.getText().toString().trim();
            String nIden = etNiden.getText().toString().trim();
            guardar(nombreP, nombreS, apellidoP, apellidoS, rol, tipoDoc, nIden);
        });


    }

    public void guardar(final String pNombre, final String sNombre, final String pApellido, final String sApellido, final String rol, final String tipoDoc, final String nIden) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLI,
                response -> Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show(), error -> Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show()
        ) {
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_rol", rol);
                params.put("tipo_doc", tipoDoc);
                params.put("n_identificacion", nIden);
                params.put("nombre_p", pNombre);
                params.put("nombre_s", sNombre);
                params.put("apellido_p", pApellido);
                params.put("apellido_s", sApellido);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}

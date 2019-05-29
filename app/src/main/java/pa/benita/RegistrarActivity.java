package pa.benita;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarActivity extends AppCompatActivity {

    EditText clave, nombre, direccion, tiempo, comentarios, capacidad;
    Context context;
    Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        getSupportActionBar().setTitle("Registrar");

        clave = findViewById(R.id.txtClave);
        nombre = findViewById(R.id.txtNombre);
        direccion = findViewById(R.id.txtDireccion);
        tiempo = findViewById(R.id.txtTiempo);
        comentarios = findViewById(R.id.txtComentarios);
        capacidad = findViewById(R.id.txtCapacidad);
        queue = Queue.getInstance(this);
        context = this;
    }

    public void registrar(View view) {
        CentroAdopcion centroAdopcion = new CentroAdopcion(
            clave.getText().toString(),
            nombre.getText().toString(),
            direccion.getText().toString(),
            comentarios.getText().toString(),
            Integer.parseInt(tiempo.getText().toString()),
            Integer.parseInt(capacidad.getText().toString())
        );

        JSONObject body = new JSONObject();

        try {
            body = new JSONObject(new Gson().toJson(centroAdopcion));
        } catch (JSONException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest request = new JsonObjectRequest(
            Request.Method.POST,
            "http://45.79.25.148/api/centro-adopcion",
            body,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    clave.setText("");
                    nombre.setText("");
                    direccion.setText("");
                    tiempo.setText("");
                    comentarios.setText("");
                    capacidad.setText("");
                    Toast.makeText(context, "Registrado exitosamente", Toast.LENGTH_LONG).show();
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Ha ocurrido un error: " + error.networkResponse.toString(), Toast.LENGTH_LONG).show();
                }
            }
        );

        queue.addToQueue(request);
    }
}

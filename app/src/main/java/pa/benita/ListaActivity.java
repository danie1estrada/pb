package pa.benita;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar spinner;
    private Adapter adapter;
    private Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        getSupportActionBar().setTitle("Consultar");

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        spinner = findViewById(R.id.spinner);
        queue = Queue.getInstance(this);
        cargarDatos();
    }

    private void cargarDatos() {
        StringRequest request = new StringRequest(
            Request.Method.GET,
            "http://45.79.25.148/api/centro-adopcion",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    CentroAdopcion[] centros = new Gson().fromJson(response, CentroAdopcion[].class);
                    for (CentroAdopcion centro : centros)
                        adapter.arrayList.add(centro);
                    adapter.notifyDataSetChanged();
                    spinner.setVisibility(View.GONE);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(adapter.context, "Error al cargar los datos", Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }
            }
        );

        queue.addToQueue(request);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private Context context;
        private ArrayList<CentroAdopcion> arrayList;

        public Adapter(Context context) {
            this.context = context;
            arrayList = new ArrayList<>();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout, viewGroup, false)
            );
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            CentroAdopcion centro = arrayList.get(i);

            viewHolder.clave.setText(centro.clave_cadopcion);
            viewHolder.nombre.setText(centro.nombre_ca);
            viewHolder.direccion.setText(centro.direccion_ca);
            viewHolder.tiempo.setText(String.valueOf(centro.tiempo_eo));
            viewHolder.comentarios.setText(centro.comentarios);
            viewHolder.capacidad.setText(String.valueOf(centro.capacidad));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView clave, nombre, direccion, tiempo, comentarios, capacidad;

            public ViewHolder(View itemView) {
                super(itemView);
                clave = itemView.findViewById(R.id.clave);
                nombre = itemView.findViewById(R.id.nombre);
                direccion = itemView.findViewById(R.id.direccion);
                tiempo = itemView.findViewById(R.id.direccion);
                comentarios = itemView.findViewById(R.id.comentarios);
                capacidad = itemView.findViewById(R.id.capacidad);
            }
        }
    }
}

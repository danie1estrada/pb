package pa.benita;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private Queue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        queue = Queue.getInstance(this);
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private Context context;
        private ArrayList arrayList;

        public Adapter(Context context) {
            this.context = context;
            arrayList = new ArrayList();
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

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}

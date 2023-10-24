package ignacio.campillos.androidstudio_recycler_ejemplo06;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;


import ignacio.campillos.androidstudio_recycler_ejemplo06.adapters.ToDoAdapter;
import ignacio.campillos.androidstudio_recycler_ejemplo06.databinding.ActivityMainBinding;
import ignacio.campillos.androidstudio_recycler_ejemplo06.modelos.ToDo;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<ToDo> todoList;
    private ActivityMainBinding binding;
    private ToDoAdapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        todoList = new ArrayList<ToDo>();
        crearTareas();

        adapter = new ToDoAdapter(todoList,R.layout.todo_view_model, MainActivity.this); //Lista + Resource + Contexto
        binding.contentMain.contenedor.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void crearTareas() {
        for (int i = 0; i < 1000000; i++) {
            todoList.add(new ToDo("Titulo"+i, "Contenido"+i));
        }
    }
}
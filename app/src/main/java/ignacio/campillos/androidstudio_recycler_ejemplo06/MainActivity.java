package ignacio.campillos.androidstudio_recycler_ejemplo06;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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
        //crearTareas(); --> Vamos a hacer uso del boton en lugar de automaticamente crear tareas

        adapter = new ToDoAdapter(todoList,R.layout.todo_view_model, MainActivity.this); //Lista + Resource + Contexto
        binding.contentMain.contenedor.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        binding.contentMain.contenedor.setLayoutManager(layoutManager);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Llamar al metodo para crear un tod0 al pulsar en el fab

                createToDo().show();
            }
        });


    }

    private AlertDialog createToDo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CREAR TAREA");
        builder.setCancelable(false);

        //Mapeamos el layout creado de todo_model_alert
        View todoAlert = LayoutInflater.from(this).inflate(R.layout.todo_model_alert,null);
        EditText txtTitulo = todoAlert.findViewById(R.id.txtTituloToDoModelAlert);
        EditText txtContenido = todoAlert.findViewById(R.id.txtContenidoToDoModelAlert);
        builder.setView(todoAlert);

        builder.setNegativeButton("CANCELAR", null);
        builder.setPositiveButton("INSERTAR", new DialogInterface.OnClickListener() {
            //SI al hacer click a insertar los dos texts no estan vacios, entonces agrega el tod0 a la lista
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!txtTitulo.getText().toString().isEmpty() &&
                !txtContenido.getText().toString().isEmpty()){
                    todoList.add(new ToDo(txtTitulo.getText().toString(),
                            txtContenido.getText().toString()));

                    //Avisar al adapter de que hay cambios
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return builder.create();
    }
    private void crearTareas() {
        for (int i = 0; i < 1000000; i++) {
            todoList.add(new ToDo("Titulo"+i, "Contenido"+i));
        }
    }
}
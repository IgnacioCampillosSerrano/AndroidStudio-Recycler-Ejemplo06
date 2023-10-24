package ignacio.campillos.androidstudio_recycler_ejemplo06.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ignacio.campillos.androidstudio_recycler_ejemplo06.R;
import ignacio.campillos.androidstudio_recycler_ejemplo06.modelos.ToDo;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.TodoVH> {
    //La lista de cosas que quiero mostrar
    private List<ToDo> objects;



    //LLa fila del elemento que quiero mostrar
    private int resource;



    //El contexto (actividad) donde lo voy a mostrar
    private Context context;

    public ToDoAdapter(List<ToDo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Instanciar tantos elementos como quepan por pantalla
        View todoView = LayoutInflater.from(context).inflate(resource, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        todoView.setLayoutParams(lp);
        return new TodoVH(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoVH holder, int position) {
        //Para pintar el elemento ( Llenar los objetos creados antes de lbTitulo, lbContenido... etc )
        ToDo toDo = objects.get(position);

        holder.lbTitulo.setText(toDo.getTitulo());
        holder.lbContenido.setText(toDo.getContenido());
        holder.lbFecha.setText(toDo.getFecha().toString());

        if (toDo.isCompletado()){
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_on_background);
        }else{
            holder.btnCompletado.setImageResource(android.R.drawable.checkbox_off_background);
        }

        holder.btnCompletado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDo.setCompletado(!toDo.isCompletado());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
//Cuantos objetos tengo para mostrar

        return objects.size();

    }

    public class TodoVH extends RecyclerView.ViewHolder{

        //Los elementos de la view "todo_view_model"
        TextView lbTitulo, lbContenido, lbFecha;
        ImageButton btnCompletado;

        public TodoVH(@NonNull View itemView) {
            super(itemView);

            lbTitulo = itemView.findViewById(R.id.lbTituloToDoViewModel);
            lbContenido = itemView.findViewById(R.id.lbContenidoToDoViewModel);
            lbFecha = itemView.findViewById(R.id.lbFechaToDoViewModel);
            btnCompletado = itemView.findViewById(R.id.btnCompletadoToDoViewModel);

        }
    }
}

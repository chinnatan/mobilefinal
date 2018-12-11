package th.ac.kmitl.a59070040.todos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.a59070040.R;

public class TodosAdapter extends ArrayAdapter<Todos> {

    List<Todos> todosList = new ArrayList<Todos>();
    Context context;

    public TodosAdapter(Context context, int resource, List<Todos> objects) {
        super(context, resource, objects);
        this.todosList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View todosItem = LayoutInflater.from(context).inflate(R.layout.fragment_todos_item, parent, false);

        TextView id = todosItem.findViewById(R.id.todositem_id);
        TextView title = todosItem.findViewById(R.id.todositem_title);
        TextView completed = todosItem.findViewById(R.id.todositem_completed);

        Todos row = todosList.get(position);
        id.setText(String.valueOf(row.getId()));
        title.setText(row.getTitle());
        if(row.isCompleted()) {
            completed.setText("Completed");
        } else {
            completed.setText(" ");
        }

        return todosItem;
    }
}

package th.ac.kmitl.a59070040.post;

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

public class PostAdapter extends ArrayAdapter<Post> {

    List<Post> postList = new ArrayList<Post>();
    Context context;

    public PostAdapter(Context context, int resource, List<Post> objects) {
        super(context, resource, objects);
        this.postList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View postItem = LayoutInflater.from(context).inflate(R.layout.fragment_post_item, parent, false);

        TextView id = postItem.findViewById(R.id.post_id);
        TextView body = postItem.findViewById(R.id.post_body);

        Post row = postList.get(position);
        id.setText(String.valueOf(row.getId()) + " : " + row.getTitle());
        body.setText(row.getBody());

        return postItem;
    }
}

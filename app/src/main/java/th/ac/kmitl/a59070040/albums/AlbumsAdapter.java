package th.ac.kmitl.a59070040.albums;

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

public class AlbumsAdapter extends ArrayAdapter<Albums> {

    List<Albums> albumsList = new ArrayList<Albums>();
    Context context;

    public AlbumsAdapter(Context context, int resource, List<Albums> objects) {
        super(context, resource, objects);
        this.albumsList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View albumsItem = LayoutInflater.from(context).inflate(R.layout.fragment_albums_item, parent, false);

        TextView id = albumsItem.findViewById(R.id.albumesitem_id);
        TextView title = albumsItem.findViewById(R.id.albumesitem_title);

        Albums row = albumsList.get(position);
        id.setText(String.valueOf(row.getId()));
        title.setText(row.getTitle());

        return albumsItem;
    }
}

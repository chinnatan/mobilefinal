package th.ac.kmitl.a59070040.photos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.a59070040.R;
import th.ac.kmitl.a59070040.albums.Albums;

public class PhotosAdapter extends ArrayAdapter<Photos> {

    List<Photos> photosList = new ArrayList<Photos>();
    Context context;

    public PhotosAdapter(Context context, int resource, List<Photos> objects) {
        super(context, resource, objects);
        this.photosList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View photosItem = LayoutInflater.from(context).inflate(R.layout.fragment_photos_item, parent, false);

        TextView id = photosItem.findViewById(R.id.photositem_id);
        TextView title = photosItem.findViewById(R.id.photositem_title);
        final ImageView image = photosItem.findViewById(R.id.photositem_image);

        Photos row = photosList.get(position);
        id.setText(String.valueOf(row.getId()));
        title.setText(row.getTitle());

        return photosItem;
    }
}

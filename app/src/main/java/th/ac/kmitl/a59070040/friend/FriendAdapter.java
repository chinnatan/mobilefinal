package th.ac.kmitl.a59070040.friend;

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

public class FriendAdapter extends ArrayAdapter<Friend> {

    List<Friend> friendsList = new ArrayList<Friend>();
    Context context;

    public FriendAdapter (Context context, int resource, List<Friend> objects) {
        super(context, resource, objects);
        this.friendsList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View friendsItem = LayoutInflater.from(context).inflate(R.layout.fragment_friend_item, parent, false);

        TextView id = friendsItem.findViewById(R.id.frienditem_id);
        TextView email = friendsItem.findViewById(R.id.frienditem_email);
        TextView phone = friendsItem.findViewById(R.id.frienditem_phone);
        TextView website = friendsItem.findViewById(R.id.frienditem_website);

        Friend row = friendsList.get(position);
        id.setText(row.getId() + " : " + row.getName());
        email.setText(row.getEmail());
        phone.setText(row.getPhone());
        website.setText(row.getWebsite());

        return friendsItem;
    }
}

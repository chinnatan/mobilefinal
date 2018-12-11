package th.ac.kmitl.a59070040.albums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import th.ac.kmitl.a59070040.R;
import th.ac.kmitl.a59070040.comment.Comment;
import th.ac.kmitl.a59070040.comment.CommentAdapter;
import th.ac.kmitl.a59070040.comment.CommentFragment;
import th.ac.kmitl.a59070040.photos.PhotosFragment;
import th.ac.kmitl.a59070040.post.Post;

public class AlbumsFragment extends Fragment {

    private static final String TAG = "ALBUMS";

    private String url;
    private ArrayList<Albums> albumsArrayList = new ArrayList<>();
    private ListView albumsList;
    private AlbumsAdapter albumsAdapter;

    private Button zBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        albumsFragmentElements();
        initBack();

        Bundle bundle;
        bundle = getArguments();
        final int userId = bundle.getInt("userId");

        url = "https://jsonplaceholder.typicode.com/users/" + userId + "/albums";

        albumsList = getView().findViewById(R.id.albums_list);
        albumsAdapter = new AlbumsAdapter(getActivity(), R.layout.fragment_albums_item, albumsArrayList);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG,"error - " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    try {
                        final JSONArray jsonArray = new JSONArray(response.body().string());
                        Log.d(TAG, "JSON ARRAY SIZE : " + jsonArray.length());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    try {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        Albums albums = new Albums(jsonObject.getInt("id"), jsonObject.getString("title"));
                                        albumsArrayList.add(albums);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                albumsAdapter.notifyDataSetChanged();
                                albumsList.setAdapter(albumsAdapter);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        albumsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Albums albums = (Albums) parent.getAdapter().getItem(position);
                Fragment fragment = new PhotosFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                bundle.putInt("id", albums.getId());
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_view, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void albumsFragmentElements() {
        zBack = getView().findViewById(R.id.albums_backbtn);
    }

    private void initBack() {
        zBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}

package th.ac.kmitl.a59070040.photos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import th.ac.kmitl.a59070040.albums.Albums;
import th.ac.kmitl.a59070040.albums.AlbumsAdapter;

public class PhotosFragment extends Fragment {

    private static final String TAG = "PHOTOS";

    private String url;
    private ArrayList<Photos> photosArrayList = new ArrayList<>();
    private ListView photosList;
    private PhotosAdapter photosAdapter;

    private Button zBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        photosFragmentElements();
        initBack();

        Bundle bundle;
        bundle = getArguments();
        final int id = bundle.getInt("id");

        url = "https://jsonplaceholder.typicode.com/albums/" + id + "/photos";

        photosList = getView().findViewById(R.id.photos_list);
        photosAdapter = new PhotosAdapter(getActivity(), R.layout.fragment_photos_item, photosArrayList);

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
                                        Photos photos = new Photos(jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getString("thumbnailUrl"));
                                        photosArrayList.add(photos);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                photosAdapter.notifyDataSetChanged();
                                photosList.setAdapter(photosAdapter);
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
    }

    private void photosFragmentElements() {
        zBack = getView().findViewById(R.id.photos_backbtn);
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

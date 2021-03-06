package th.ac.kmitl.a59070040.post;

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
import th.ac.kmitl.a59070040.comment.CommentFragment;
import th.ac.kmitl.a59070040.friend.Friend;
import th.ac.kmitl.a59070040.friend.MyFriendFragment;

public class PostFragment extends Fragment {

    private static final String TAG = "POST";

    private String url;
    private ArrayList<Post> postArrayList = new ArrayList<>();
    private ListView postList;
    private PostAdapter postAdapter;

    private Button zBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        postFragmentElements();
        initBack();

        Bundle bundle;
        bundle = getArguments();
        final int userId = bundle.getInt("userId");

        url = "https://jsonplaceholder.typicode.com/users/" + userId + "/posts";

        postList = getView().findViewById(R.id.post_list);
        postAdapter = new PostAdapter(getActivity(), R.layout.fragment_post_item, postArrayList);

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
                                        Post post = new Post(jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getString("body"));
                                        postArrayList.add(post);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                postAdapter.notifyDataSetChanged();
                                postList.setAdapter(postAdapter);
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

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = (Post) parent.getAdapter().getItem(position);
                Fragment fragment = new CommentFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                bundle.putInt("postId", post.getId());
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_view, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void postFragmentElements() {
        zBack = getView().findViewById(R.id.post_backbtn);
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

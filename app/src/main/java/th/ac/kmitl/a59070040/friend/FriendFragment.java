package th.ac.kmitl.a59070040.friend;

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
import android.widget.Toast;

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
import th.ac.kmitl.a59070040.HomeFragment;
import th.ac.kmitl.a59070040.R;

public class FriendFragment extends Fragment {

    private static final String TAG = "FRIEND";

    private Button zBack;
    private ListView zFriendList;

    private final String url = "https://jsonplaceholder.typicode.com/users";

    private ArrayList<Friend> friendsArrayList = new ArrayList<>();
    private ListView friendList;
    private FriendAdapter friendAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        friendFragmentElements();
        initBack();

        friendList = getView().findViewById(R.id.friend_list);
        friendAdapter = new FriendAdapter(getActivity(), R.layout.fragment_friend_item, friendsArrayList);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(getActivity(), "error - " + e.getMessage(), Toast.LENGTH_LONG).show();
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
                                        Log.d(TAG, String.valueOf(jsonObject.getInt("id")));
                                        Friend friend = new Friend(jsonObject.getInt("id"), jsonObject.getString("name"), jsonObject.getString("email"), jsonObject.getString("website"), jsonObject.getString("phone"));
                                        friendsArrayList.add(friend);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                friendAdapter.notifyDataSetChanged();
                                friendList.setAdapter(friendAdapter);
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

        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Friend friend = (Friend) parent.getAdapter().getItem(position);
                Fragment fragment = new MyFriendFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                bundle.putInt("userId", friend.getId());
                bundle.putString("name", friend.getName());
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_view, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void friendFragmentElements() {
        zBack = getView().findViewById(R.id.friend_backbtn);
        zFriendList = getView().findViewById(R.id.friend_list);
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

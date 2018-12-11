package th.ac.kmitl.a59070040.todos;

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
import th.ac.kmitl.a59070040.R;
import th.ac.kmitl.a59070040.friend.Friend;
import th.ac.kmitl.a59070040.friend.FriendAdapter;

public class TodosFragment extends Fragment {

    private static final String TAG = "TODOS";

    private String url;
    private ArrayList<Todos> todosArrayList = new ArrayList<>();
    private ListView todosList;
    private TodosAdapter todosAdapter;

    private Button zBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        todosFragmentElements();
        initBack();

        Bundle bundle;
        bundle = getArguments();
        final int userId = bundle.getInt("userId");

        url = "https://jsonplaceholder.typicode.com/users/" + userId + "/todos";

        todosList = getView().findViewById(R.id.todos_list);
        todosAdapter = new TodosAdapter(getActivity(), R.layout.fragment_todos_item, todosArrayList);

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
                                        Todos todos = new Todos(jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getBoolean("completed"));
                                        todosArrayList.add(todos);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                todosAdapter.notifyDataSetChanged();
                                todosList.setAdapter(todosAdapter);
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

    private void todosFragmentElements() {
        zBack = getView().findViewById(R.id.todos_backbtn);
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

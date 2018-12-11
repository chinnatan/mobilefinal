package th.ac.kmitl.a59070040.friend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import th.ac.kmitl.a59070040.R;
import th.ac.kmitl.a59070040.albums.AlbumsFragment;
import th.ac.kmitl.a59070040.post.PostFragment;
import th.ac.kmitl.a59070040.todos.TodosFragment;

public class MyFriendFragment extends Fragment {

    private TextView zTtile;
    private Button zTodos;
    private Button zPost;
    private Button zAlbums;
    private Button zBack;

    private int userId;
    private String userName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myfriend, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myfrinedFragmentElements();
        initTodos();
        initPost();
        initAlbums();
        initBack();

        Bundle bundle;
        bundle = getArguments();
        userId = bundle.getInt("userId");
        userName = bundle.getString("name");

        zTtile.setText(userId + " : " + userName);

    }

    private void myfrinedFragmentElements() {
        zTtile = getView().findViewById(R.id.myfriend_id);
        zTodos = getView().findViewById(R.id.myfriend_todos);
        zPost = getView().findViewById(R.id.myfriend_post);
        zAlbums = getView().findViewById(R.id.myfriend_albums);
        zBack = getView().findViewById(R.id.myfriend_backbtn);
    }

    private void initTodos() {
        zTodos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new TodosFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                bundle.putInt("userId", userId);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_view, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void initPost() {
        zPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PostFragment();
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null);
                bundle.putInt("userId", userId);
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.main_view, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void initAlbums() {
        zAlbums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new AlbumsFragment()).addToBackStack(null).commit();
            }
        });
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

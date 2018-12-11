package th.ac.kmitl.a59070040;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import th.ac.kmitl.a59070040.friend.FriendFragment;

public class HomeFragment extends Fragment {

    private static final String TAG = "HOME";
    private static final String isLogin = "loginStatus";
    private SharedPreferences loginCheck;
    private SharedPreferences.Editor loginChange;

    private TextView zHello;
    private TextView zQuote;
    private Button zProfile;
    private Button zMyFriends;
    private Button zSignout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeFragmentElements();
        initFragment();
        initFriend();
        initSignout();
    }

    private void homeFragmentElements() {
        zHello = getView().findViewById(R.id.home_hello);
        zQuote = getView().findViewById(R.id.home_quote);
        zProfile = getView().findViewById(R.id.home_profilesetupbtn);
        zMyFriends = getView().findViewById(R.id.home_myfriendsbtn);
        zSignout = getView().findViewById(R.id.home_signoutbtn);
    }

    private void initFragment() {
        SharedPreferences my = getActivity().getSharedPreferences(isLogin, Context.MODE_PRIVATE);
        String name = my.getString("name", "unknow");

        zHello.setText("Hello " + name);
    }

    private void initFriend() {
        zMyFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new FriendFragment()).addToBackStack(null).commit();
            }
        });
    }

    private void initSignout() {
        zSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCheck = getActivity().getSharedPreferences(isLogin, Context.MODE_PRIVATE);
                loginChange = loginCheck.edit();
                loginChange.clear();
                loginChange.commit();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).commit();
            }
        });
    }
}

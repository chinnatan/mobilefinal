package th.ac.kmitl.a59070040;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    private static final String TAG = "HOME";

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
    }

    private void homeFragmentElements() {
        zHello = getView().findViewById(R.id.home_hello);
        zQuote = getView().findViewById(R.id.home_quote);
        zProfile = getView().findViewById(R.id.home_profilesetupbtn);
        zMyFriends = getView().findViewById(R.id.home_myfriendsbtn);
        zSignout = getView().findViewById(R.id.home_signoutbtn);
    }
}

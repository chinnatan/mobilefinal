package th.ac.kmitl.a59070040;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment {

    private static final String TAG = "LOGIN";

    private EditText zUsername;
    private EditText zPassword;
    private Button zLogin;
    private TextView zRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginFragmentElements();
        initRegister();
    }

    private void loginFragmentElements() {
        zUsername = getView().findViewById(R.id.login_username);
        zPassword = getView().findViewById(R.id.login_password);
        zLogin = getView().findViewById(R.id.login_loginbtn);
        zRegister = getView().findViewById(R.id.login_register);
    }

    private void initRegister() {
        zRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new RegisterFragment()).addToBackStack(null).commit();
            }
        });
    }
}

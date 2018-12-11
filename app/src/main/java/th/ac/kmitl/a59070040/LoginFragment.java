package th.ac.kmitl.a59070040;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private static final String TAG = "LOGIN";
    private SQLiteDatabase myDB;
    private static final String isLogin = "loginStatus";
    private SharedPreferences loginCheck;
    private SharedPreferences.Editor loginChange;

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
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        checkCurrentUser();
        loginFragmentElements();
        initRegister();
        initLogin();
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

    private void initLogin() {
        zLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = zUsername.getText().toString();
                String password = zPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill out this form", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor loginCursorCheck = myDB.rawQuery("select * from account where username = '" + username + "' and password = '" + password + "'", null);
                    if (loginCursorCheck.getCount() > 0) {
                        loginCursorCheck.move(1);

                        loginCheck = getActivity().getSharedPreferences(isLogin, Context.MODE_PRIVATE);
                        loginChange = loginCheck.edit();
                        loginChange.putString("userId", String.valueOf(loginCursorCheck.getInt(0)));
                        loginChange.putString("name", loginCursorCheck.getString(4));
                        loginChange.commit();

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new HomeFragment()).commit();
                    } else {
                        Toast.makeText(getActivity(), "Invalid user or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void checkCurrentUser() {
        SharedPreferences loginCheck = getActivity().getSharedPreferences(isLogin, Context.MODE_PRIVATE);
        String isLogin = loginCheck.getString("userId", "0");
        if(!isLogin.equals("0")) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new HomeFragment()).commit();
        }
    }
}

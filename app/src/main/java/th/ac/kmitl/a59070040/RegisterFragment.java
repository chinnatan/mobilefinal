package th.ac.kmitl.a59070040;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterFragment extends Fragment {

    private static final String TAG = "REGISTER";
    private SQLiteDatabase myDB;

    private EditText zUsername;
    private EditText zPassword;
    private EditText zFullname;
    private EditText zAge;
    private Button zRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        registerFragmentElements();
        initRegister();
    }

    private void registerFragmentElements() {
        zUsername = getView().findViewById(R.id.register_username);
        zPassword = getView().findViewById(R.id.register_password);
        zFullname = getView().findViewById(R.id.register_fullname);
        zAge = getView().findViewById(R.id.register_age);
        zRegister = getView().findViewById(R.id.register_registerbtn);
    }

    private void initRegister() {
        zRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = zUsername.getText().toString();
                String password = zPassword.getText().toString();
                String fullname = zFullname.getText().toString();
                String ageStr = zAge.getText().toString();
                int age = Integer.parseInt(zAge.getText().toString());

                if (username.isEmpty() || password.isEmpty() || fullname.isEmpty() || ageStr.isEmpty()) {
                    Log.d(TAG, "field is empty");
                } else if (!((username.length() >= 6) && (username.length() <= 12))){
                    Log.d(TAG, "username is less");
                } else if(!(age >= 10 && age <= 80)) {
                    Log.d(TAG, "age not work");
                } else if(!(password.length() > 6)) {
                    Log.d(TAG, "password more than 6");
                } else if(!fullname.contains(" ")) {
                    Log.d(TAG, "fullname not work");
                } else {
                    ContentValues registerAccount = new ContentValues();
                    registerAccount.put("username", username);
                    registerAccount.put("password", password);
                    registerAccount.put("age", age);
                    registerAccount.put("fullname", fullname);

                    Log.d(TAG, "Insert new account");
                    myDB.insert("account", null, registerAccount);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new LoginFragment()).commit();
                }
            }
        });
    }
}

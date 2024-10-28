package com.appointment.booking.system.ui.main;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.appointment.booking.system.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginFragment extends Fragment {

    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    public static final String EXTRA_TEXT ="com.appointment.booking.system.EXTRA_TEXT";

    public LoginFragment() {

    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        editTextUsername = v.findViewById(R.id.login_email);
        editTextPassword = v.findViewById(R.id.login_psd);
        buttonLogin = v.findViewById(R.id.login_btn);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username, password;
                username = String.valueOf(editTextUsername.getText());
                password = String.valueOf(editTextPassword.getText());

                if(!username.equals("") && !password.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;

                            PutData putData = new PutData("https://192.168.0.7/LoginRegister/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Successful"))
                                    {
                                        String myID = username;
                                        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getActivity(),Appointments.class);
                                        intent.putExtra(EXTRA_TEXT,myID);
                                        getActivity().startActivity(intent);
                                        getActivity().finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getActivity(),"All fields are required !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
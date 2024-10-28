package com.appointment.booking.system.ui.main;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.appointment.booking.system.R;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vishnusivadas.advanced_httpurlconnection.PutData;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;

public class RegisterFragment extends Fragment implements LocationListener {


    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQ_CODE = 102;
    public static final int LOCATION_PERM_CODE = 103;

    EditText editTextFirstName, editTextLastName, editTextMiddleName,
             editTextDOB, editTextMobile, editTextUsername, editTextPassword;
    Button buttonRegister, buttonLocation;
    TextView textViewAddress;
    Switch switchGender;
    ImageView imageProfile;
    FloatingActionButton buttonCamera;
    LocationManager locationManager;


    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trustEveryone();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_register, container, false);

        imageProfile = v.findViewById(R.id.imageView);
        buttonCamera = v.findViewById(R.id.camera);
        editTextFirstName = v.findViewById(R.id.fname_text);
        editTextLastName = v.findViewById(R.id.lname_text);
        editTextMiddleName = v.findViewById(R.id.mname_text);
        switchGender = v.findViewById(R.id.gender_switch);
        editTextDOB = v.findViewById(R.id.date);
        editTextMobile = v.findViewById(R.id.mobile_text);
        editTextUsername = v.findViewById(R.id.email_text);
        editTextPassword = v.findViewById(R.id.password_text);
        buttonRegister = v.findViewById(R.id.register_btn);
        buttonLocation = v.findViewById(R.id.location);
        textViewAddress = v.findViewById(R.id.myaddress);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermission();
            }

        });
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askLocationPermission();
            }

        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        return v;
    }


    private void registerUser() {

        final String firstname, lastname, middlename, dob, mobile, username, password;
        firstname = String.valueOf(editTextFirstName.getText());
        lastname = String.valueOf(editTextLastName.getText());
        middlename = String.valueOf(editTextMiddleName.getText());
        dob = String.valueOf(editTextDOB.getText());
        mobile = String.valueOf(editTextMobile.getText());
        username = String.valueOf(editTextUsername.getText());
        password = String.valueOf(editTextPassword.getText());


        if (!firstname.equals("") && !lastname.equals("") && !middlename.equals("") &&
                !dob.equals("") && !mobile.equals("") && !username.equals("") && !password.equals("")) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String[] field = new String[8];
                    field[0] = "firstname";
                    field[1] = "lastname";
                    field[2] = "middlename";
                    field[3] = "gender";
                    field[4] = "dob";
                    field[5] = "mobile";
                    field[6] = "username";
                    field[7] = "password";

                    String[] data = new String[8];
                    data[0] = firstname;
                    data[1] = lastname;
                    data[2] = middlename;
                    if (switchGender.isChecked()) {
                        data[3] = "female";
                    } else {
                        data[3] = "male";
                    }
                    data[4] = dob;
                    data[5] = mobile;
                    data[6] = username;
                    data[7] = password;

                    PutData putData = new PutData("https://192.168.0.7/LoginRegister/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if (result.equals("Registration Successful")) {
                                String myID = username;

                                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), Appointments.class);
                                intent.putExtra(LoginFragment.EXTRA_TEXT, myID);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                }
            });
        } else {
            Toast.makeText(getActivity(), "All fields are required !", Toast.LENGTH_SHORT).show();
        }
    }


    private void askCameraPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            openCamera();
        }
    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERM_CODE);
        } else {
            getLocation();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] Permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(getActivity(), "Camera Permission is Required to Use Camera ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cam, CAMERA_REQ_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQ_CODE) {
            if (resultCode == RESULT_OK && data.hasExtra("data")) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    Glide.with(getActivity())
                            .load(bitmap)
                            .override(150, 150)
                            .centerCrop()
                            .into(imageProfile);

                } else {
                    Toast.makeText(getActivity(), "Error setting profile image", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getLocation() {

        try {
            locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, (LocationListener) getActivity());

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getActivity(), ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            textViewAddress.setText(address);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

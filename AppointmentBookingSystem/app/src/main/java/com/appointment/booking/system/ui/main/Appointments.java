package com.appointment.booking.system.ui.main;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.appointment.booking.system.MainActivity;
import com.appointment.booking.system.R;

public class Appointments extends AppCompatActivity {

    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments);

        Intent intent = getIntent();
        String myID = intent.getStringExtra(LoginFragment.EXTRA_TEXT);

        TextView username = findViewById(R.id.name_appointment);
        Spinner spinnerDoctor = findViewById(R.id.spinner_doctor);
        Spinner spinnerDate = findViewById(R.id.spinner_date);
        Spinner spinnerTime = findViewById(R.id.spinner);
        buttonLogout = findViewById(R.id.logout_appointment);

        username.setText(myID);

        ArrayAdapter<CharSequence> doctorAdapter = ArrayAdapter.createFromResource(this,
                R.array.doctor_array, android.R.layout.simple_spinner_item);
        doctorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoctor.setAdapter(doctorAdapter);


        ArrayAdapter<CharSequence> dateAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_array, android.R.layout.simple_spinner_item);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(dateAdapter);


        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(this,
                R.array.time_array, android.R.layout.simple_spinner_item);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(timeAdapter);



        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Appointments.this,"Log out Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Appointments.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

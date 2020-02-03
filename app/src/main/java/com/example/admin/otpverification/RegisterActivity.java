package com.example.admin.otpverification;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Spinner spinner;
    EditText editText_phone_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.spinnerCountries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, CountryData.countryNames);
        spinner.setAdapter(adapter);

        editText_phone_no = (EditText) findViewById(R.id.editTextPhone);
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String countrycode = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];


                String number = editText_phone_no.getText().toString().trim();
                if (number.isEmpty() || number.length() < 10)
                {
                    editText_phone_no.setError("Enter Valid Number");
                    editText_phone_no.requestFocus();
                    return;
                }

                String phonenumber = "+" + countrycode + number;
                Intent intent = new Intent(RegisterActivity.this, OtpActivity.class);
                intent.putExtra("phonenumber", phonenumber);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}

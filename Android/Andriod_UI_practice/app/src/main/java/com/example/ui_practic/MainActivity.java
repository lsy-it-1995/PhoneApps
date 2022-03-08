package com.example.ui_practic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText txtName, txtEmail, txtPassword, txt2Password;
    private Button btnPickImage, btnRegister;
    private TextView warningName, warningEmail, warningPassword, warning2Password;
    private Spinner countriesSpinner;
    private RadioGroup rdGender;
    private CheckBox agreementBox;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yet to talk about", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
                initRegister();
          }
        });
    }
    private void initRegister(){
        Log.d(TAG, "initRegister started");
        if(validData()){
            if(agreementBox.isChecked()){
                showSnackBar();
            }else{
                Toast.makeText(this, "Please read the license and click the checkbox", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "showSnackBar started");
        warning2Password.setVisibility(View.GONE);
        warningName.setVisibility(View.GONE);
        warningEmail.setVisibility(View.GONE);
        warningPassword.setVisibility(View.GONE);
        String name = txtName.getText().toString(),
               email = txtEmail.getText().toString(),
               password = txtPassword.getText().toString(),
               country = countriesSpinner.getSelectedItem().toString();
        String gender = "";

        switch(rdGender.getCheckedRadioButtonId()){
            case R.id.radio_female_btn:
                gender ="femail";
                break;
            case R.id.radio_male_btn:
                gender = "male";
                break;
            case R.id.radio_other_btn:
                gender = "other";
                break;
            default:
                gender = "unknown";
                break;
        }
        String snackText = "Name: " + name + "\n" + "Email: " + email + "\n" + "Gender: " + gender + "\n" + "country: " + country;

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtName.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txt2Password.setText("");
            }
        }).show();
    }

    private boolean validData(){
        Log.d(TAG, "validData started");
        if(txtName.getText().toString().equals("")){
            warningName.setVisibility(View.VISIBLE);
            warningName.setText("ENTER YOUR NAME");
            return false;
        }
        if(txtEmail.getText().toString().equals("")){
            warningEmail.setVisibility(View.VISIBLE);
            warningEmail.setText("ENTER YOUR EMAIL");
            return false;
        }
        if(txtPassword.getText().toString().equals("")){
            warningPassword.setVisibility(View.VISIBLE);
            warningPassword.setText("ENTER YOUR PASSWORD");
            return false;
        }
        if(txt2Password.getText().toString().equals("")){
            warning2Password.setVisibility(View.VISIBLE);
            warning2Password.setText("RE-ENTER YOUR PASSWORD");
            return false;
        }
        if(!txt2Password.getText().toString().equals(txtPassword.getText().toString())){
            warning2Password.setVisibility(View.VISIBLE);
            warning2Password.setText("PASSWORD DOESNT MATCH");
            return false;
        }
        return true;
    }
    private void initView(){
        Log.d(TAG, "initView: started");
        txt2Password = findViewById(R.id.txt_password_2);
        txtPassword = findViewById(R.id.txt_password);
        txtEmail = findViewById(R.id.txt_email);
        txtName = findViewById(R.id.txt_name);

        btnPickImage = findViewById(R.id.image_btn);
        btnRegister = findViewById(R.id.btn_register);

        warningName = findViewById(R.id.txt_warning_name);
        warningEmail = findViewById(R.id.txt_warning_email);
        warning2Password = findViewById(R.id.txt_warning_pwd2);
        warningPassword = findViewById(R.id.txt_warning_pwd);

        countriesSpinner = findViewById(R.id.spinner_country_list);
        rdGender = findViewById(R.id.radioGenders);
        agreementBox = findViewById(R.id.checkbox_agree);
        parent = findViewById(R.id.parent);

    }
}
package com.example.innoskritproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.innoskritproject.R.layout.activity_login;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText etEmail;
    CheckBox recaptcha;
    TextView Forgot,tvsignup;
    TextInputEditText etPassword;
    View vEmail;
    View vPassword;
    CardView btnLogin;
    TextInputLayout tilEmail, tilPassword;
    LinearLayout llsign;
    ProgressBar pb_loader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);
        findviews();
    }

    private void findviews() {

        etEmail = findViewById(R.id.etEmail);
        vEmail=findViewById(R.id.vEmail);
        vPassword = findViewById(R.id.vPassword);
        etPassword = findViewById(R.id.etPassword);
        recaptcha = findViewById(R.id.recaptcha);
        Forgot = findViewById(R.id.forgot);
        btnLogin = findViewById(R.id.btnLogin);
        llsign = findViewById(R.id.llsign);
        tvsignup = findViewById(R.id.tv_signup_client);
        pb_loader=findViewById(R.id.pb_loader);
        tilEmail=findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        btnLogin.setOnClickListener(this);
        llsign.setOnClickListener(this);
        Forgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (isValidate() && recaptcha.isChecked()==true) {
                    etEmail.getText().toString();
                    etPassword.getText().toString();
//                    updateLocalDataBase();
//                    loginUser(email, password, userRole);

                    Intent intent = new Intent(LoginActivity.this, OptionActivity.class);
                     startActivity(intent);
                     finish();

                }
                break;

            case R.id.forgot:
                startActivity(new Intent(LoginActivity.this, ForgetPassActivity.class));
                break;

            case R.id.llsign:
               startActivity(new Intent(LoginActivity.this, SignupActivity.class));

                break;
        }
    }
    private boolean isValidate() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isEmailValid(etEmail.getText().toString())) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText())) {
            Toast.makeText(LoginActivity.this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}

package com.example.innoskritproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_next;
    TextView tvTitle;
    EditText etSignUpEmail,etPassword,etConfirmPassword;
    LinearLayout llBack;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setToolbar();
        findviews();
    }

    private void findviews() {
        iv_next = findViewById(R.id.iv_next);
        etPassword =findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        etPassword.setOnClickListener(this);
        etConfirmPassword.setOnClickListener(this);
        iv_next.setOnClickListener(this);
        etSignUpEmail.setOnClickListener(this);
        llBack = findViewById(R.id.llBack1);
    }

    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText("Sign Up");
    }

    @Override
    public void onClick(View view) {
        if (view == iv_next && !etSignUpEmail.getText().toString().isEmpty()){
            if(isValidate()){
                startActivity(new Intent(SignupActivity.this, OtpActivity.class));
            }
        }
        if (view == llBack) {
            onBackPressed();
        }
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


    /*validation check need to define message*/
    private boolean isValidate() {
        etSignUpEmail.getText().toString();
        String pass=etPassword.getText().toString();
        String cpass=etConfirmPassword.getText().toString();
        if (!isEmailValid(etSignUpEmail.getText().toString())) {
            Toast.makeText(SignupActivity.this, getResources().getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
            etSignUpEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etSignUpEmail.getText())) {
            Toast.makeText(SignupActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(etPassword.getText())){
            Toast.makeText(SignupActivity.this, getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pass.equals(cpass)){
            Toast.makeText(SignupActivity.this, getResources().getString(R.string.please_enter_matching_password), Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        finish();
    }
}

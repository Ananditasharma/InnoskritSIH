package com.example.innoskritproject;

import android.app.Activity;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPassActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_next;
    TextView tvTitle;
    EditText etSignUpEmail,etPassword,etConfirmPassword;
    LinearLayout llBack;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pass);
        setToolbar();
        findviews();
    }

    private void findviews() {
        iv_next = findViewById(R.id.iv_next);
        builder = new AlertDialog.Builder(this);
        etSignUpEmail = findViewById(R.id.etSignUpEmail);
        iv_next.setOnClickListener(this);
        etSignUpEmail.setOnClickListener(this);
        llBack = findViewById(R.id.llBack1);
    }

    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        tvTitle.setText("Forget Password");
    }

    @Override
    public void onClick(View view) {
        if (view == iv_next && !etSignUpEmail.getText().toString().isEmpty()) {
            if (isValidate()) {
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Forgot Password ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Please Check your  Mail inbox for Password Change !!!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), " Mail not sent !!!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                AlertDialog alert = builder.create();

                alert.setTitle("Submit Application");
                alert.show();
            }

        }

        if (view == llBack) {
            Intent intent = new Intent(ForgetPassActivity.this, LoginActivity.class);
            startActivity(intent);
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
        if (!isEmailValid(etSignUpEmail.getText().toString())) {
            Toast.makeText(ForgetPassActivity.this, getResources().getString(R.string.please_enter_valid_email), Toast.LENGTH_SHORT).show();
            etSignUpEmail.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(etSignUpEmail.getText())) {
            Toast.makeText(ForgetPassActivity.this, getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}

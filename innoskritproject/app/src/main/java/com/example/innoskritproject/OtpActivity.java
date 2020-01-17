package com.example.innoskritproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private LinearLayout llBack;
    private CardView btnVerify;
    private EditText etOtp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_activity);
        setToolbar();
        findviews();
    }

    private void setToolbar() {
        tvTitle = findViewById(R.id.tvTitle);
        llBack = findViewById(R.id.llBack1);
        llBack.setOnClickListener(this);
        tvTitle.setText("OTP Verification");
    }

    private void findviews() {
   etOtp = findViewById(R.id.etOtp);
   btnVerify = findViewById(R.id.btnVerify);
   btnVerify.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if( v== btnVerify && !etOtp.getText().toString().isEmpty()){
            startActivity(new Intent(OtpActivity.this, OptionActivity.class));
        }
        if(v == llBack){

            onBackPressed();
        }
    }

    @Override
    public void onBackPressed () {
        super.onBackPressed();
        finish();
    }
}

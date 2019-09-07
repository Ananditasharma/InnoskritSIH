package com.example.mckinley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements login_fragment.OnLoginFormActivityListener {

    public static PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);
       // apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }

            if(prefConfig.readLoginStatus())
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new profile()).commit();


            }
            else
            {

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new login_fragment()).commit();
            }
        }
    }




    @Override
    public void performRegister() {

    }

    @Override
    public void performLogin() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new profile()).commit();

    }
}

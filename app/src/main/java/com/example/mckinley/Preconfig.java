package com.example.mckinley;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mckinley.R;

import static android.content.Context.MODE_PRIVATE;

class PrefConfig
{


    private SharedPreferences sharedPreferences;
    private Context context;

    public PrefConfig(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("com.example.preference_file", MODE_PRIVATE);

    }

    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor  = sharedPreferences.edit();
        editor.putBoolean("com.example.preference_login_status",status);
        editor.commit();


    }

    public boolean readLoginStatus()
    {
        return sharedPreferences.getBoolean("com.example.preference_login_status",false);

    }


    public void writeName(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("com.example.preference_user_name",name);
        editor.commit();


    }

    public String readName()
    {
        return  sharedPreferences.getString("com.example.preference_user_name","User");
    }


    public void displayToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}

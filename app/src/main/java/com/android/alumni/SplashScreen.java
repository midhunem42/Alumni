package com.android.alumni;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.alumni.Admin.AdminPage;
import com.android.alumni.Student.StudentPage;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread t=new Thread() {
            public void run() {

                try {
                    //sleep thread for 10 seconds, time in milliseconds
                    sleep(3000);
                    check();



                    //destroying Splash ac finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //start thread
        t.start();
    }

    private void check() {
        int i;
        boolean a;
        Context context = getApplicationContext();
        SharedPreferences pref=context.getSharedPreferences("logins",context.MODE_PRIVATE);
        i=pref.getInt("user",1);
        a=pref.getBoolean("login",false);

        if (a){
            if (i==1){
                startActivity(new Intent(this, AdminPage.class));
                finish();
            }
            else  if (i==2){
                startActivity(new Intent(this, StudentPage.class));
                finish();
            }
            else  if (i==3){
                startActivity(new Intent(this, AdminPage.class));
                finish();
            }
        }
        else {
            Intent it=new Intent(SplashScreen.this,LoginActivity.class);
            startActivity(it);
            finish();

        }


    }
}

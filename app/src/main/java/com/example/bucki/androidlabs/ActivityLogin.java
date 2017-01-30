package com.example.bucki.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {
    protected static final String ACTIVITY_NAME="ActivityLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");

        final SharedPreferences sharedPref = this.getSharedPreferences("com.example.bucki.androidlabs",Context.MODE_PRIVATE);

        final EditText email = (EditText)findViewById(R.id.editText2);
        email.setText(sharedPref.getString("com.example.bucki.androidlabs.emailLogin", "email@domain.com"));

        Button login = (Button)findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener(){
                 public void onClick(View v) {
                     sharedPref.edit().putString("com.example.bucki.androidlabs.emailLogin", email.getText().toString()).apply(); // put string at key value pair to login email edittext

                     Intent intent = new Intent(ActivityLogin.this, StartActivity.class);
                     startActivity(intent);
                 }
        });
    }

    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}

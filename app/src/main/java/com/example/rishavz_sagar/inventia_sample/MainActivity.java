package com.example.rishavz_sagar.inventia_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        getFragmentManager().beginTransaction().add(R.id.container, new FirstFragment()).commit();
    }

}

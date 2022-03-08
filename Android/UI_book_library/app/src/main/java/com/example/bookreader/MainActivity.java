 package com.example.bookreader;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button library_btn, waitlist_btn, favourite_btn,finished_btn, currently_read_btn, about_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_state();
        btnListener();

        Utils.getInstance(this);

    }

    private void btnListener(){
        library_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBookActivity.class);
                startActivity(intent);
            }
        });
        waitlist_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, waitlistActivity.class);
                startActivity(intent);
            }
        });
        favourite_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, favouriteActivity.class);
                startActivity(intent);
            }
        });
        finished_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, finishedBooksActivity.class);
                startActivity(intent);
            }
        });
        currently_read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, currentReadingActivity.class);
                startActivity(intent);
            }
        });

        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Developed by LSY");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                        intent.putExtra("url", "https://google.com");
                        startActivity(intent);
                    }
                });

                builder.create().show();
            }
        });
    }
    private void init_state(){
        library_btn = findViewById(R.id.btn_library);
        waitlist_btn = findViewById(R.id.btn_waitlist);
        favourite_btn = findViewById(R.id.btn_favourite);
        finished_btn = findViewById(R.id.btn_finished);
        currently_read_btn = findViewById(R.id.btn_currently_reading);
        about_btn = findViewById(R.id.btn_about);
    }
}
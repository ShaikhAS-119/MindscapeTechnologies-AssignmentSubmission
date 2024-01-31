package com.example.mindscape_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BlogViews extends AppCompatActivity {

    TextView thead,tcontent;
    ImageView imageView,back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_views);

        thead=findViewById(R.id.heading);
        tcontent=findViewById(R.id.content);

        imageView=findViewById(R.id.image);
        back=findViewById(R.id.BackIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        thead.setText(getIntent().getStringExtra("title"));
        tcontent.setText(getIntent().getStringExtra("body"));
        Glide.with(this).load(getIntent().getStringExtra("url")).into(imageView);
    }
}
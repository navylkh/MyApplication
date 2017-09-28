package com.example.chad.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by chad on 2017-09-28.
 */

public class DetailActivity extends AppCompatActivity {

    private Intent intent;
    private ImageView imageView;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // MainActivity에서 보낸 imageRes를 받기위해 getIntent()로 초기화
        intent = getIntent();
        imageView = (ImageView) findViewById(R.id.imageView);

        // "imgRes" key로 받은 값은 Int 형이기 때문에 getIntExtra(key, defaultValue);
        // 받는 값이 String 형이면 getStringExtra(key);
        imageView.setImageResource(intent.getIntExtra("imgRes", 0));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(intent.getStringExtra("title"));

    }
}

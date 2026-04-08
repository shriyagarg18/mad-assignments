package com.example.cameraapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class GalleryActivity extends AppCompatActivity {

    File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        GridView gridView = findViewById(R.id.gridView);

        File folder = getExternalFilesDir(null);
        files = folder.listFiles();

        ImageAdapter adapter = new ImageAdapter(this, files);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("path", files[position].getAbsolutePath());
            startActivity(intent);
        });
    }
}
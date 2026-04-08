package com.example.cameraapp;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        ImageView image = findViewById(R.id.image);
        TextView details = findViewById(R.id.details);
        Button deleteBtn = findViewById(R.id.deleteBtn);

        String path = getIntent().getStringExtra("path");
        File file = new File(path);

        image.setImageBitmap(BitmapFactory.decodeFile(path));

        details.setText(
                "Name: " + file.getName() +
                        "\nPath: " + file.getAbsolutePath() +
                        "\nSize: " + file.length() + " bytes"
        );

        deleteBtn.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Image")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (d, w) -> {
                        file.delete();
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}
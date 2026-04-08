package com.example.cameraapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.*;
import android.widget.*;
import java.io.File;

public class ImageAdapter extends BaseAdapter {

    Context context;
    File[] files;

    ImageAdapter(Context context, File[] files) {
        this.context = context;
        this.files = files;
    }

    public int getCount() { return files.length; }
    public Object getItem(int i) { return files[i]; }
    public long getItemId(int i) { return i; }

    public View getView(int i, View view, ViewGroup parent) {
        ImageView img = new ImageView(context);
        img.setImageBitmap(BitmapFactory.decodeFile(files[i].getAbsolutePath()));
        img.setLayoutParams(new GridView.LayoutParams(300, 300));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return img;
    }
}
package com.example.recipe_provider.innerstorage;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageStorage {

    public static Bitmap getBitmapFromDrawable(Context context, int drawableId) {
        return BitmapFactory.decodeResource(context.getResources(), drawableId);
    }

    public static String saveToInternalStorage(final Bitmap bitmapImage, final Context context) {
        ContextWrapper contextWrapper = new ContextWrapper(context);

        File directory = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE);

        Date now = new Date();
        final String imagePath = new SimpleDateFormat("yyyyMMdd_HHmmss").format(now);

        File filePath = new File(directory, imagePath);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath() + "/" + imagePath;
    }

    public static boolean loadImageFromStorage(final String path, final ImageView imageView) {
        try {
            File imageFile = new File(path);
            Bitmap bitmapImage = BitmapFactory.decodeStream(new FileInputStream(imageFile));
            imageView.setImageBitmap(bitmapImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

package com.example.recipe_provider;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import androidx.test.core.app.ApplicationProvider;

import com.example.recipe_provider.innerstorage.ImageStorage;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(RobolectricTestRunner.class)
public class InnerStorageTest {

    Context context ;


    @Before
    public void init() {
        context = ApplicationProvider.getApplicationContext();
    }
    @Test
    public void TEST_AVE_IMAGE() {
        String path = ImageStorage.saveToInternalStorage(Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888), context);
        Date now = new Date();
        final String imagePath = new SimpleDateFormat("yyyyMMdd_HHmmss").format(now);

    }
}

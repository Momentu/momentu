package com.momentu.momentuandroid.Utility;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import static com.momentu.momentuandroid.Adapter.FeedAdapter.context;

/**
 * Created by Ibrahim on 1/27/2018.
 */

public class ConvertImagesToStringOfBytes {

    //takes an image and compress it and convert it to String of bytes
    public static byte[] mediaToByteArray(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] imageBytes =
               return byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imageBytes,Base64.DEFAULT);
    }
    //takes an image and compress it and convert it to String of bytes
    public static byte[] mediaToByteArray(Uri video) {
        Log.d("Converter","Just got this path: "+ video);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(video);
            byteArrayOutputStream = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int n;

            while (-1 != (n = inputStream.read(buf)))
                byteArrayOutputStream.write(buf, 0, n);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}

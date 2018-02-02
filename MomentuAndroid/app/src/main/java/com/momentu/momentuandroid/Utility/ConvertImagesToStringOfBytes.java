package com.momentu.momentuandroid.Utility;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Ibrahim on 1/27/2018.
 */

public class ConvertImagesToStringOfBytes {

    //takes an image and compress it and convert it to String of bytes
    public static byte[] imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        byte[] imageBytes =
               return byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imageBytes,Base64.DEFAULT);
    }
}

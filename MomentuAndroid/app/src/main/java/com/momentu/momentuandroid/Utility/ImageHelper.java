package com.momentu.momentuandroid.Utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageHelper {
    public static File createTempImageFile() throws IOException {
        final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        final String imageFileName = "JPEG_" + timestamp;
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "pics");
        storageDir.mkdirs();
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }
    public static File createTempVideoFile() throws IOException {
        final String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        final String videoFileName = "MP4_" + timestamp;
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "videos");
        storageDir.mkdirs();
        return File.createTempFile(videoFileName, ".mp4", storageDir);
    }

    public static BitmapFactory.Options provideCompressionBitmapFactoryOptions() {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = false;
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        return opt;
    }
}

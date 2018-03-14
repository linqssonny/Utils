package com.sonnyjack.utils.bitmap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by SonnyJack on 2018/3/13.
 */
public class BitmapUtils {

    /**
     * calculate bitmap sample size
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger
            // inSampleSize).
            final float totalPixels = width * height;
            // Anything more than 2x the requested pixels we'll sample down
            // further.
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }

    /**
     * decode bitmap by local url
     *
     * @param imageLocalPath
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static Bitmap decodeBitmap(String imageLocalPath, int maxWidth, int maxHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 设置为true可以在不分配空间状态下计算出图片的大小
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeFile(imageLocalPath, options);
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imageLocalPath, options);
    }

    /**
     * save bitmap to appoint path
     *
     * @param bitmap
     * @param path
     * @return
     */
    public static boolean saveBitmap(Context context, Bitmap bitmap, String path) {
        return saveBitmap(context, bitmap, path, false);
    }

    /**
     * save bitmap to appoint path
     *
     * @param bitmap
     * @param path
     * @param recycle is or not recycle bitmap
     * @return
     */
    public static boolean saveBitmap(Context context, Bitmap bitmap, String path, boolean recycle) {
        if (null == bitmap) {
            return true;
        }
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            return false;
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (recycle) {
            recycleBitmap(bitmap);
        }
        notifyAlbum(context, path);
        return true;
    }

    /**
     * recycle bitmap
     *
     * @param bitmap
     */
    public static void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        bitmap = null;
    }

    /***
     * notify album scan appoint path
     *
     * @param context
     * @param path
     */
    public static void notifyAlbum(Context context, String path) {
        if (null == context || TextUtils.isEmpty(path)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        if (!path.startsWith("file://")) {
            path = "file://" + path;
        }
        intent.setData(Uri.parse(path));
        context.sendBroadcast(intent);
    }

    /**
     * cut bitmap by appoint size, when outWidth greater than bitmap width and outWidth equals bitmap width, the same to height
     * note: cut bitmap in center
     *
     * @param b
     * @param outWidth
     * @param outHeight
     * @return
     */
    public static Bitmap cutBitmap(Bitmap b, int outWidth, int outHeight) {
        if (null == b || b.isRecycled()) {
            return null;
        }
        int w = b.getWidth(), h = b.getHeight();
        outWidth = outWidth > w ? w : outWidth;
        outHeight = outHeight > h ? h : outHeight;
        int left = (w - outWidth) / 2;
        int top = (h - outHeight) / 2;
        Bitmap bitmap = Bitmap.createBitmap(b, left, top, outWidth, outHeight, null, false);
        return bitmap;
    }

    /**
     * calculate bitmap size in memory
     */
    public static int calculateBitmapSizeInMemory(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bytes.length;
        } catch (Exception exception) {
            return 0;
        }
    }

    /**
     * create thumb bitmap, size unit KB
     * the method is used for create thumb to wechat share
     *
     * @param bitmap
     * @param size
     * @return
     */
    public static Bitmap createThumbBitmap(Bitmap bitmap, int size) {
        if (null == bitmap || bitmap.isRecycled()) {
            return null;
        }
        size = size * 1024;
        int byteCount = calculateBitmapSizeInMemory(bitmap);
        if (byteCount < size) {
            return bitmap;
        }
        Bitmap result = bitmap;
        while (byteCount > size) {
            int width = result.getWidth();
            if (byteCount > 2 * 1024 * 1024) {
                //2M
                width = width / 3;
            } else if (byteCount > 1024 * 1024) {
                //1M
                width = (int) (width / 2f);
            } else if (byteCount > 512 * 1024) {
                //512KB
                width = (int) (width / 1.5f);
            } else if (byteCount > 256 * 1024) {
                //256KB
                width = (int) (width / 1.2f);
            } else {
                width = (int) (width / 1.1f);
            }
            float scale = result.getWidth() * 1.0f / width;
            int height = (int) (result.getHeight() * 1.0f / scale);
            result = Bitmap.createScaledBitmap(result, width, height, true);
            byteCount = calculateBitmapSizeInMemory(result);
        }
        return result;
    }

    /**
     * decode bitmap by uri
     *
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap decodeBitmapByUri(Context context, Uri uri) {
        Bitmap bmp = null;
        try {
            bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    /***
     * bitmap transform byte[]
     *
     * @param bitmap
     * @param quality 0-100
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap, int quality) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}

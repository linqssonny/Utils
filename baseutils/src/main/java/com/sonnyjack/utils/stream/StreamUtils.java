package com.sonnyjack.utils.stream;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.channels.FileChannel;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class StreamUtils {

    /**
     * close inputStream
     *
     * @param inputStream
     * @return
     */
    public static boolean close(InputStream inputStream) {
        boolean success = false;
        if (null == inputStream) {
            return success;
        }
        try {
            inputStream.close();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * close outputStream
     *
     * @param outputStream
     * @return
     */
    public static boolean close(OutputStream outputStream) {
        boolean success = false;
        if (null == outputStream) {
            return success;
        }
        try {
            outputStream.close();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * close fileChannel
     *
     * @param fileChannel
     * @return
     */
    public static boolean close(FileChannel fileChannel) {
        boolean success = false;
        if (null == fileChannel) {
            return success;
        }
        try {
            fileChannel.close();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * close reader
     *
     * @param reader
     * @return
     */
    public static boolean close(Reader reader) {
        boolean success = false;
        if (null == reader) {
            return success;
        }
        try {
            reader.close();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * stream to bytes
     *
     * @param inputStream
     * @return
     */
    public static byte[] stream2Bytes(InputStream inputStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
        } catch (Exception e) {
            close(outStream);
            return null;
        }
        byte[] bytes = outStream.toByteArray();
        close(outStream);
        return bytes;
    }
}

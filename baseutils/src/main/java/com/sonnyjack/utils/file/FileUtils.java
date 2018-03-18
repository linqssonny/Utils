package com.sonnyjack.utils.file;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class FileUtils {

    private FileUtils() {

    }

    /***
     * copy file
     *
     * @param sourceFile
     * @param targetFile
     * @return
     */
    public static boolean copyFile(File sourceFile, File targetFile) {
        boolean success = false;
        if (null == sourceFile || null == targetFile) {
            return success;
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            in = fis.getChannel();
            out = fos.getChannel();
            in.transferTo(0, in.size(), out);
            success = true;
        } catch (Exception e) {
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    /***
     * save String into file
     *
     * @param folder
     * @param fileName
     * @param content
     * @return
     */
    public static boolean saveFile(String folder, String fileName, String content) {
        boolean success = false;
        if (TextUtils.isEmpty(content)) {
            return success;
        }
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        return saveFile(inputStream, folder, fileName);
    }

    /***
     * save stream into file
     *
     * @param inputStream
     * @param folder
     * @param fileName
     * @return
     */
    public static boolean saveFile(InputStream inputStream, String folder, String fileName) {
        boolean success = false;
        if (TextUtils.isEmpty(folder) || TextUtils.isEmpty(fileName) || null == inputStream) {
            return success;
        }
        File fileDir = createFolder(folder);
        File file = new File(fileDir, fileName);
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            while ((len = inputStream.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    /**
     * read String from file
     *
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                stringBuffer.append(s);
                stringBuffer.append("\n");
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    /***
     * delete file or folder
     *
     * @param file
     */
    public static void delete(File file) {
        if (null == file || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
        } else {
            for (File f : file.listFiles()) {
                delete(f);
            }
            file.delete();
        }
    }

    /**
     * create folder by file path
     *
     * @param filePath
     * @return
     */
    public static File createFolder(String filePath) {
        File file = null;
        if (TextUtils.isEmpty(filePath)) {
            return file;
        }
        file = new File(filePath);
        if (null != file && !file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * cut file name by url contains suffix name
     *
     * @param url
     * @return
     */
    public static String buildFileNameByUrl(String url) {
        String fileName = null;
        if (!TextUtils.isEmpty(url)) {
            String tempUrl = url;
            int index = url.indexOf("?");
            if (index > 0) {
                tempUrl = url.substring(0, index);
            }
            index = tempUrl.lastIndexOf("/");
            fileName = tempUrl.substring(index + 1, tempUrl.length());
        }
        return fileName;
    }
}

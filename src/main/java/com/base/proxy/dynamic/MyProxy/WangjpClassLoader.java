package com.base.proxy.dynamic.myProxy;

import java.io.*;

/**
 * 描述:classLoader
 *
 * @author: mylover
 * @Time: 16/02/2017.
 */
public class WangjpClassLoader extends ClassLoader {

    private File dir;

    WangjpClassLoader(String path) {
        dir = new File(path);
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {

        if (dir != null) {
            File clazzFile = new File(dir, name + ".class");
            if (clazzFile.exists()) {
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(clazzFile);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                    return defineClass("com.base.proxy.dynamic.myProxy." + name,
                            byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return super.findClass(name);
    }


}

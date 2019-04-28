package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.gpproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GpClassLoader extends ClassLoader{

    private File classPathFile;

    public GpClassLoader(){
        String path = GpClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(path);
    }

    public Class findClass(String classSimpleName) {
        String className = GpClassLoader.class.getPackage().getName() + "." + classSimpleName;
        if (classPathFile == null){
            return null;
        }
        File file = new File(classPathFile, classSimpleName.replaceAll("\\.", "/") + ".class");
        if (!file.exists()){
            return null;
        }

        FileInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = in.read(bytes))!=-1){
                out.write(bytes,0,len);
            }
            Class<?> clazz = defineClass(className, out.toByteArray(), 0, out.size());
            return clazz;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            file.delete();
            if (in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

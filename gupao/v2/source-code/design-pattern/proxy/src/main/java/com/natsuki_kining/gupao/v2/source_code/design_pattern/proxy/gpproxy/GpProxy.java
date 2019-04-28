package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.gpproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GpProxy {

    public static Object newProxyInstance(GpClassLoader gpClassLoader, Class<?>[] interfaces, GpInvocationHandler h) throws Exception {

        //1、动态生成源代码.java文件
        String src = generatorJavaFile(interfaces);

        //2、Java文件输出磁盘
        String path = GpProxy.class.getResource("").getPath();
        System.out.println(path);
        File file = new File(path + "$Proxy0.java");
        FileWriter fw = new FileWriter(file);
        fw.write(src);
        fw.flush();
        fw.close();

        //3、把生成的.java文件编译成.class文件
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = javaFileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, javaFileManager, null, null, null, javaFileObjects);
        task.call();
        javaFileManager.close();
        file.delete();

        //4、编译生成的.class文件加载到JVM中来
        Class proxyClass = gpClassLoader.findClass("$Proxy0");
        Constructor constructor = proxyClass.getConstructor(GpInvocationHandler.class);

        //5、返回字节码重组以后的新的代理对象
        return constructor.newInstance(h);
    }

    private static String generatorJavaFile(Class<?>[] interfaces) {
        String ln = "\r\n";
        StringBuilder sb = new StringBuilder();
        sb.append("package com.natsuki_kining.design.pattern.proxy.gpproxy;"+ln);
        sb.append("import java.lang.reflect.Method;"+ln);
        for(Class clazz : interfaces){
            sb.append("import "+clazz.getName()+";"+ln);
        }
        sb.append("public final class $Proxy0 implements ");
        for (int i = 0; i < interfaces.length; i++) {
            sb.append(interfaces[i].getSimpleName());
            if (i<interfaces.length -1){
                sb.append(",");
            }
        }
        sb.append(" {"+ln);

        sb.append("     private GpInvocationHandler h;"+ln);
        sb.append("     public $Proxy0(GpInvocationHandler h) {"+ln);
        sb.append("         this.h = h;"+ln);
        sb.append("     }"+ln);

        for (Class clazz : interfaces) {
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                sb.append("     public final  "+m.getReturnType().getName()+" "+m.getName()+"()  {"+ln);
                sb.append("         try {"+ln);
                sb.append("             Method m = Class.forName(\""+clazz.getName()+"\").getMethod(\""+m.getName()+"\");"+ln);
                sb.append("             h.invoke(this, m, null);"+ln);
                sb.append("         } catch (Throwable t) {"+ln);
                sb.append("             t.printStackTrace();"+ln);
                sb.append("         }"+ln);
                sb.append("     }"+ln);
            }
        }
        sb.append("}");
        return sb.toString();
    }
}

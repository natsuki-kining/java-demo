package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.mycglib;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyEnhancer {

    private Class superClass;
    private MyMethodInterceptor methodInterceptor;


    public Object create(){
        if(superClass == null){
            return null;
        }
        if(methodInterceptor == null){
            return null;
        }
        try {
            //1、动态生成源代码.java文件
            String src = generatorJavaFile(superClass);

            //2、Java文件输出磁盘
            String path = MyEnhancer.class.getResource("").getPath();
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

            //4、编译生成的.class文件加载到JVM中来
            Class proxyClass = new MyClassLoader().findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(MyMethodInterceptor.class);

            //5、返回字节码重组以后的新的代理对象
            return constructor.newInstance(methodInterceptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generatorJavaFile(Class superClass) {
        String ln = "\r\n";
        StringBuilder sb = new StringBuilder();
        sb.append("package com.natsuki_kining.design.pattern.proxy.mycglib;"+ln);
        sb.append("import java.lang.reflect.Method;"+ln);
        sb.append("import java.lang.reflect.Constructor;"+ln);
        sb.append("import "+superClass.getName()+";"+ln);

        sb.append("public final class $Proxy0 extends "+superClass.getSimpleName()+" {"+ln);

        sb.append("     private MyMethodInterceptor h;"+ln);
        sb.append("     private Class superClass;"+ln);
        sb.append("     public $Proxy0(MyMethodInterceptor h) {"+ln);
        sb.append("         this.h = h;"+ln);
        sb.append("     }"+ln);

        superClass.getDeclaredMethods();
        Method[] methods = superClass.getDeclaredMethods();
        for (Method m : methods) {
            sb.append("     public final "+m.getReturnType().getName()+" "+m.getName()+"()  {"+ln);
            sb.append("         try {"+ln);
            sb.append("             Method m = Class.forName(\""+superClass.getName()+"\").getMethod(\""+m.getName()+"\");"+ln);
            sb.append("             Class<MyMethodProxy> mmCLazz = MyMethodProxy.class;"+ln);
            sb.append("             Constructor<MyMethodProxy> mmConstructor = mmCLazz.getConstructor(Method.class);"+ln);
            sb.append("             MyMethodProxy mm = mmConstructor.newInstance(m);"+ln);
            sb.append("             Constructor oc = this.getClass().getSuperclass().getConstructor();"+ln);
            sb.append("             Object o = oc.newInstance();"+ln);
            sb.append("             h.intercept(o, m, null,mm);"+ln);
            sb.append("         } catch (Throwable t) {"+ln);
            sb.append("             t.printStackTrace();"+ln);
            sb.append("         }"+ln);
            sb.append("     }"+ln);
        }
        sb.append("}");
        return sb.toString();
    }

    public Class getSuperClass() {
        return superClass;
    }

    public void setSuperClass(Class superClass) {
        this.superClass = superClass;
    }

    public MyMethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MyMethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public void setCallback(MyMethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}

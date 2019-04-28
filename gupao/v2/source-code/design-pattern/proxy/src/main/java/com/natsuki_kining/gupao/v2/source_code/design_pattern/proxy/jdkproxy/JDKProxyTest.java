package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.jdkproxy;
import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Person;
import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Work;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class JDKProxyTest {

    public static void main(String[] args) {
        try {
            Person person = (Person)new JDKWorkProxy().getInstance(new Work());
            person.findWork();

            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
            FileOutputStream fileOutputStream = new FileOutputStream("E://$Proxy.class");
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

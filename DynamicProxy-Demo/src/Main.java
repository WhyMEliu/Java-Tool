import dynamic.JdkProxyHello;
import object.Hello;
import object.IHello;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        IHello hello = (IHello) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{IHello.class},new JdkProxyHello(new Hello()));
        hello.helloWorld();
    }
}

package dynamic;

import object.Hello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxyHello implements InvocationHandler {

    private Hello hello;

    public JdkProxyHello(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = null;
        try {
            result = method.invoke(hello,args);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            System.out.println("after");
        }
        return result;
    }
}

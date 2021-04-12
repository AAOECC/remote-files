package pro.walden.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Lenovo lenovo = new Lenovo();

        //动态代理
        ComputerProduct proxy_lenovo = (ComputerProduct) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //判断方法名
                if (method.getName().equals("sale")) {
                    //增强传入参数
                    int money = (int) args[0];
                    money = (int) (money * 0.85);

                    //增强方法体
                    System.out.println("专车接送");
                    Object obj = method.invoke(lenovo,money);
                    System.out.println("送货上门");
                    //增强返回值
                    String str = (String)obj +"_鼠标";

                    return str;
                } else {
                    Object obj = method.invoke(lenovo,args);
                    return obj;
                }
            }
        });

        String computer = proxy_lenovo.sale(8000);
        System.out.println(computer);
    }
}

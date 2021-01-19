package reflect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTests {

    /**
     * 小型“框架”实现：不改变源码，通过更改配置文件，
     * 更改创建的类和执行的方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        
        //1.加载pro文件字节流
        //1.1加载路径
        Class rls = ReflectTests.class;
        ClassLoader clr =  rls.getClassLoader();
        //1.2加载字节流
        InputStream is =  clr.getResourceAsStream("pro.properties");

        //2.创建pro对象
        Properties pro = new Properties();
        pro.load(is);

        //3.取得类名和方法名
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3.通过反射生成类，调用方法
        //3.1创建对象实例
        Class aClass = Class.forName(className);
        Object obj = aClass.getConstructor().newInstance();
        //3.2调用方法
        Method aMethod = aClass.getMethod(methodName);
        aMethod.invoke(obj);

    }
}

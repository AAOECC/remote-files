package reflect;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {
    public static void main(String[] args)  throws Exception{
        Properties pro = new Properties();

        ClassLoader clr =  ReflectTest.class.getClassLoader();
        InputStream is = clr.getResourceAsStream("pro.properties");

        pro.load(is);
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        Class aClass = Class.forName(className);
        Object obj = aClass.getConstructor().newInstance();
        Method method = aClass.getDeclaredMethod(methodName);
        method.setAccessible(true);
        method.invoke(obj);

    }
}

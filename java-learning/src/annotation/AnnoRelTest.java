package annotation;


import java.lang.reflect.Method;

@Pro(className = "annotation.Worker", methodName = "working")
public class AnnoRelTest{

    public static void main(String[] args) throws Exception {

        //1.获取注解作用位置的字节码文件
        Class<AnnoRelTest> annoRelTestClass = AnnoRelTest.class;

        //2.获取注解对象
        Pro aPro = annoRelTestClass.getAnnotation(Pro.class);

        //3.解析取得注解内属性配置
        String className = aPro.className();
        String methodName = aPro.methodName();

        //4.通过反射调用
        Class cls = Class.forName(className);
        Object obj = cls.getConstructor().newInstance();
        Method mtd = cls.getMethod(methodName);
        mtd.invoke(obj);
    }
}

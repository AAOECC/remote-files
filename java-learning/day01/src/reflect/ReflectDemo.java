package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {

    public static void main(String[] args) {
        System.out.println("Methods: ");
        Method[] methods = Object.class.getDeclaredMethods();
        for(Method m : methods){
            System.out.println(m);
        }

        System.out.println("----------------");
        System.out.println("Fields: ");
        Field[] fields = Object.class.getDeclaredFields();
        for(Field f : fields){
            System.out.println(f);
        }
    }
}

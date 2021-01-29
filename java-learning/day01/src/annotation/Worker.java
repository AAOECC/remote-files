package annotation;


import org.junit.Test;

import java.lang.annotation.Target;

@MyAnno1("")
public class Worker {
    private String name;

    @MyAnno1("")
    @Deprecated
    public static void working(){
        //do something
        System.out.println("work is working");
    }


    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                '}';
    }

    @Test
    public void test(){
        Worker wr = new Worker();
        working();
    }
}

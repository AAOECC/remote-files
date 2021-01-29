package annotation.demo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 通过 @Check 注解确定要测试的方法
 */
public class TestCheck {

    public static void main(String[] args) throws IOException {
        //1.取得要测试类的class对象
        Calculator c = new Calculator();
        Class cal = c.getClass();
        //2.取得对象中的全部方法
        Method[] methods = cal.getMethods();

        int count = 0; //异常数量统计
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));
        //3.遍历方法，查找被注解的方法
        for(Method m : methods){
            //4.执行被注解的方法
            if(m.isAnnotationPresent(Check.class)){
                //5.捕获异常
                try {
                    m.invoke(c);
                } catch (Exception e) {
                    count++;
                    //6.异常信息写入文件bug.txt
                    bw.write(m.getName()+"方法出现异常：");
                    bw.newLine();
                    bw.write("异常名称： "+e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常类型： "+e.getCause().getMessage());
                    bw.newLine();
                    bw.write("--------------");
                    bw.newLine();
//                    System.out.println(e.getCause());
//                    System.out.println(e.getCause().getClass());
//                    System.out.println(e.getMessage());
                }
            }
        }
        bw.write("测试完成，出现 "+count+" 个异常。");
        bw.flush();
        bw.close();
        System.out.println("测试结束。");
    }
}

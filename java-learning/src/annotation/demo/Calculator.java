package annotation.demo;

public class Calculator {

    @Check
    public void add(){
        String n = null;
        n.toString();
        System.out.println("1 + 2 = "+(1+2));
    }

    @Check
    public void sub(){
        System.out.println("1 - 2 = "+(1-2));
    }

    @Check
    public void mul(){
        System.out.println("1 * 2 = "+(1*2));
    }

    @Check
    public void div(){
        System.out.println("1 / 0 = "+(1/0));
    }

    public String show(){
        return("show()...");
    }
}

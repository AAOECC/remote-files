package annotation.demo;

public class Calculator {

    @Check
    public String add(){
        return("1 + 2 = " + (1+2));
    }

    @Check
    public String sub(){
        return("1 - 2 = " + (1-2));
    }

    @Check
    public String mul(){
        return("1 * 2 = " + (1*2));
    }

    @Check
    public String div(){
        return("1 / 0 = " + (1/0));
    }

    public String show(){
        return("show()...");
    }
}

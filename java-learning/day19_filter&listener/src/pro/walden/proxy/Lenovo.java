package pro.walden.proxy;

public class Lenovo implements ComputerProduct {

    public String sale(int money){
        System.out.println("花费"+money+"购买了一台联想电脑...");

        return "联想电脑";
    }

    public void show(){
        System.out.println("展示电脑");
    }
}

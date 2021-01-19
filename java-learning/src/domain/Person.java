package domain;

public class Person{
    private String name;
    private int age;

    public String a;

    public Person(){

    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    
    public void eat(){
        System.out.println("eat...");
    }

    private void pri(){
        System.out.println("this is a private method.");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", a='" + a + '\'' +
                '}';
    }

    @Deprecated
    public int add(int a, int b){
        return a+b;
    }
    
}
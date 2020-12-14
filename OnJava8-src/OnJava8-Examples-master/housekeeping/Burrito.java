import sun.net.www.content.text.plain;

// housekeeping/Burrito.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class Burrito {
  Spiciness degree;

  public Burrito(Spiciness degree) {
    this.degree = degree;
  }

  public void describe(){
    System.out.print("This Burrito is: ");
    switch(this.degree){
      case MEDIUM:
      case MILD:
        System.out.println("a littlt hot.");
        break;
      case NOT:
        System.out.println("not spicy at all.");
        break;
      case FLAMING:
      case HOT:
      default:
        System.out.println("may be too hot.");
        break;
    }
  }

  public static void main(String[] args) {
    Burrito plain = new Burrito(Spiciness.NOT),
    greenChild = new Burrito(Spiciness.MEDIUM),
    jalapeno = new Burrito(Spiciness.HOT);

    plain.describe();
    greenChild.describe();
    jalapeno.describe();
  }
}
/*
 * Output: This burrito is not spicy at all. This burrito is a little hot. This
 * burrito is maybe too hot.
 */

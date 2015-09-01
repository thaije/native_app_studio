class Person {

   // Properties of the class...
   private String name;
   public int    age;
   private String sex;
    
   // Constructor of the class...
   public Person() {}

   public Person(String aName, int anAge, String aSex) {
      name = aName;
      age  = anAge;
      sex = aSex;
   }

   // Methods of the class...
   public void talk() {
      System.out.println("Hi, my name's " + name);
      System.out.println("my age is " + age);
      System.out.println("and I am a " + sex);
      commentAboutAge();
      System.out.println();
   }
   public void commentAboutAge() {
      if (age < 5) {
         System.out.println("baby");
      }
      if (age == 5) {
         System.out.println("time to start school");
      }
      if (age > 60) {
         System.out.println("Grandpa");
      }
   }

   public void giveKnighthood() {
      name = "Sir " + name;
   }

   public void growOlder(int increment) {
      age = age + increment;
   }

   public void growOlder() {
      age++;
   }



}

class PersonTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      //Person ls = new Person("Luke Skywalker",34, "male");
      //Person wp = new Person("Winston Peters",48, "male");    

      Person ls = new Person();
      Person wp = new Person();

      ls.talk();
      ls.growOlder(10);
      //ls.commentAboutAge();
      System.out.println("Luke Age directly from main:" + ls.age + "\n");

      wp.talk();
      //wp.commentAboutAge();
      wp.growOlder();
      wp.giveKnighthood();
      wp.talk();

   }

}


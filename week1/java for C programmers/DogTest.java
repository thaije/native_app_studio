class Flea {
   
   // Properties of the class...
   private String name;
   
   // Constructor of the class...
   public Flea(String aName) {
      name = aName;
   }

   // Methods of the class...
   public String toString() {
      return "I am a flea called " + name;
   }

}

class Dog {

   // Properties of the class...
   private String name;
   private int    age;
   public Flea   dogsFlea;

   // Constructor of the class...
   public Dog(String aName, int anAge, Flea aFlea) {
      name     = aName;
      age      = anAge;
      dogsFlea = aFlea;
   }

   public String toString() {
      return "I am a dog of " + age + " years old called " 
      + name + " with a flea: \n" + dogsFlea;
   }

}

class DogOwner {

   private String name;
   private int salary;
   public Dog ownersDog;

   public DogOwner(String name, int salary, Dog ownersDog) {
      this.name = name;
      this.salary = salary;
      this.ownersDog = ownersDog;
   }

   public String toString() {
      return "I am a dogOwner, name:" + name + " salary:"+ salary + 
      "Dog:\n" + ownersDog;
   }

}

class DogTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {
      Flea pop = new Flea("Pop");
      Flea squak = new Flea("Squak");
      Flea zip = new Flea("Zip");

      Dog rex = new Dog("Rex",2,pop);
      Dog jimbo = new Dog("Jimbo",3,squak);
      Dog fido = new Dog("Fido",5,zip);

      DogOwner angus = new DogOwner("Angus", 10000, rex);
      DogOwner brian = new DogOwner("Brian", 15000, jimbo);
      DogOwner charles = new DogOwner("charles", 16000, fido);

      System.out.println(angus.toString());
      System.out.println(brian.toString());
      System.out.println(charles.toString());
      System.out.println();

      System.out.println(angus.ownersDog.dogsFlea.toString());

   }
}


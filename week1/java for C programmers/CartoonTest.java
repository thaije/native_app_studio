class FredFlintstone {

   // Properties of the class...
   static String name            = "Fred Flintstone";
   static String favouriteColour = "blue";
   static int    favouriteNumber = 42;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class WilmaFlintstone {

   // Properties of the class...
   static String name            = "Wilma Flintstone";
   static String favouriteColour = "red";
   static int    favouriteNumber = 63;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class BarneyRubble {

   // Properties of the class...
   static String name            = "Barney Rubble";
   static String favouriteColour = "yellow";
   static int    favouriteNumber = 2;

   // Methods of the class...
   static void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}

class CartoonTest { 

   // The main method is the point of entry into the program...
   public static void main(String[] args) {
     /* FredFlintstone f = new FredFlintstone();
      f.displayMe();


      BarneyRubble b = new BarneyRubble();
      System.out.println("Barney Rubble's favourite colour:" + BarneyRubble.favouriteColour);
      */
      CartoonCharacter f = new CartoonCharacter("Fred Flintstone","blue",42);
      CartoonCharacter w = new CartoonCharacter("Wilma Flintstone","red",63);
      CartoonCharacter b = new CartoonCharacter("Barney Rubble","yellow",2);

      f.displayMe();
      w.displayMe();
      b.displayMe();

      System.out.println("Favourite color barney:" + b.favouriteColour);
      System.out.println("Count characters:" + CartoonCharacter.count);
      System.out.println("PI is:" + Math.PI);
   }
}


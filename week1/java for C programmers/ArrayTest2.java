class Human {

   // Properties of the class...
   String name;

   // Constructor of the class...
   public Human(String aName) {
      name = aName;
   }

   // Methods of the class...
   public String toString() {
      return "I am a human and my name is " + name;
   }
}

class ArrayTest2 {

   public static void main(String[] args) {
      ArrayTest2 x = new ArrayTest2();
      x.doStuff();
   }

   public void printArray(Human[] array) {
      for(int i =0; i<array.length;i++){
         System.out.println(array[i].toString());
      }
   }

   public void doStuff() {

      // insert your code here:
      Human angus = new Human("Angus");
      Human brian = new Human("Brian");
      Human charles = new Human("Charles");
        
      Human[] threesome = new Human[]{angus,brian,charles};

      printArray(threesome);

   }
}

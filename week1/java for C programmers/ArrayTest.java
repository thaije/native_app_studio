class ArrayTest {

   public static void main(String[] args) {
      ArrayTest x = new ArrayTest();
      x.doStuff();
   }

   public void doStuff() {

      double[] nums = new double[10];

      // sets the values of the fred array
      for (int j=1; j<10; j++) {
         nums[j] = j*0.1 + 1;
      }

      ArrayPrint.printArray(nums);

      // creates the array fred of ten integers.
      int[] fred = new int[10];

      // sets the values of the fred array
      for (int i=0; i<10; i++) {
         fred[i] = i;
      }

      // prints the values of the fred array
      ArrayPrint.printArray(fred);

      // insert your code for the nums array here:

   }

   public void printArray(double[] array) {
      for (int i=0; i<10; i++) {
         array[i] = i;
      }
   }

   public void printArray(int[] array) {
      for (int i=0; i<10; i++) {
         array[i] = i;
      }
   }


}

public class BRTest {

	private int calls = 0;
	private int successfulCalls = 0;
	private int totalReturned = 0;
	private double[] excepCounts = new double[5];

	public void callIt() {
		calls++;

		try {
			// try a random value and increment ints if no exception
			int i = BadRandom.randVal();
			calls++;
			successfulCalls++;
			totalReturned += i;

		} catch(Exception e) {
			System.out.println(e);
			// check for excpetion, and add to array
			if(e instanceof ArithmeticException) 
				excepCounts[0]++;

			if(e instanceof NullPointerException)
				excepCounts[1]++;

			if(e instanceof ArrayIndexOutOfBoundsException)
				excepCounts[2]++;

			if(e instanceof ClassCastException)
				excepCounts[3]++;

			if(e instanceof NegativeArraySizeException)
				excepCounts[4]++;

		}
	}

	public void resetCounts() {
		calls = successfulCalls = totalReturned = 0;
		excepCounts = new double[5];
	}

	public void nRandInts(int n) {
		while(successfulCalls < n) {
			callIt();
		}
	}

	public void writeData() {
		System.out.println("Calls:" + calls);
		System.out.println("Succesful Calls:" + successfulCalls);
		System.out.println("Total value:" + totalReturned);
		System.out.println("Percentage Arithmetic Exceptions:" + (excepCounts[0]/calls)*100);
		System.out.println("Percentage Null Pointer Exceptions::" + (excepCounts[1]/calls)*100);
		System.out.println("Percentage Array Index Exceptions:" + (excepCounts[2]/calls)*100);
		System.out.println("Percentage Class Cast Exceptions:" + (excepCounts[3]/calls)*100);
		System.out.println("Percentage Negative Array Exceptions:" + (excepCounts[4]/calls)*100);
		System.out.println("Percentage of succesful Calls:" + (successfulCalls/(calls*1.0))*100);
	}

	public static void main (String[] args) {

		BRTest me = new BRTest();
		me.resetCounts();
		me.nRandInts(30);
		me.writeData();



	}

}
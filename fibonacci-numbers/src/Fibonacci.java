public class Fibonacci {
	public static int fib(int n) {
		// computes fibonacci function for any non-negative integer n
		if (n <= 1)
			return n;
		return fib(n - 1) + fib(n - 2);
	}

	/**
	 * Computes the Fibonacci function iteratively for any non-negative integer
	 * <code>n</code>
	 */
	public static int fastFib(int n) {
		//TODO: write your code here: 
		return 0; 
	}

	/**
	 * Computes the Fibonacci function for any non-negative integer
	 * <code>n</code>
	 */
	public static long longFastFib(long n) {
		//TODO: write your code here.
		return 0;
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Needs an integer parameter");
			System.exit(1);
		}

		int n = Integer.parseInt(args[0]);
		System.out.println(fib(n));
	}
}

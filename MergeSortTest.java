import java.util.Random;
/**
 * MergeSortTest is a class to sort integers from Merge Sort Algorithm
 * Merge Sort runs in O(n log n) time complexity
 * @author Rajesh
 */
public class MergeSortTest {
	// creating some variables
	static long count = 1;
	static int MAXNUM = 27;  // for 2^4, 2^5, ... 2^27
	static Random rand = new Random();
	
	/**
	 * Create an array with random numbers
	 * @param n as number of elements
	 * @return integer array
	 */
	public static int[] randomArray(int n) {
		int[] myArray = new int[n];
		for (int i = 0; i < n; i++)
			myArray[i] = rand.nextInt(n);
		return myArray;
	}
	
	/**
	 * mergeSort for sorting an array
	 * @param A array
	 * @param n length of an array
	 * @return count number of operations to sort an array
	 */
	public static long mergeSort(int[] A, int n) {
		if (n <= 1)
			return count;
		int arrlen = n;
		int[] rhs = new int[arrlen / 2];
		int rhslen = rhs.length;
		int[] lhs = new int[arrlen - rhslen];
		int lhslen = lhs.length;
		count += 5;
		System.arraycopy(A, 0, rhs, 0, rhslen);
		count += rhslen;
		System.arraycopy(A, rhslen, lhs, 0, lhslen);
		count += lhslen - rhslen;
		// recursive
		mergeSort(rhs, rhslen);
		count++;
		mergeSort(lhs, lhslen);
		count++;
		merge(rhs, lhs, A);
		count++;
		return count;
	}

	/**
	 * helper function for sorting array 
	 */
	public static void merge(int[] rhs, int[] lhs, int[] res) {
		int rhsindx = 0;
		int lhsindx = 0;
		int mrgindx = 0;
		int rhslen = rhs.length;
		int lhslen = lhs.length;
		count += 5;
		while (rhsindx < rhslen && lhsindx < lhslen) {
			if (rhs[rhsindx] < lhs[lhsindx]) {
				res[mrgindx] = rhs[rhsindx];
				rhsindx++;
				count += 2;
			} else {
				res[mrgindx] = lhs[lhsindx];
				lhsindx++;
				count += 2;
			}
			mrgindx++;
			count++;
		}
		System.arraycopy(rhs, rhsindx, res, mrgindx, rhslen - rhsindx);
		System.arraycopy(lhs, lhsindx, res, mrgindx, lhslen - lhsindx);
	}
	
	/**
	 * running mergeSort 2^4, 2^5, ...2^27
	 */
	public static void main(String[] args) {
		long averageCount = 0;
		System.out.println(" n  Average Count");
		System.out.println("===============");
		try {
			for (int i = 4; i <= MAXNUM; i++) {
				averageCount = 0;
				int n = (int) Math.pow(2, i);
				for (int j = 0; j < 5; j++) {
					averageCount += mergeSort(randomArray(n), n);
				}
				averageCount = averageCount / 5;
				// printing output for graphing 
				System.out.printf("%d %d%n", i, averageCount);
			}
		} catch (Exception e) {
			System.out.println("Some error occurred!");
		}
	}
}
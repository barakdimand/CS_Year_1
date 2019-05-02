import java.util.Random;

import Plotter.Plotter;



public class Sorting {
	final static int BUBBLE_VS_QUICK_LENGTH = 12;
	final static int MERGE_VS_QUICK_LENGTH = 15;
	final static int BUBBLE_VS_QUICK_SORTED_LENGTH = 12;
	final static int ARBITRARY_VS_MEDIAN_LENGTH = 16;
	final static double T = 600.0;

	/**
	 * Sorts a given array using the quick sort algorithm.
	 * At each stage the pivot is chosen to be the rightmost element of the subarray.
	 * 
	 * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void quickSortArbitraryPivot(double[] arr) {
		QuickSort(arr, 0, arr.length - 1);
	}

	/**
	 * The main method of the QuickSort algorithm.
	 *
	 * @param arr - the array to be sorted
	 * @param p - The first element in the current subarray
	 * @param r - The last element in the current subarray
	 */
	public static void QuickSort(double[] arr, int p, int r) {
		if (p < r) {
			int q = Partition(arr, p, r);
			QuickSort(arr, p, q - 1);
			QuickSort(arr, q + 1, r);
		}
	}

	/**
	 * Divides the subarray into two subarrays with elements larger than the pivot on
	 * the right side and elements smaller than the pivot on the left side.
	 *
	 * @param arr - the array to be sorted
	 * @param p - The first element in the current subarray
	 * @param r - The last element in the current subarray
	 */
	public static int Partition(double[] arr, int p, int r) {
		int i = p;
		int j = r - 1;
		while (true) {
			while (arr[i] < arr[r] && i < r) {
				i++;
			}
			while (arr[j] > arr[r] && j > p) {
				j--;
			}
			if (i < j) {
				double temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			} else {
				double temp = arr[r];
				arr[r] = arr[i];
				arr[i] = temp;
				return i;
			}
		}
	}
	
	/**
	 * Sorts a given array using the quick sort algorithm.
	 * At each stage the pivot is chosen in the following way:
	 * Choose 3 random elements from the array, the pivot is the median of the 3 elements.
	 * 
	 * Should run in average complexity of O(nlog(n)), and worst case complexity of O(n^2)
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void quickSortMedianPivot(double[] arr){
		QuickSortMedian(arr, 0, arr.length - 1);
	}

	/**
	 * The main method of the QuickSort with median algorithm.
	 *
	 * @param arr - the array to be sorted
	 * @param p - The first element in the current subarray
	 * @param r - The last element in the current subarray
	 */
	public static void QuickSortMedian(double[] arr, int p, int r) {
		if (p < r) {
			findMedianPivot(arr, p, r);
			int q = Partition(arr, p, r);
			QuickSortMedian(arr, p, q - 1);
			QuickSortMedian(arr, q + 1, r);
		}
	}

	/**
	 * Finding the median of three randomly-chosen elements of the subarray.
	 *
	 * @param arr - the array to be sorted
	 * @param p - The first element in the current subarray
	 * @param r - The last element in the current subarray
	 */
	public static void findMedianPivot (double[] arr, int p, int r) {

		int Index1 = (int)(Math.random() * (r - p) + p);;
		int Index2 = (int)(Math.random() * (r - p) + p);;
		int Index3 = (int)(Math.random() * (r - p) + p);;
		int medianIndex;

		double median = Math.max(Math.min(arr[Index1], arr[Index2]),
				Math.min(Math.max(arr[Index1], arr[Index2]), arr[Index3]));
		if (median == arr[Index1]) {
			medianIndex = Index1;
		} else if (median == arr[Index2]) {
			medianIndex = Index2;
		} else {
			medianIndex = Index3;
		}

		double temp = arr[r];
		arr[r] = median;
		arr[medianIndex] = temp;
	}
	
	/**
	 * Sorts a given array using the merge sort algorithm.
	 * 
	 * Should run in complexity O(nlog(n)) in the worst case.
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void mergeSort(double[] arr){
		mainMergeSort(arr, 0, arr.length - 1);
	}

	/**
	 * The main method of the MergeSort algorithm.
	 *
	 * @param arr - the array to be sorted
	 * @param p - The first element in the current subarray
	 * @param r - The last element in the current subarray
	 */
	public static void mainMergeSort(double[] arr, int p, int r) {
		if (p < r) {
			int q = (r + p) / 2;
			mainMergeSort(arr, p, q);
			mainMergeSort(arr, q + 1, r);
			Merge(arr, p, r, q);
		}
	}

	/**
	 * Merging two sorted subarrays into one sorted array.
	 *
	 * @param arr - the array to be sorted
	 * @param p - The first element in the current subarray
	 * @param r - The last element in the current subarray
	 * @param q - The middle element in the current subarray
	 */
	public static void Merge(double[] arr, int p, int r, int q) {
		double[] left = new double[q - p + 2];
		double[] right = new double[r - q + 1];
		System.arraycopy(arr, p, left, 0, left.length - 1);
		System.arraycopy(arr, q + 1, right, 0, right.length - 1);
		left[left.length - 1] = Integer.MAX_VALUE;
		right[right.length - 1] = Integer.MAX_VALUE;

		int j = 0;
		int k = 0;
		for (int i = p; i <= r; i++) {
			if (left[j] < right[k]) {
				arr[i] = left[j];
				j++;
			} else {
				arr[i] = right[k];
				k++;
			}
		}
	}


	/**
	 * Sorts a given array using bubble sort.
	 * If at any time the algorithm recognizes no more inversions are needed it should stop.
	 * 
	 * The algorithm should run in complexity O(n^2) in the worst case.
	 * The algorithm should run in complexity O(n) in the best case.
	 * 
	 * @param arr - the array to be sorted
	 */
	public static void bubbleSort(double[] arr){
		boolean inverted = false;
		for (int i = arr.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j + 1]) {
					double temp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = temp;
					inverted = true;
				}
			}
			if (!inverted) {
				break;
			}
			inverted = false;
		}
	}
	
	public static void main(String[] args) {
		bubbleVsQuick();
		mergeVsQuick();
		bubbleVsQuickOnSortedArray();
		arbitraryPivotVsMedianPivot();
	}
	
	/**
	 * Compares the selection sort algorithm against quick sort on random arrays
	 */
	public static void bubbleVsQuick(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < BUBBLE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumSelection = 0;
			for(int k = 0; k < T; k++){
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumSelection += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumSelection/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "bubble sort on random array", bubbleTimes);
	}
	
	/**
	 * Compares the merge sort algorithm against quick sort on random arrays
	 */
	public static void mergeVsQuick(){
		double[] quickTimes = new double[MERGE_VS_QUICK_LENGTH];
		double[] mergeTimes = new double[MERGE_VS_QUICK_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < MERGE_VS_QUICK_LENGTH; i++) {
			long sumQuick = 0;
			long sumMerge = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				mergeSort(b);
				endTime = System.currentTimeMillis();
				sumMerge += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			mergeTimes[i] = sumMerge/T;
		}
		Plotter.plot("quick sort on random array", quickTimes, "merge sort on random array", mergeTimes);
	}

	/**
	 * Compares the merge sort algorithm against quick sort on pre-sorted arrays
	 */
	public static void bubbleVsQuickOnSortedArray(){
		double[] quickTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		double[] bubbleTimes = new double[BUBBLE_VS_QUICK_SORTED_LENGTH];
		long startTime, endTime;
		for (int i = 0; i < BUBBLE_VS_QUICK_SORTED_LENGTH; i++) {
			long sumQuick = 0;
			long sumBubble = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = j;
					b[j] = j;
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumQuick += endTime - startTime;
				startTime = System.currentTimeMillis();
				bubbleSort(b);
				endTime = System.currentTimeMillis();
				sumBubble += endTime - startTime;
			}
			quickTimes[i] = sumQuick/T;
			bubbleTimes[i] = sumBubble/T;
		}
		Plotter.plot("quick sort on sorted array", quickTimes, "bubble sort on sorted array", bubbleTimes);
	}

	/**
	 * Compares the quick sort algorithm once with a choice of an arbitrary pivot and once with a choice of a median pivot
	 */
	public static void arbitraryPivotVsMedianPivot(){
		double[] arbitraryTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
		double[] medianTimes = new double[ARBITRARY_VS_MEDIAN_LENGTH];
		long startTime, endTime;
		Random r = new Random();
		for (int i = 0; i < ARBITRARY_VS_MEDIAN_LENGTH; i++) {
			long sumArbitrary = 0;
			long sumMedian = 0;
			for (int k = 0; k < T; k++) {
				int size = (int)Math.pow(2, i);
				double[] a = new double[size];
				double[] b = new double[size];
				for (int j = 0; j < a.length; j++) {
					a[j] = r.nextGaussian() * 5000;
					b[j] = a[j];
				}
				startTime = System.currentTimeMillis();
				quickSortArbitraryPivot(a);
				endTime = System.currentTimeMillis();
				sumArbitrary += endTime - startTime;
				startTime = System.currentTimeMillis();
				quickSortMedianPivot(b);
				endTime = System.currentTimeMillis();
				sumMedian += endTime - startTime;
			}
			arbitraryTimes[i] = sumArbitrary/T;
			medianTimes[i] = sumMedian/T;
		}
		Plotter.plot("quick sort with an arbitrary pivot", arbitraryTimes, "quick sort with a median pivot", medianTimes);
	}
}

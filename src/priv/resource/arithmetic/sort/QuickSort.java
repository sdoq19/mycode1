package priv.resource.arithmetic.sort;

/**
 * 快速排序，平均复杂度O(nlog2n)
 * 思想：分治排序
 * 详解：找出一个元素（随便一个元素）作为基准（pivot），让后对数组进行分区操作，使基准左边的元素值不大于基准值，
 * 基准右边的元素值不小于基准值。然后进行递归操作，将基准左右两边分别作为新的元素组进行基准定位，最终得到排序后
 * 的元素序列。
 * 
 * @author zhuzh
 */
public class QuickSort {
	public static void main(String[] args) {
		System.out.println("Quick Sort Test!");
		int[] v = {
				(int)(Math.random() * 1000), 
				(int)(Math.random() * 1000),
				(int)(Math.random() * 1000),
				(int)(Math.random() * 1000),
				(int)(Math.random() * 1000),
				(int)(Math.random() * 1000),
				(int)(Math.random() * 1000),
				(int)(Math.random() * 1000),
				(int)(Math.random() * 1000)
			};
		
		for (int a:v) {
			System.out.print(a + " ");
		}
		quickSort(v, 0, v.length-1);
		System.out.println();
		for (int a:v) {
			System.out.print(a + " ");
		}
	}

	public static void quickSort(int[] v, int left, int right) {
		if (left < right) {
			int pivot = v[left];
			int low = left;
			int high = right;
			while (low < high) {
				while (low < high && v[high] > pivot) {
					high--;
				}
				v[low] = v[high];
				while (low < high && v[low] < pivot) {
					low++;
				}
				v[high] = v[low];
			}
			v[low] = pivot;
			quickSort(v, left, low -1);
			quickSort(v, low +1, right);
		}
	}
}

import java.util.Arrays;
import java.util.Random;

public class Main {

    private static int[] mergeSort(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int length = nums.length;
        int halfLength = length / 2;

        int[] left = new int[halfLength];
        int[] right = new int[length % 2 == 0 ? halfLength : halfLength + 1];

        for (int i = 0; i < length; i++) {
            if (i < halfLength) {
                left[i] = nums[i];
            } else {
                right[i - halfLength] = nums[i];
            }
        }
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private static int[] removeFirst(int[] nums) {
        var length = nums.length;
        int[] newNums = new int[length - 1];
        for (int i = 1; i < nums.length; i++) {
            newNums[i - 1] = nums[i];
        }
        return newNums;
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i] = left[0];
                left = removeFirst(left);
            } else {
                result[i] = right[0];
                right = removeFirst(right);
            }
            i++;
        }

        while (left.length > 0) {
            result[i++] = left[0];
            left = removeFirst(left);
        }
        while (right.length > 0) {
            result[i++] = right[0];
            right = removeFirst(right);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new Random().ints(100_000, 0, 10).toArray();
        // System.out.println("Before sort:");
        // Arrays.stream(nums).forEach(i -> System.out.print(i + " "));

        int[] sortedNums = mergeSort(nums);
        // System.out.println("\nAfter Merge sort:");
        // Arrays.stream(sortedNums).forEach(i -> System.out.print(i + " "));
    }

}

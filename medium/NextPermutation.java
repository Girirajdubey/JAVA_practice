import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.

 
Example 1:

Input: nums = [1,2,3]
Output: [1,3,2]
Example 2:

Input: nums = [3,2,1]
Output: [1,2,3]
Example 3:

Input: nums = [1,1,5]
Output: [1,5,1]
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100
*/

class Arrays_NextPermutation {

	private static void getNextPermutation(int[] nums, int n) {

		int i = n - 2;	/* Grab the index of the 2nd to last element in the array */

		/* Walk backwards. Keep walking until we reach the point right before the decreasing sequence begins.
		When this while loop ends that is where i will stand */

		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}

		/* If i is not the first element we have more work to do.
		If i IS the first element we just skip down to reverse the whole array since the WHOLE array would be decreasing
		meaning we are on our last permutation */

		if (i >= 0) {

			/* Start a pointer at the end of the array, we want to search for
			the smallest item THAT IS GREATER THAN THE ELEMENT AT i

        	Why? Why Why Why. The reason why is that we want to know the NEXT element that is to be planted where i is sitting.
        	THIS WILL ROOT THE NEXT PERMUTATION and represents the smallest change
        	we can make thus ensuring we have exactly the next permutation
      		*/

			int j = n - 1;
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}

			/* We swap those elements.

        	Now all that is left is to reverse the decreasing section (it is already sorted in reverse order)
        	to restore it to the first positionality it would be with the new value rooted at i	*/
			swap(nums, i, j);
		}

		/* Perform the reversal on the decreasing section now. We pass in i + 1 since i sits RIGHT BEFORE the
		decreasing section that is on its final permutation */
		reverse(nums, i + 1, n - 1);
	}

	/* Reverses from 'start' to the end of the array 'nums' */
	private static void reverse(int[] nums, int left, int right) {
		while (left < right) {
			swap(nums, left, right);
			left++;
			right--;
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while(t-- > 0) {
			int n = Integer.parseInt(br.readLine().trim());
			String[] input = br.readLine().trim().split("\\s+");
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(input[i]);
			}

			getNextPermutation(nums, n);

			print(nums, n);
		}
	}

	private static void print(int[] nums, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(nums[i]).append(" ");
		}
		System.out.println(sb);
		sb.setLength(0);
	}
}

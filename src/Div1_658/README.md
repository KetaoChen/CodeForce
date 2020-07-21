# Codeforce Div1 658 (Rating 395)

##### A_Greedy: (O(n))
1. We need to finish this in 2n times. We can observe that we need to match the last digit in two steps. 
2. In every operation, the prefix does invert and reverse. If the s[0] == p[l - 1], we need to invert the first digit by itself, then invert and reverse to the end.
3. How to do it in place or not actually do the operation? Use two pointer to track the index in S.

##### B_Dp: (O(n2))
1. Merge the unsorted array. We need to find the first index that is larger than current value, and divide the array into several segments.
2. Then this problem is a knapsack problem.

##### C_Greedy_Sort: O(nlogn) (there is better solution O(n))
1. Analyze the problem: whether we can construct an array, with x positions that the value is the same as the array. The totally number of in the array is y.
2. When there is no solution? According to pigeon hole principle, when (l - x - maxCount) < (y - x + 1) / 2, there is no solution.
3. How to reindex the array so that in the rest of the array, there is no position the value matches?
4. Starting from the high freq number, when there is only one number available but this position matches, we can also put the missing number.

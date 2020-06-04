# Codeforce Div2 647 (Rating 970)

## What I learned (WA)
Take care to use Map, because of large constant time complexity. T T, What a pity for TLE of problemE.

##### A_Greedy:
Find the multiplication of two numbers, and check whether it can be done by doing *2, *4 and *8.

##### B_Brute_Force:  
Because of n = 1024, it is ok to do it in n^2. Iterate each possible value to check the XOR array.

##### C_Dp: 
1. I find the pattern by printing out the first 300 numbers. 
2. dp[1>>i] = 2 * dp[1>>(i+1)] + 1
3. Get the binary representation of the number and sum up all the difference. 

##### D_Topological: 
1. Since the smallest number that does not have will be filled in the node.
2. Using topological sorting, starting from target value = 1.
3. At the same time, when doing bfs, we need to check that the no neighbors' value should be the same.
4. **Important Here. The condition to offer the next node in the queue is when all the neighbors cover all the values smaller than current value.**

##### E_Greedy_FastPow:
1. If they are regular values, it is a backpack problem.
2. Since they are exponential values, a larger exponent is much larger than a smaller exponent number.
3. Starting from the largest value, each time when we move, we will try to cancel with a smaller number. 
4. Instead of TreeMap to do the sort and get & retrieve values, sorting the array and starting from the last value can AC. 





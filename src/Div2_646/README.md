# Codeforce Div2 646 (Rating 1026)

## What I learned (WA)
1. Instead of directly thinking of O(1) algorithms to get the result, starting from O(n) method to find the pattern.
2. Should always thinking about the base case and corner case (when input is 0 or invalid).
3. When getting the max or min of an array several times, and we also need to change the values in the array. We usually need to make a copy of original array.

##### A_Odd_Even_CornerCase:
1. The number of odd numbers should be odd to make the sum odd.
2. Thinking the odd_num or even_num is zero.

##### B_Dp_Sequence:  
1. For each character as the last digit, iterate its value, to be 0 or 1.
2. At the same time, iterate its state, it can be changed or not. 

##### C_Graph_Odd_Even_CornerCase: 
1. Corner Case: 
    1. This is the only node in the graph.
    2. The target node is a leaf.
2. Otherwise, Count the number of nodes in the graph. Get the result by the number of total nodes.

##### E_Graph_PostOrder: 
1. Base case: if we can not shuffle the tree, return -1.
2. Solve it recursively, pass the cost into subproblem, and use the minCost as the cost.
3. The result type should include the cost, num_zero, num_one. 
4. When calculating cost, we get the min(num_zero, num_one), and do the minus, make sure record the min dependently!




​	

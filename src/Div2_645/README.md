# Codeforce Div2 645 (Rating 1540)

##### A_Easy.

##### B_Greedy_Sort: find the last index that has lower value than index.

##### C_Math_Observation:

1. Figure out that for each cell, the number below it is one greater than the number on the right.
2. Need to find that number of different sum if the max path - min path.
3. How to calculate the path difference?
   1. We can pair the numbers one by one, and add all the difference.  Instead pair them linear, we do it diagonally, because the difference between the left bottom number and right top number is the width of the row. 
   2. Another way to do it is to bend the min path once at a time, and we can find that we can bend the path x*y times, which is the number of cells which are not in the min path. (This is also the tutorial method)

##### D_Prefix_SlideWindow:

1. This is a rotate array, so we double the length and copy the array once.
2. The maximum value must end with some number, so we can use a slide window to track the right end of the window.
3. We need to make sure the sum of values in the window is smaller than K, so I create an extra array to record the value that we have used on the left side.





​	

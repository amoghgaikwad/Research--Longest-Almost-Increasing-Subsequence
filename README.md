# Research--Longest-Almost-Increasing-Subsequence



Almost Increasing Subsequence

This project implements the longest almost increasing subsequence with regard to the algorithm given by Amr Elmasry. The program implemented uses Red – Black trees to implement the LaIS algorithm. The table -2 given in the paper is produced as the output of the code. The sample output is as shown below;

Sample Output:

Input:  612 187 626 691 734 878 552 401 182 291 555 505 409 53 733 57 494 8 323 170

The LaIS as per Table 2:  733 734 691 626 187

p[i] values : 0 1 1 2 3 4 1 1 8 8 9 9 9 13 12 13 12 17 15 15
*******************************************


Input:  902 814 850 178 664 816 827 936 280 523 910 733 568 206 524 58 622 443 400 802

The LaIS as per Table 2:  802 622 524 523 280 178

p[i] values : 0 1 1 3 3 4 5 6 3 8 6 9 9 3 9 15 14 13 13 16
*******************************************

Now, the code is run for n=1000 and for 1000 runs of the algorithm we get an average run time by calculating the number of times each loop is executed in the whole program and then dividing the value by a constant factor. These values are seen while the code is executed, in every iteration.

We can now relate the average run time as a function of k. The run-time of the almost increasing sub sequence algorithm is given to be of nlogk. 

The graph shown below depicts the average run-time (no of iterations/constant value) as a function of k.
 
Graph -1 : Selected Values (Normalised)


 
Graph -2 : For all the 1000 Runs
Testing:

	The code is written in java and complied using the javac compiler on my linux system.
	While Executing please make sure that the java file is in the current directory.
	Use the code- “javac RedblackTree.java” to compile the file.
	Execute the program by the command- “java RedBlackTree” 
This will execute the code and display the required output.

Discussion:

Next, we plot the graph for run time analysis by plotting Average (no of iterations/constant value) on y-axis and “k” i.e. the length of the longest almost increasing subsequence so found for a particular iteration on x-axis.
Here is the plot:

Conclusion:

The Red-Black tree primary operations such as insert, delete, fixTree, etc run for logk time, where k is the length of the longest almost increasing subsequence, i.e. the depth the tree. Since, the size of the input array is “n”, so the code runs for the nlogk time. Hence, it is empirically evident that the implementation LaIS algorithm runs for nlogk time.

References:

	“The Longest Almost-Increasing Subsequence” by Amr Elmasry (Info Proc Letters, vol 110,2010, pp. 655-658).
	Introduction to Algorithms: Edition 3 (Thomas H. Cormen Charles E. Leiserson Ronald L. RivestClifford Stein, July 31, 2009
	The RedBlackTree (http://www.codebytes.in/2014/10/red-black-tree-java-implementation.html)


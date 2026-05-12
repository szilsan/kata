https://www.codewars.com/kata/59f7679d3640cef20e0000a0/java

In this kata, you'll have to write a function cartesianProduct(int[][]) -> Set<int[]> that calculates the Cartesian product of a set of sets of integers. What this means is that you'll be producing all the possible sequences consisting of an element from the first set followed by an element from the second set and so on. Here's the catch: you only have one semicolon!

As an example, suppose we have these sets:

A := { 1, 2 }
B := { 3, 4 }
C := { 5, 6, 7 }
The Cartesian product A * B * C would be the following set of lists:

{[ 1, 3, 5 ]
 [ 1, 3, 6 ]
 [ 1, 3, 7 ]
 [ 1, 4, 5 ]
 [ 1, 4, 6 ]
 [ 1, 4, 7 ]
 [ 2, 3, 5 ]
 [ 2, 3, 6 ]
 [ 2, 3, 7 ]
 [ 2, 4, 5 ]
 [ 2, 4, 6 ]
 [ 2, 4, 7 ]}
Note that the set may have duplicate elements, but this is handled by Set already since two arrays with equivalent elements are considered different objects.

The int[][] you receive is the set of sets from which you'll have to draw elements. For example, you might be passed the following for the above example:

new int[][] {
  new int[] {1, 2},
  new int[] {3, 4},
  new int[] {5, 6, 7}
}
The Set<int[]> you return is the set of sequences produced by the Cartesian product. The order of the set doesn't matter (obviously, since Sets are unordered anyways), but the order of the sequences do; an element in the sequence must be at the same array index as the set from which it is drawn.

Beware! Your algorithm will be tested for some fairly large sets!

Good luck.
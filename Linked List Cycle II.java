/*
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

 

Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:


Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:


Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.

Solution:
Approach 1: Hash Table
Intuition

If we keep track of the nodes that we've seen already in a Set, we can traverse the list and return the first duplicate node.

Algorithm

First, we allocate a Set to store ListNode references. Then, we traverse the list, checking visited for containment of the current node. If the node has already been seen, then it is necessarily the entrance to the cycle. If any other node were the entrance to the cycle, then we would have already returned that node instead. Otherwise, the if condition will never be satisfied, and our function will return null.

The algorithm necessarily terminates for any list with a finite number of nodes, as the domain of input lists can be divided into two categories: cyclic and acyclic lists. An acyclic list resembles a null-terminated chain of nodes, while a cyclic list can be thought of as an acyclic list with the final null replaced by a reference to some previous node. If the while loop terminates, we return null, as we have traversed the entire list without encountering a duplicate reference. In this case, the list is acyclic. For a cyclic list, the while loop will never terminate, but at some point the if condition will be satisfied and cause the function to return.


Complexity Analysis

Time complexity : O(n)O(n)

For both cyclic and acyclic inputs, the algorithm must visit each node exactly once. This is transparently obvious for acyclic lists because the nnth node points to null, causing the loop to terminate. For cyclic lists, the if condition will cause the function to return after visiting the nnth node, as it points to some node that is already in visited. In both cases, the number of nodes visited is exactly nn, so the runtime is linear in the number of nodes.

Space complexity : O(n)O(n)

For both cyclic and acyclic inputs, we will need to insert each node into the Set once. The only difference between the two cases is whether we discover that the "last" node points to null or a previously-visited node. Therefore, because the Set will contain nn distinct nodes, the memory footprint is linear in the number of nodes.


Approach 2: Floyd's Tortoise and Hare
Intuition

What happens when a fast runner (a hare) races a slow runner (a tortoise) on a circular track? At some point, the fast runner will catch up to the slow runner from behind.

Algorithm

Floyd's algorithm is separated into two distinct phases. In the first phase, it determines whether a cycle is present in the list. If no cycle is present, it returns null immediately, as it is impossible to find the entrance to a nonexistant cycle. Otherwise, it uses the located "intersection node" to find the entrance to the cycle.

Phase 1

Here, we initialize two pointers - the fast hare and the slow tortoise. Then, until hare can no longer advance, we increment tortoise once and hare twice.[1] If, after advancing them, hare and tortoise point to the same node, we return it. Otherwise, we continue. If the while loop terminates without returning a node, then the list is acyclic, and we return null to indicate as much.

To see why this works, consider the image below:

Diagram of cyclic list
Here, the nodes in the cycle have been labelled from 0 to C-1C−1, where CC is the length of the cycle. The noncyclic nodes have been labelled from -F−F to -1, where FF is the number of nodes outside of the cycle. After FF iterations, tortoise points to node 0 and hare points to some node hh, where F \equiv h \pmod CF≡h(modC). This is because hare traverses 2F2F nodes over the course of FF iterations, exactly FF of which are in the cycle. After C-hC−h more iterations, tortoise obviously points to node C-hC−h, but (less obviously) hare also points to the same node. To see why, remember that hare traverses 2(C-h)2(C−h) from its starting position of hh:

\begin{aligned} h + 2(C-h) &= 2C - h \\ &\equiv C-h \pmod C \end{aligned} 
h+2(C−h)
​
  
=2C−h
≡C−h(modC)
​
 

Therefore, given that the list is cyclic, hare and tortoise will eventually both point to the same node, known henceforce as the intersection.

Phase 2

Given that phase 1 finds an intersection, phase 2 proceeds to find the node that is the entrance to the cycle. To do so, we initialize two more pointers: ptr1, which points to the head of the list, and ptr2, which points to the intersection. Then, we advance each of them by 1 until they meet; the node where they meet is the entrance to the cycle, so we return it.

Use the diagram below to help understand the proof of this approach's correctness.

Phase 2 diagram
We can harness the fact that hare moves twice as quickly as tortoise to assert that when hare and tortoise meet at node hh, hare has traversed twice as many nodes. Using this fact, we deduce the following:

To compute the intersection point, let's note that the hare has traversed twice as many nodes as the tortoise, i.e. 2d(\text{tortoise}) = d(\text{hare})2d(tortoise)=d(hare), that means

2(F + a) = F + nC + a2(F+a)=F+nC+a, where nn is some integer.

Hence the coordinate of the intersection point is F + a = nCF+a=nC.

To see the entire algorithm in action, check out the animation below:

Current
1 / 13

Complexity Analysis

Time complexity : O(n)O(n)

For cyclic lists, hare and tortoise will point to the same node after F+C-hF+C−h iterations, as demonstrated in the proof of correctness. F+C-h \leq F+C = nF+C−h≤F+C=n, so phase 1 runs in O(n)O(n) time. Phase 2 runs for F < nF<n iterations, so it also runs in O(n)O(n) time.

For acyclic lists, hare will reach the end of the list in roughly \dfrac{n}{2} 
2
n
​
  iterations, causing the function to return before phase 2. Therefore, regardless of which category of list the algorithm receives, it runs in time linearly proportional to the number of nodes.

Space complexity : O(1)O(1)

Floyd's Tortoise and Hare algorithm allocates only pointers, so it runs with constant overall memory usage.

*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {

        ListNode node=head;
        Set<ListNode> set =new HashSet<ListNode>();
        while(node!=null){
        if(set.contains(node))
            return node;
        set.add(node);
        node=node.next;
            
       }
        return null;
    }
}

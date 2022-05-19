/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
Accepted
382,622

Solution:
Solution
Overview
First of all, as one should know, this is one of the problems from the series of Best Time to Buy and Sell Stock problem. One could start from the first problem in the series and progress one by one from easy to hard.

If there is ever a God of the stock market, who knows the price of stock at any moment, then the strategies to gain the maximum profits from the stock market is actually surprisingly intuitive, which also depends on the number of transactions that one can make.

If one can only make one transaction (i.e. buy and sell once), then better to make this one bet count. The best strategy would be to buy at the lowest price and then sell at the highest price. To put it simple, buy low sell high.

example of price sequence

Let us look at a concrete example as shown in the above graph, given a list of prices, the task becomes to find maximal difference between a latter stock price and an earlier one, which would be the maximal profits that we could gain, if only one transaction is allowed.

In the above example, the best moment to buy the stock would be the timestamp t1, and the best moment to sell the stock would be the timestamp t9.

The above strategy is actually the solution to the first problem of the series, i.e. Best Time to Buy and Sell Stock.

On the other hand, if one can make as many transactions as one would like, then in order to gain the maximal profits, one must capture each augmentation and avoid each plunging of stock price.

Specifically, given a list of prices, for any two adjacent time points with stock prices p1 and p2, the above best strategies can be broke down into the following two cases:

If later the price augments, i.e. p2 > p1, then a good trader should buy at p1 and then sell at p2, seizing this moment to make profits.

If later the price stays the same or even plunges, i.e. p2 <= p1, then a good trader should just hold the money in the pocket, neither to buy nor sell any stock.

With the above strategies, as one can see, we would perfectly capitalize at each right moment, meanwhile avoiding any loss. At the end, the accumulative profits that we gain over the time would reach the maximum.

example of price sequence

With the same example above, we would buy at the moment of t1 and sell it at the moment of t2. Similarly, we would also buy at the moment of t2 and sell the moment of t3. As one might notice, the profits we gain from these two transactions are equivalent to the single transaction of buying at the moment of t1 and selling at the moment of t3.

The above strategy would be the solution for the second problem of series, i.e. Best Time to Buy and Sell Stock II where there is no limit on the number of transactions.


Approach 1: Bidirectional Dynamic Programming
Intuition

The only difference between this problem and the previous two problems is that in this problem we are allowed to make at most two transactions.

Additionally, there is a constraint on the order of transactions stated in the problem description as follows:

You may not engage in multiple transactions at the same time, (i.e. you must sell the stock before you buy again).

We could interpret this constraint as that there would be no overlapping in the sequence of transactions.

order of transaction actions

In other words, the two transactions that we should make would situate in two different subsequences of the stock prices, without any overlapping, which we illustrate in the above graph.

That being said, we can solve the problem in a divide-and-conquer manner, where we divide the original sequence of prices into two subsequences and then we calculate the maximum profit that we could gain from making a single transaction in each subsequence.

The total profits would be the sum of profits from each subsequence. If we enumerate all possible divisions (or we could consider them as combinations of subsequences), we could find the maximum total profits among them, which is also the desired result of the problem.

visual of partitions on sequence

So we divide this problem into two subproblems, and each subproblem is actually of the same problem of Best Time to Buy and Sell Stock as we discussed in the overview section.

Algorithm

A naive implementation of the above idea would be to divide the sequences into two and then enumerate each of the subsequences, though this is definitely not the most optimized solution.

For a sequence of length NN, we would have NN possible divisions (including no division), each of the elements would be visited once in each division. As a result, the overall time complexity of this naive implementation would be \mathcal{O}(N^2)O(N 
2
 ).

We could do better than the naive \mathcal{O}(N^2)O(N 
2
 ) implementation. Regarding the algorithms of divide-and-conquer, one common technique that we can apply in order to optimize the time complexity is called dynamic programming (DP) where we trade less repetitive calculation with some extra space.

In dynamic programming algorithms, normally we create an array of one or two dimensions to keep the intermediate optimal results. In this problem though, we would use two arrays, with one array keeping the results of sequence from left to right and the other array keeping the results of sequence from right to left. For the sake of name, we could call it bidirectional dynamic programming.

visualization of dp arrays

First, we denote a sequence of prices as Prices[i], with index starting from 0 to N-1. Then we define two arrays, namely left_profits[i] and right_profits[i].

As suggested by the name, each element in the left_profits[i] array would hold the maximum profits that one can gain from doing one single transaction on the left subsequence of prices from the index zero to i, (i.e. Prices[0], Prices[1], ... Prices[i]). For instance, for the subsequences of [7, 1, 5], the corresponding left_profits[2] would be 4, which is to buy the price of 1 and sell it at the price of 5.

And each element in the right_profits[i] array would hold the maximum profits that one can gain from doing one single transaction on the right subsequence of the prices from the index i up to N-1, (i.e. Prices[i], Prices[i+1], ... Prices[N-1]). For example, for the right subsequence of [3, 6, 4], the corresponding right_profits[3] would be 3, which is to buy at the price of 3 and then sell it at the price of 6.

Now, if we divide the sequence of prices around the element at the index i, into two subsequences, with left subsequences as Prices[0], Prices[1], ... Prices[i] and the right subsequence as Prices[i+1], ... Prices[N-1], then the total maximum profits that we obtain from this division (denoted as max_profits[i]) can be expressed as follows: \text{max\_profits[i]} = \text{left\_profits[i]} + \text{right\_profits[i+1]}max_profits[i]=left_profits[i]+right_profits[i+1]

Then if we exhaust all possible divisions, i.e. we place the two transactions in all possible combinations of subsequences, we would then obtain the global maximum profits that we could gain from this sequence of stock prices, which can be expressed as follows: \max_{i=[0, N)}{(\text{max\_profits[i]})}max 
i=[0,N)
​
 (max_profits[i])

We demonstrate how the DP arrays are calculated in the following animation.

Current
1 / 9
Following the above idea, Here are some sample implementations.


In the above implementations, we refined the code a bit to make it a bit more concise and hopefully more intuitive. Here are some tweaks that we applied.

Rather than constructing the two DP arrays in two separate loops, we do the calculation in a single loop (two birds with one stone).

We pad the right_profits[i] array with an additional zero, which indicates the maximum profits that we can gain from an empty right subsequence, so that we can compare the result of having only one transaction (i.e. left_profits[N-1]) with the profits gained from doing two transactions.

By the way, one can try the above algorithm on another problem called Sliding Window Maximum.

Complexity

Time Complexity: \mathcal{O}(N)O(N) where NN is the length of the input sequence, since we have two iterations of length NN.

Space Complexity: \mathcal{O}(N)O(N) for the two arrays that we keep in the algorithm.


Approach 2: One-pass Simulation
Intuition

Just when we think that the space complexity of \mathcal{O}(N)O(N) is the best we can get for this problem, many users in the Discussion forum proposed a more optimized solution that reduced the space complexity to O(1)O(1), (just to name a few of them @weijiac, @shetty4l). The idea is quite brilliant, and requires only a single iteration without the additional DP arrays.

The intuition is that we can consider the problem as a game, and we as agent could make at most two transactions in order to gain the maximum points (profits) from the game.

The two transactions be decomposed into 4 actions: "buy of transaction #1", "sell of transaction #1", "buy of transaction #2" and "sell of transaction #2".

To solve the game, we simply run a simulation along the sequence of prices, at each time step, we calculate the potential outcomes for each of our actions. At the end of the simulation, the outcome of the final action "sell of transaction #2" would be the desired output of the problem.

game simulation

Algorithm

Overall, we run an iteration over the sequence of prices.

Over the iteration, we calculate 4 variables which correspond to the costs or the profits of each action respectively, as follows:

t1_cost: the minimal cost of buying the stock in transaction #1. The minimal cost to acquire a stock would be the minimal price value that we have seen so far at each step.

t1_profit: the maximal profit of selling the stock in transaction #1. Actually, at the end of the iteration, this value would be the answer for the first problem in the series, i.e. Best Time to Buy and Sell Stock.

t2_cost: the minimal cost of buying the stock in transaction #2, while taking into account the profit gained from the previous transaction #1. One can consider this as the cost of reinvestment. Similar with t1_cost, we try to find the lowest price so far, which in addition would be partially compensated by the profits gained from the first transaction.

t2_profit: the maximal profit of selling the stock in transaction #2. With the help of t2_cost as we prepared so far, we would find out the maximal profits with at most two transactions at each step.


Complexity

Time Complexity: \mathcal{O}(N)O(N), where NN is the length of the input sequence.

Space Complexity: \mathcal{O}(1)O(1), only constant memory is required, which is invariant from the input sequence.

*/

class Solution {
    public int maxProfit(int[] prices) {
        int l=prices.length;
        if(l<=1) return 0;
        
        int RightMax=prices[l-1];
        int LeftMin=prices[0];
        
        int[] RP=new int[l+1];
        int[] LP= new int[l];
        
        for(int i=1;i<l;i++){
           LP[i]=Math.max(LP[i-1],prices[i]-LeftMin);
            LeftMin=Math.min(LeftMin,prices[i]);

            int k=l-1-i;
            RP[k]=Math.max(RP[k+1],RightMax-prices[k]);
            RightMax=Math.max(RightMax,prices[k]);
                
        }
        
        int maxProfit=0;
            for(int i=0;i<l;i++){
                maxProfit=Math.max(maxProfit,LP[i]+RP[i+1]);
            }
        return maxProfit;
    }
}

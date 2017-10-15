1. rotation of string
//
public static int rightRotate(String word1, String word2) {
    if (word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0 || word1.length() != word2.length()) {
        return -1;
    }
    String str = word1 + word1;
    return str.indexOf(word2) != -1 ? 1 : -1;
}

2. Gray code
byte x = (byte)(term1 ^ term2);
int total = 0;
while(x != 0){
    x = (byte) (x & (x - 1));
    total++;
}
if(total == 1) return 1; else return 0;

3. remove vowels
StringBuffer sb = new StringBuffer();
String v = "aeiouAEIOU";
for(int i = 0; i < string.length(); i++){
    if(v.indexOf(string.charAt(i)) > -1) continue;
    sb.append(string.charAt(i));
}
return sb.toStirng();

4. check valid parath
import java.util.Stack;

public class isValid {
    public boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0)   return true;
        Stack<Character> stack = new Stack<Character>();

        stack.push(s.charAt(0));
        for(int 1 = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') stack.push('(');
            else {
                if(stack.empty()) return false;
                stack.pop();
            }
        }
        return stack.empty();
}

5. longest palindromic substring
// refer to leetcode
public String longestPalindrome(String s) {
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}


6. Subtree

public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s,t);
    }
    public boolean equals(TreeNode x,TreeNode y)
    {
        if(x==null && y==null)
            return true;
        if(x==null || y==null)
            return false;
        return x.val==y.val && equals(x.left,y.left) && equals(x.right,y.right);
    }
    public boolean traverse(TreeNode s,TreeNode t)
    {
        return  s!=null && ( equals(s,t) || traverse(s.left,t) || traverse(s.right,t));
    }
}

7. Two Sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> set = new HashMap<Integer, Integer>();
        set.put(nums[0], 0);
        int[] result = new int[2];
        for(int i = 1; i < nums.length; i++){
            if(set.containsKey(target - nums[i])){
                result[0] = set.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            set.put(nums[i], i);
        }
        return result;
    }
}

8. Find K Nearest Point
// same idea as finding k largest value for a given list

import java.util.PriorityQueue;
import java.util.Comparator;

public class kNearestPoint {
    public Point[] Solution(Point[] array, Point origin, int k) {
        Point[] rvalue = new Point[k];
        int index = 0;
        PriorityQueue<Point> pq = new PriorityQueue<Point> (k, new Comparator<Point> () {
            @Override
            public int compare(Point a, Point b) {
                return (int) (getDistance(a, origin) - getDistance(b, origin));
            }
        });

        for (int i = 0; i < array.length; i++) {
            pq.offer(array[i]);
            if (pq.size() > k)
                pq.poll();
        }
        while (!pq.isEmpty())
            rvalue[index++] = pq.poll();
        return rvalue;
    }
    private double getDistance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
}

9. overlap
// Returns true if two rectangles (l1, r1) and (l2, r2) overlap
bool doOverlap(Point l1, Point r1, Point l2, Point r2)
{
    // If one rectangle is on left side of other
    if (l1.x > r2.x || l2.x > r1.x)
        return false;
    // If one rectangle is above other
    if (l1.y < r2.y || l2.y < r1.y)
        return false;
    return true;

    10. windowSum
    public class SumOfWindow {
    public int[] Solution(int[] array, int k) {
        if (array == null || array.length < k || k <= 0)    return null;
        int[] rvalue = new int[array.length - k + 1];
        for (int i = 0; i < k; i++)
            rvalue[0] += array[i];
        for (int i = 1; i < rvalue.length; i++) {
            rvalue[i] = rvalue[i-1] - array[i-1] + array[i+k-1];
        }
        return rvalue;
    }
}

11. GCD Greatest Common Divisor  就是给一个数组找这些数的最大公约数

public class GCD {
    public int Solution(int[] array) {
        if (array == null || array.length == 1) return 0;
        int gcd = array[0];
        for (int i = 1; i < array.length; i++) {
            gcd = gcd(gcd, array[i]);
        }
        return gcd;
    }
    private int gcd(int num1, int num2) {
        if (num2==0) return num1;
        return GCD(num2,num1%num2);
    }
}

12.LRU Cache count miss、
// REFER to leetcode with hashmap and doublelinklist simmulation;


13. Round Robin
//* TODO
import java.util.LinkedList;
import java.util.Queue;

class Process {
    int arrTime;
    int exeTime;
    Process(int arr, int exe) {
        arrTime = arr;
        exeTime = exe;
    }
}

// here we define the wait time: curTime - lastOperationed time
public class RoundRobinScheduling {
    public float Solution(int[] Atime, int[] Etime, int q) {
        // input checking

        Queue<process> queue = new LinkedList<process>();
        int curTime = 0, waitTime = 0, position = 0;
        while(!queue.isEmpty() || position < Atime.length){
            if(!queue.isEmpty()){
              Process cur = queue.poll();
              waitTime += curTime - cur.arrTime;
              curTime += Math.min(q, exeTime);
              // add all valid processes into the queue
              while(Atime[index] <= curTime && index < Atime.length) {
                  queue.offer(new Process(Atime[index], Etime[index]));
                  index++;
              }
              if(cur.exeTime > q){
                  queue.offer(new Process(curTime, cur.exeTime - q));
              }
            else{
              queue.offer(new Process(Atime[index], Etime[index]));
              curTime = Atime[index++];
            }
          }
        }
    }
}





14.Rotate Matrix
// refer to leetcode
class Solution {
    // idea: tranpose matrix and swap columns
    public void rotate(int[][] matrix) {
        transpose(matrix);
        swapColumn(matrix);
    }

    public void transpose(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = i; j < matrix[0].length; j++){
                int buff = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = buff;
            }
        }
    }

    public void swapColumn(int[][] matrix){
           for(int i = 0; i < matrix.length; i++){
               for(int j = 0; j < matrix[0].length / 2; j++){
                    int buff = matrix[i][j];
                    matrix[i][j] = matrix[i][matrix[0] .length- 1 - j];
                    matrix[i][matrix[0].length - 1 - j] = buff;
               }
           }
    }
}


15. insert into sorted cyclic singly linked list for given random node
public class LinkedListInsert {
    public ListNode Solution(ListNode head, int val)
      ListNode node  = new ListNode(val);
      if(head == null) {
          head = node;
          return;
      }
      // stop condition1 , when cur <= val, cur.next >= val
      // stop condition2 , when at the end of the loop
      // cur.next < cur &&
      // val >= cur -> val is the max, or
      // val  <= cur.next -> is the min at to the end
      ListNode cur = head;
      do{
          if(cur.val <= val && cur.next.val >= val) break;
          if(cur.next.val < cur.val && (val >= cur.val || val  <= cur.next.val)) break;
          cur = cur.next;
      }while(cur != head);
      node.next = cur.next;
      cur.next = node;
  }
}

16. Shorted job first
// TODO follow the idea from roundRoubind with PriorityQueue to store the current avalible job.






17. Binary search tree minimum sum from root to leaf
// recursion
public class ListNode{
  int val;
  ListNode left;
  ListNode right;
}
public class Solution{
  public int minSum(ListNode node){
    if(node == null) return 0;
    if(node.left == null && node.right == null) return minSum(node.left) + node.val;
    if(node.left == null && node.right != null) return minSum(node.right) + node.val;
    return Math.min(minSum(node.left), minSum(node.right)) + node.val;
  }
}


18. Day change(cell growth)
// TODO
// int[] dayChange(int[] cells, int days), cells 数组，有8个元素，
// 每天的变化情况是 当前位置 cell = (cell[i - 1] == cell[i + 1]) ? 0 : 1,
// 左右相等，当前位置为0， 不等为1， 默认 cells[0]左边 和 cells[cells.length - 1]
// 右边的位置元素都是0， 求days天后的变化.

// using add by 2 to mark the changed postion
// thus 2 -> 0 & 3 -> 1;
public class Solution {
  public int[] growth cell(){
    while(days-- > 0) {
      cell[0] = (cell[1] == 0) ?  0 : 1;
      cell[cell.length - 1] = (cell[cell.length - 2] == 0) ?  0 : 1;
      for(int i = 1; i < cell.length - 1; i++){
          if(cell[i] == 1 && cell[i - 1] == cell[i + 1]) {
             cell[i] += 2;
          }
          if(cell[i] == 0 && cell[i - 1] != cell[i + 1]) {
             cell[i] += 2;
          }
      }
      Updates(cell);
    }
    return cell;
  }
  public void Updates(int[] cell){
    for(int i = 0; i < cell.length; i++){
        if(cell[i] == 2) cell[i] = 0;
        if(cell[i] == 3) cell[i] = 1;
    }
  }
}

// Using a pre pointer
public class Solution {
  public int[] growth cell(){
    while(days-- > 0) {
      int pre = cell[0];
      int cur = pre;
      cell[0] = (cell[1] == 0) ?  0 : 1;
      cell[cell.length - 1] = (cell[cell.length - 2] == 0) ?  0 : 1;
      int pre = cell[0];
      for(int i = 1; i < cell.length - 1; i++){
        cur = cell[i];
        cell = (pre == cell[i + 1]) ? 0 : 1;
        pre = cur;
      }
    }
    return cell;
  }
}


20. Maze
// 给个array,其中只有一格是9，其他格子是0或1，0表示此路不通，1表示可以走，
//判断从（0,0) 点开始上下左右移动能否找到这个是9的格子


// we have 4 direction to test for each postion
// using dfs to search path
public class Maze {
   boolean[][] explored;
   boolean found;
   public static boolean findPath(int[][] maze){
     this.found = false;
     explored = new boolean[maze.length][maze[0].length];
     findPath(maze, 0, 0);
     return this.found;
   }
   private void findPath(int[][] maze, int x, int y){
      if(maze[x][y] == 9) {
        this.found = true;
        return;
      }
      if(!this.explored[x][y] || maze[x][y] == 1) return;
      this.explored[x][y] = true;
      if(x - 1 >= 0) findPath(maze, x - 1, y);
      if(x + 1 < maze.length) findPath(maze, x + 1, y);
      if(y - 1 < 0) findPath(maze, x, y - 1);
      if(y + 1 < maze[0].length) findPath(maze, x, y + 1);
   }
}


//TODO  need!!!practice
21. Window Minimum // output the minimum values within each window
// TODO  refer to leetcode, sliding window max;
// structure:  deque
public int[] maxSlidingWindow(int[] a, int k) {
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
	}


22. Four Integer
//TODO
public int[] Solution(int A, int B, int C, int D) {
    int[] rvalue = new int[4];
    rvalue[0] = A;
    rvalue[1] = B;
    rvalue[2] = C;
    rvalue[3] = D;
    Arrays.sort(rvalue);
    swap(rvalue, 0, 1);
    swap(rvalue, 2, 3);
    swap(rvalue, 0, 3);
    return rvalue;
}
public int[] Solution(int A, int B, int C, int D) {
    int[] rvalue = new int[4];
    rvalue[0] = A;
    rvalue[1] = B;
    rvalue[2] = C;
    rvalue[3] = D;
    Arrays.sort(rvalue);
    if((A[3] - A[1]) > (A[2] - A[0]){
       swap(A, 3 ,1);
       swap(A, 2 ,3);
    } else{
       swap(A, 0, 1);
       swap(A, 0, 3);
    }
    return rvalue;
}

23. Arithmetic sequence
// TODO
// find difference for the array
public static int getLAS(int[] A){
  if(A == null || A.length < 3) return 0;
  int count = 0;
  int diff = A[0] - A[1];
  int start = 0;
  for(int i = 1; i < length - 1; i++){
     if(diff != A[i] - A[i + 1]){
          if(i - start >= 2) count += i - start - 1;
          start = i;
          diff = A[i] - A[i + 1];
     }
  }
  if(i - start >= 2) count += i - start - 1;
  return count;
}



24.Tree Amplitude
// TODO
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode (int x) {
        val = x;
        left = null;
        right = null;
    }
}
public class TreeAmplitude {
    public int Solution(TreeNode root) {
        if(root == null) return 0;
        return helper(root, 0, 0);
    }
    private int helper(TreeNode node, int max, int min){
        if(node == null) return max - min;
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        return math.max(helper(node.left, max, min),
        helper(node.right, max, min));
    }
}



25. Maximum Minimum Path // find
public void dfsHelper(int[][] matrix, int min, int i, int j ){
        if (i >= row || j >= col) return;
        if (i == row - 1 && j == col - 1) {
            min = Math.min(min, matrix[i][j]);
            max = Math.max(max, min);
            return;
        }
        min = Math.min(min, matrix[i][j]);
        dfsHelper(matrix, min, i, j + 1);
        dfsHelper(matrix, min, i + 1, j);
    }

int max = 0;
public int maxMIn(int[][] matrix){
  this.max = 0;
  dfs(matrix, 0, 0, Integer.MAX_VALUE);
  return max;
}

public void dfs(int[][] matrix, int i, int j, int min){
    if( i > matrix.length  || j >  matrix[0].length) return;
    if(i == matrix.length && j == matrix[0].length){
        max = Math.max(max, min);
        return;
    }
    int left = 0, right = 0;
    dfs(matrix, i + 1, j, Math.min(min, matrix[i][j]));
    rdfs(matrix, i, j + 1, Math.min(min, matrix[i][j]));
}



26.Search in 2D matrix
// search a point int a sorted ordered matrix
// refer to leetcode


27.Close Two Sum,  window sliding idea, to form the max window first, and search back

//TODO
public Container findOptimalWeights(double capacity, double[] weights, int numOfContainers){
    int right;
    int left = 0;
    int min = Integer.MAX_VALUE;
    Container result = new Container;
    Arrays.sort(weights);
    for(int right = 1; right < weights.length; right++){
      if(weights[0] + weights[right] > capacity) {
         right--;
         break;
      }
    }
    // keep search inside the window
    while(left < right){
        left++;
        while(weights[left] + weights[right] > capacity) {
          right--;
        }
        if(min < capacity - weights[left] - weights[right]){
            result.first = left;
            result.second = right;
        }
    }
    return result;

}

28.Loop in linked list
// TODO

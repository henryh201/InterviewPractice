import java.util.Random;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.*;


class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}

class TreeNode{
    TreeNode left;
    TreeNode right;
    int val;
}

class ListNode{
    ListNode next;
    char val;
    public ListNode(char val){
        this.val = val;
    }
}

class PostIterator{
    Queue<TreeNode> iterator;
    public PostIterator(TreeNode root){
        iterator = new LinkedList();
        helper(root, iterator);
    }
    private void helper(TreeNode root, Queue<TreeNode> queue){
        if(root == null) return;
        helper(root.left, queue);
        helper(root.right, queue);
        queue.add(root); 
    }
    public TreeNode getNext(){
        return iterator.poll();
    }
     // Also isEmpty() && size()
}

public class Ixl{ 
    //@TEST
    public static void main(String[] args){
        
        // TEST1 *P
        //int[] test = {5, 6 , 2, 4, 1, 8};
        //System.out.println(secondNumebr(test));

        //@TES2 *P
        //String test1 = "a1b2 c";
        // System.out.println(convertToInt(test1));

        //@TEST3 *P
        // System.out.println(generateOdd(10, 16));
        // System.out.println(generateOdd(10, 16));
        // System.out.println(generateOdd(10, 16));

        //@TEST4 noNeed

        //@Test5 *p
        // Interval test1 = new Interval(2,9);
        // Interval test2 = new Interval(1, 6);
        // Interval test3 = new Interval(3,7);
        // List<Interval> test = new ArrayList(Arrays.asList(test1, test2, test3));
        // Interval result = findOverlap(test);
        // if(result != null) System.out.println(result.start  + " " + result.end);

        //@TEST6 
        //System.out.println(findCyclic(123));

        //@TEST7 noN

        //@TEST8 refer to leetCode

        //@TEST9. refer to leetcode103

        //@TEST10 *P
        // String[] temp = {"math", "science", "english", "history"};
        // List<String> sticks = new ArrayList(Arrays.asList(temp));
        // String target = "learning";
        // List<String> result = minSticks(target, sticks);
        // for(String cur: result){
        //     System.out.println(cur);
        // }

        //@TEST11 p
        // System.out.println(generateFab(128));
    
        //@TEST12  //TODO

        //@TEST13  test
        // implemente the quick sort function
        // *P
        //int[] test = {1, 9, 5, 3, 7, 11};
        //System.out.println(findKthOpt(5, test));

        //@TEST14 *P

        //@TEST15 NoN
 
        //@TEST16 *P
        // ListNode head = new ListNode('a');
        // head.next = new ListNode('b');
        // head.next.next =  new ListNode('a');
        // head.next.next.next =  new ListNode('a');
        // System.out.println(isPaland(head)); 
        // ListNode cur = head;
        // while(cur != null){
        //     System.out.print(cur.val);
        //     cur = cur.next;
        // }
        

        //@TEST17  TestR!

        //test case required
        
        //@TEST20


        //@TEST23 
        List<String> test = dearrangement("1234");
        for(String cur:test){
            System.out.println(cur);
        }

    }

    // 1.find the 2nd largest number 
    // using queueway to sort 
    public static int secondNumebr(int[] nums){
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(5);
        for(int cur: nums){
            queue.offer(cur); 
            if(queue.size() > 2){
                queue.poll();
            }
        }
        return queue.peek();
    }
    //Followup. using quick sort find nth elements 

    //2.Covert letter to number. "a1b2 c" => "1122 3"
    // assume here ascii value mapping from char to int
    public static String convertToInt(String input){  
        StringBuilder result = new StringBuilder();
        for(char cur: input.toCharArray()){
            if((cur - 'a') >=0 && (cur - 'z') <= 0){
                result.append((cur - 'a') + 1);
            } else {
                result.append(cur);
            }
        }
        return result.toString();
    }


    //3. generate random odd number for given interval
    public static int generateOdd(int low, int high){
        int result =  (int)(Math.random() * (high/2  - low/2)) + low/2;
        return result * 2 + 1;
    }

    //4. find all duplicates within the array
    public static List<Integer> findDuplicate(int[] nums){
        List<Integer> result = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        for(int cur: nums){
            if(set.contains(cur)){
                result.add(cur);
            } else {
                set.add(cur);
            }
        }
        return result;
    }
    // us O(1) space to find:  idea, sort the list and window slid
    

    //5.  find comman interval
    public static Interval findOverlap(List<Interval> input){

        // list has sort function within
        input.sort((a, b)->a.start - b.start);
        int low = input.get(0).start;
        int high = input.get(0).end;
        for(int i = 1; i < input.size(); i++){
            //System.out.print(low); 
            if(input.get(i).start < high){
                low = input.get(i).start;
                high = Math.min(high, input.get(i).end);
            } else {
                return null;
            }
        }
        return new Interval(low, high);
    }  // consider doing so without sort? 

    //6. 1/n find the cyclic part, if no, return 0
    public static String findCyclic(int divisor){
        StringBuilder result = new StringBuilder();
        Map<Integer, Integer> hm = new HashMap<>();
        int divident = 1;
        // notic the first remainder heree
        hm.put(divident, result.length());
        while(true){
            divident *= 10;
            result.append(divident/divisor);
            divident %= divisor;
            if(divident == 0) return "0";
            else if(hm.containsKey(divident)){
                return result.substring(hm.get(divident));
            }
            else {
                hm.put(divident, result.length());
            }
        }
    }

    // 7. generate 4 digit number, where neighboor is different
    public static void generateNumber(int left, List<String> result, String cur){
        if(cur.length() == 4) {
            result.add(cur);
            return; 
        }
        for(int i = 0; i < 10; i*=2){
            if(i != left) generateNumber(i, result, cur + i);
        }
    }


    //8. unique path II.
    // refer to leetcode, note the optimiaze solution
    public static int uniquePath(int[][] obstacles){
        int n = obstacles[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < obstacles.length; i++){
            for(int j =1 ; j < n; j++){
                if(obstacles[i][j] == 1) dp[j] = 0;
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    //9. lc 102. binary tree level order traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        queue.offer(null);
        List<List<Integer>> result = new ArrayList();
        while(queue.peek() != null){
            TreeNode cur =  queue.poll();
            List<Integer> curLevel = new ArrayList<Integer>();
            while(cur != null){
                curLevel.add(cur.val);
                if(cur.left != null)queue.offer(cur.left);
                if(cur.right != null)queue.offer(cur.right);
                cur = queue.poll();
            }
            result.add(curLevel);
            queue.offer(null);
        }
        return result;
    }

    //10. word sticks problem, given a target string,  and stick list,
    // using the letters from each stick to compose the target, find min #  of sticks solution

    public static List<String> minSticks(String target, List<String> sticks){
        // edge case
        HashMap<Character, Integer> map = converToMap(target);
        List<String> result = new ArrayList<>();
        helper(map, result, sticks);
        return result;
    }
    private static void helper(HashMap<Character, Integer> map, List<String> result, List<String> sticks){
        if(map.isEmpty()) return;
        int max = 0;
        String bestStick = "";
        HashMap<Character, Integer> bestMap = new HashMap<Character, Integer>(map);
        for(String stick: sticks){
            HashMap<Character, Integer> hm = new HashMap<Character, Integer>(map);
            int cur = reduceMap(stick, hm);
            if(cur > max) {
                bestStick = stick;
                bestMap = hm;
                max = cur;
            } 
        }
        result.add(bestStick);
        helper(bestMap, result, sticks);
    }
    private static HashMap<Character, Integer> converToMap(String target){
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for(char cur: target.toCharArray()){
            if(hm.containsKey(cur)) hm.put(cur, hm.get(cur) + 1);
            else hm.put(cur, 1);
        }
        return hm;
    }
    private static int reduceMap(String target,HashMap<Character, Integer> map){
        int delete = 0;
        for(char cur: target.toCharArray()){
            if(map.containsKey(cur)) {
                delete++;
                if(map.get(cur) == 1) map.remove(cur); 
                else map.put(cur, map.get(cur) - 1);
            }
        }
        return delete;
    }


    //11. generate random fibanocci number smaller than given n

    public  static int generateFab(int n){
        if(n < 0) return -1;
        if(n == 0) return 0;
        ArrayList<Integer> nums = new ArrayList();
        int a = 0, b = 1;
        int c;
        nums.add(0);
        nums.add(b);
        while(a + b < n){
            c = b;
            b += a;
            a = c;
            nums.add(b);
        }
        int result = nums.get((int)(Math.random() * nums.size()));
        return result;
    }
    // note the fomula for nth fabnacci number:  Fn = （(sqrt(5) + 1))^n - (sqrt(5) - 1)^(-n) / sqrt(5);



    //12.lc 46, give a collection of distinict integers, return all permuations
    // allow duplicates for input string
    public static List<List<Integer>> generatePermuation(List<Integer> nums){  
        // consider the case with duplicate
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Collections.sort(nums);
        boolean[] checked = new boolean[nums.size()];
        helper(nums, new ArrayList<Integer>(), result, checked);
        return result;
    }

    public static void helper(List<Integer> nums, List<Integer>cur,
    List<List<Integer>> result, boolean[] checked){
        if(nums.size() == cur.size()) {
            // create a shallow copy for current list
            result.add(new ArrayList(cur));
            return;
        }
        for(int i = 0; i < nums.size(); i++){
            //check if the element has been used
            if(checked[i] || i > 0 && nums.get(i) == nums.get(i - 1) && !checked[i - 1]) continue;
            checked[i] = true;
            cur.add(nums.get(i));
            helper(nums, cur, result, checked);
            checked[i] = false;
            cur.remove(cur.size() - 1);
        }
    }
    // sides: consider, combinaion sum && subset refer to lc, add to practice list

    // subset from the given list
    public static List<List<Integer>> subset(List<Integer> nums){
        Collections.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        generateSubset(nums, new ArrayList<Integer>(), result, 0);
        return result;
    }
    private static void generateSubset(List<Integer> nums, List<Integer> cur, List<List<Integer>> result, int start){
        //if(cur.size() > nums.size()) return; never used base case
        result.add(new ArrayList(cur));
        for(int i = start; i < nums.size(); i++){
            cur.add(nums.get(i));
            generateSubset(nums, cur, result, i + 1);
            cur.remove(cur.size() - 1); 
        }
    }



    //13. find the kth largest number for given array
    // refer to lc, find kth value
    public static int findKth(int k, int[] nums){
        // quick way, sorting the array, then get the kth value, take O(nlogn);
        // using quick sort idea to find the kth element
        // if partion well, we can have binary search performance
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num: nums){
            pq.offer(num);
            if(pq.size() > k){
                pq.poll();
            }
        }
        return pq.peek();
    }

    // method2 using quick sort, divide conquer
    public static int findKthOpt(int k, int[] nums){
        return findKth(nums, 0, nums.length - 1, k);
    }
    private static int findKth(int[] nums, int start, int end,  int k){
        // not find the kth elemement;
        if(start > end) return -1;
        int pivot = nums[end];
        int left = start;
        for(int i = start; i < end; i++){
            if(nums[i] >= pivot){
                swap(nums, left++, i);
            }
        }
        swap(nums, left, end); 
        if(left  == k - 1) return  nums[left];
        else if(left < k - 1){
            return findKth(nums, left + 1, end, k);
        }else {
            return findKth(nums, start, left - 1, k);
        }
    }
    private static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp; 
    }



    //14. lc64 Minimum Path Sum  
    // dp solution for find the min sum path
    public int minPathSum(int[][] grid) { 
        // using the unique path idea to generate 
        // dp[i] = min(dp[i - 1], dp[i]) + grid[j][i](current value)
        // edge case, dp[0] +=  grid[j][i];
        int[] dp = new int[grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(i == 0 && j == 0) {
                    dp[0] = grid[i][j];
                } else if( j == 0) {
                    dp[0]  += grid[i][j];
                } else if(i == 0){ 
                    dp[j] = dp[j - 1] + grid[i][j];
                } else{ 
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
        }
        return dp[grid[0].length - 1];
    }

    // recursion way to find the min path
    public static int minPath(int[][] grid, int x, int y){
        if(x >= grid.length || y >= grid[0].length){
            return Integer.MAX_VALUE;
        }
        if(x == grid.length - 1 && y == grid[0].length - 1){
            return grid[x][y] ;
        }
        return Math.min(minPath(grid, x + 1, y), minPath(grid, x, y + 1)) + grid[x][y];
    }


    //15. give a n*n square, how many small square you can find 
    
    public static int numSquare(int n){  // lenth of square
        int result = 0;
        for(int i = 1; i <= n; i++){
            result += i*i;
        }
        return result;

        // note: the fomula for square sum is,  n(n + 1)(2n + 1) / 6;
    }

    //expand, if not square, a rectangle
    // observe that, for we can have, (n - i + 1) * (m - i + 1) squares for given length of i;
    public static int numSquare(int n, int m){
        return 0;
    }

    //16 palindromic linked list
    public static boolean palLinkedList(ListNode input){  
        // find the center node, and reverse the right half of list, then compare the value
        ListNode fast = input;
        ListNode slow = input;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next; 
            slow = slow.next;
        }
        if(slow.next == null) return true;
        ListNode rightHalf = reverseList(slow.next);
        ListNode leftHalf = input;
        ListNode temp = rightHalf;
        // reverse the 2nd half according to whether its even of odd by fast pointer
        boolean result = true;
        do{ 
            if(leftHalf.val != temp.val) {
                result = false;
                break; 
            }   
            leftHalf = leftHalf.next;
            temp = temp.next;
        } while(leftHalf != null && temp != null);
        slow.next = reverseList(rightHalf);
        return result;    
    }
    private static ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // solution 2, using a stack to store all the node and compare
    public static boolean isPaland(ListNode head){
        if(head == null || head.next == null) return true;
        Stack<ListNode> st = new Stack();
        ListNode cur = head;
        while(cur != null){
            st.push(cur);
            cur = cur.next;
        }
        cur = st.pop();
        do {
            if(cur.val != head.val) return false;
            cur = st.pop();
            head = head.next;
        } while(head != cur && head.next != cur);
        return head.val == cur.val; 
    }


    //17. LC 199 Binary Tree Right Side View
    // return the left hand view from the tree
    // refer to question levelOrder,

    //TODO
    // 1. method1, post tree traverse where each level only add node into 
    public static List<Integer> reightSideView(TreeNode root){
        List<Integer> result = new ArrayList();
        postOrderTraverse(root, result, 0);
        return result;
    }
    private static void postOrderTraverse(TreeNode root, List<Integer> result, int level){
        if(root == null) return;
        // using size to control level of the tree and postorder traverse
        if(result.size() == level) result.add(root.val);

        postOrderTraverse(root.right, result, level + 1);
        postOrderTraverse(root.left, result, level + 1);
    }

    //18.combination sum refer to permuation and subset
    
    public static List<List<Integer>> combineSum(List<Integer> nums,  int target){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Collections.sort(nums);
        helper(nums , target, result, new ArrayList<Integer>(), 0);
        return result;
    }
    private static void helper(List<Integer> nums, 
    int target, List<List<Integer>> result, List<Integer> path, int start){
        if(target < 0) return;
        if(target == 0)  result.add(new ArrayList(path));

        for(int i =start; i < nums.size(); i++) {
            path.add(nums.get(i));
            helper(nums, target - nums.get(i), result, path, i); // here using i to traverse forward 
            path.remove(nums.size() - 1);
        }
    }


    //19. iterative post order traverse a binary tree // implemented on top of the class
    // using a queue to store the node traverse
    public static void postIterator(TreeNode root){
        Queue<TreeNode> iterator = new LinkedList();
        helper(root,iterator);        
    }
    // follow the iterator style
    private static void helper(TreeNode root, Queue<TreeNode> queue){
        if(root == null) return;
        helper(root.left, queue);
        helper(root.right, queue);
        queue.add(root); 
    }

    //20. Common prefix for a list of string    
    // kind same idea as common interval, refer to lc for opt solution
    // important
    
    // brutal force way finding the common prefix / horizontal scanning the tree
    public static String findCommonPrefix(List<String> input){
        String prefix = input.get(0);
        for(int i = 1; i < input.size(); i++){
            while(input.get(i).indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.length() == 0) return "";
            }
        }
        return prefix;
    }
    // Vertical scanning by comparing each character for each column;

    // implemented tomorrow //:
    public static String findCommonFre( List<String> input){
        // corner case 
        for(int i = 0; i < input.get(0).length(); i++){
            char cur = input.get(0).charAt(i);
            for(int j = 1; j < input.size(); j++){
                if(i == input.get(i).length() || input.get(i).charAt(i) != cur){
                    return input.get(0).substring(0, i);
                }
            }
        }
        return input.get(0);
    }


    //21 return num of elements for given chimesimt fomula Fe3O4

    //22.find Last Byte


    //23. return dearrangement of a string
    public static List<String> dearrangement(String input){
        List<String> result = new ArrayList();
        boolean[] checked = new boolean[input.length()];
        generateDe(input, "", result, checked);

        return result;
    }  
    // for distinct elements
    private static void generateDe(String input, String cur, List<String> result, boolean[] checked){
        if(cur.length() == input.length()) {
            result.add(cur);
        }
        for(int i = 0; i < input.length(); i++){
            if(checked[i] || input.charAt(i) == input.charAt(cur.length())) continue; // skip the same character at specific position
            checked[i] = true;
            generateDe(input, cur + input.charAt(i), result, checked);
            checked[i] = false; 
        }
    }


}
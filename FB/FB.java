

1. find the max len of continuous path for a given tree;


inx max = 0; 
public int static void findMaxPath(ListNode node){
    if(node == null) return 0;
    int left = findMaxPath(node.left);
    int right = findMaxPath(node.left);
    boolean left_child = false, right_child = false;
    if(node.left != null) {
        if(Math.abs(node.val - node.left) == 1) {
            left_child = true;      
        }
    }
    if(node.right != null) {
        if(math.abs(node.val - node.right) == 1) {
            right_child = true;
        }
    }
    int cur_len = 0;
    if(left_child || right_child) {
        cur_len = left + right + 1;
        this.max = Math.max(max, cur_len);
        return Math.max(left, right) + 1;
    }
    return 1;

}


public class test{

    public static void main(String[] args){
        int[] test = {1,2 ,3 ,4, 1, 2 , 4, 8};
        System.out.println(getLAS(test));
    }   

    public static int getLAS(int[] A){
        if(A == null || A.length < 3) return 0;
        int count = 0;
        int diff = A[0] - A[1];
        int start = 0;
        int i;
        for(i = 1; i < A.length - 1; i++){
           if(diff != A[i] - A[i + 1]){
                if(i - start >= 2) count += i - start - 1;
                start = i;
                diff = A[i] - A[i + 1];
           }
        }
        if(i - start >= 2) count += i - start - 1;
        return count;
      }

}
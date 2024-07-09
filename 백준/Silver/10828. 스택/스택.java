import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack stack = new Stack();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            switch(s){
                case "top":
                    stack.top();
                    break;
                case "size":
                    stack.size();
                    break;
                case "empty":
                    stack.empty();
                    break;
                case "pop":
                    stack.pop();
                    break;
                default:
                    stack.push(Integer.valueOf(s.substring(5)));
                    break;

            }
        }
    }

    public static class Stack{
        private static final int STACK_SIZE = 10000;
        private int[] arr = new int[STACK_SIZE];
        private int top = -1;
        public void push(int i){
            top++;
            arr[top] = i;
        }
        public int pop(){
            if(top!=-1){
                int val = arr[top];
                top--;
                System.out.println(val);
                return val;
            }
            System.out.println(-1);
            return -1;
        }

        public int size(){
            System.out.println(top+1);
            return top+1;
        }

        public int empty(){
            if(top==-1){
                System.out.println(1);
                return 1;
            }else{
                System.out.println(0);
                return 0;
            }
        }
        public int top(){
            if(top==-1){
                System.out.println(-1);
                return -1;
            }
            System.out.println(arr[top]);
            return arr[top];
        }
    }
}
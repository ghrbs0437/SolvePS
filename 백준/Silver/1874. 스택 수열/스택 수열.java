import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();

        int arrIndex = 0;
        int stackIndex = 0;

        int temp = arr[0];

        Queue<String> queue = new LinkedList<>();
        for(int i=1;i<=temp;i++){
            stack.add(i);
            queue.add("+");
        }
        for(int i=0;i<n;i++){
//            System.out.println("!!! : "+stack.peek() + "  "+arr[i]);
            if(stack.empty()&&arr[i]>temp){
                for(int j=temp+1;j<=arr[i];j++){
                    stack.add(j);
                    queue.add("+");
                }
                stack.pop();
                queue.add("-");
                temp = arr[i];
                continue;
            }
            if(stack.peek() == arr[i]){
                stack.pop();
                queue.add("-");
            }else if(stack.peek() < arr[i]){
                for(int j=temp+1;j<=arr[i];j++){
                    stack.add(j);
                    queue.add("+");
                }
                stack.pop();
                queue.add("-");
                temp = arr[i];
            }else if(stack.peek() > arr[i]){
                System.out.println("NO");
                return;
            }
        }
        for(String s : queue){
            System.out.println(s);
        }
    }
}
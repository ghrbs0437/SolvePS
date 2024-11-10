import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] strs = br.readLine().split(" ");
        int length = Integer.parseInt(strs[0]);
        int remove = Integer.parseInt(strs[1]);

        int[] arr = new int[length];
        String str = br.readLine();
        HashMap<Integer, PriorityQueue<Integer>> hmap = new HashMap<>();

        LinkedNode root = new LinkedNode();

        LinkedNode current = root;
        for (int i = 0; i < length; i++) {
            int val = str.charAt(i) - '0';
            LinkedNode node = new LinkedNode();
            node.value = val;
            current.next = node;
            node.past = current;
            current = node;
        }

        current = root;
        root.value=99;

        int answerLength = length - remove;

        while(true){
            if(remove==0){
                break;
            }
            if(current.value < current.next.value){ // 다음게 더 큰경우.. 지우면된다.
                current.next.past = current.past;
                current.past.next = current.next;
                current = current.past;
                remove--;
            }else{
                current = current.next;
            }

            if(current.next==null){
                break;
            }
        }

        current = root.next;
        while(answerLength>0){
            sb.append(current.value);
            current = current.next;
            answerLength --;
        }
        System.out.println(sb);
    }

    public static class LinkedNode{
        int value;
        LinkedNode next;
        LinkedNode past;

    }

}
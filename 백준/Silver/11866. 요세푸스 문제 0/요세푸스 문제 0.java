import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] strs =br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]);
        LinkedNode root = new LinkedNode(1);
        LinkedNode current = root;
        for(int i=2;i<=N;i++){
            LinkedNode node = new LinkedNode(i);
            current.next = node;
            node.past = current;
            current = node;
        }
        root.past = current;
        current.next = root;


        current = root;

        sb.append("<");
        while(N>=1){
            for(int i=1;i<K;i++){
                current = current.next;
            }
            sb.append(current.num);
            current.past.next = current.next;
            current.next.past = current.past;
            current = current.next;
            N--;
            if(N<1){
                break;
            }
            sb.append(", ");
        }
        sb.append(">");
        System.out.println(sb);

    }

    public static class LinkedNode{
        LinkedNode past;
        LinkedNode next;
        int num;
        LinkedNode(int num){
            this.num = num;
        }
    }
}
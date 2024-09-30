import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        HashMap<Integer,ArrayList<Node>> hashMap = new HashMap<>();

        int[] arr = new int[cnt];
        for(int i=0;i<cnt;i++){
            arr[i] = Integer.parseInt(strs[i]);
            hashMap.put(arr[i],new ArrayList<>());
        }

        for(int i=0;i<cnt;i++){
            for(int j=0;j<cnt;j++){
                if(i==j){
                    continue;
                }
                int sum = arr[i] + arr[j];
                if(hashMap.get(sum)!=null){
                    hashMap.get(sum).add(new Node(i,j,sum));
                }
            }
        }
        int answer =0 ;

        tc:
        for(int i=0;i<cnt;i++){
            ArrayList<Node> nodelist = hashMap.get(arr[i]);
            for(Node node : nodelist){
                if(node.index1 != i && node.index2!= i){
                    answer++;
                    continue tc;
                }
            }
        }



        System.out.println(answer);
    }

    public static class Node{
        int index1;
        int index2;
        int sum;

        Node(int idx1, int idx2, int sum){
            this.index1 = idx1;
            this.index2 = idx2;
            this.sum = sum;
        }
    }
}
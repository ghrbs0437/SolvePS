import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int n = Integer.parseInt(strs[0]); // 직원의 수
        int m = Integer.parseInt(strs[1]); // 최초의 칭찬 횟수
        
        ArrayList<Node> nodes = new ArrayList<>();
        
        for(int i=0; i<n;i++){
            Node node = new Node();
            nodes.add(node);
        }
        strs = br.readLine().split(" ");

        int root = -1;
        for(int i=0; i<n;i++){
            int num = Integer.parseInt(strs[i]);
            if(num>0){
                nodes.get(i).parent = num - 1;
                nodes.get(num -1).children.add(nodes.get(i));
            }else{
                root = i;
            }
        }

        for(int i=0; i<m;i++){
            strs = br.readLine().split(" ");
            int num = Integer.parseInt(strs[0]) - 1;
            int amount = Integer.parseInt(strs[1]);
            nodes.get(num).count+=amount;
        }

        nodes.get(root).praise(0);

        for(Node node : nodes){
            System.out.print(node.count+" ");
        }

    }

    public static class Node{
        int parent = -1;

        int count = 0;
        ArrayList<Node> children = new ArrayList<>();

        @Override
        public String toString() {
            return "Node{" +
                    "parent=" + parent +
                    '}';
        }

        public void praise(int num){
            count+=num;
            for(Node node : children){
                node.praise(count);
            }
        }

    }
}
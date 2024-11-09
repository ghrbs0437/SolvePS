import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Node[] nodeArr = new Node[101];
        for(int i=1;i<=100;i++){
            nodeArr[i] = new Node(i);
        }

        for(int i=0;i<M;i++){
            String[] strs = br.readLine().split(" ");
            int X = Integer.parseInt(strs[0]);
            int Y = Integer.parseInt(strs[1]);
            int K = Integer.parseInt(strs[2]);
            nodeArr[X].nexts.add(Y);
            nodeArr[X].nextCnt.add(K);
            nodeArr[Y].preCnt++;

            nodeArr[X].exist = true;
            nodeArr[Y].exist = true;
        }

        Queue<Node> nodes = new ArrayDeque<>();
        nodes.add(nodeArr[N]);
        nodeArr[N].containCnt = 1;
        int[] answer = new int[101];
        while(!nodes.isEmpty()){
            Node node = nodes.poll();
            int nSize = node.nexts.size();
            for(int i=0;i<nSize;i++){
                int next = node.nexts.get(i);
                nodeArr[next].preCnt--;
                nodeArr[next].containCnt += node.containCnt * node.nextCnt.get(i);
                if(nodeArr[next].preCnt==0){
                    nodes.add(nodeArr[next]);
                }
            }
        }

        for(int i=1;i<=100;i++){
            if(nodeArr[i].nexts.isEmpty()&&nodeArr[i].exist){
                System.out.println(i+" " +nodeArr[i].containCnt);
            }
        }

    }

    public static class Token{
        int index;
        int cnt = 1;
        Token(int index, int cnt){
            this.index = index;
            this.cnt = cnt;
        }
    }
    public static class Node{
        int index;
        ArrayList<Integer> nexts = new ArrayList<>();
        ArrayList<Integer> nextCnt = new ArrayList<>();
        int preCnt = 0;
        int containCnt ;
        boolean exist;
        public Node(int index){
            this.index = index;
        }
    }


}
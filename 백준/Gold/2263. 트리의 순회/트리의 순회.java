import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    // 인오더 :  중위순회 왼쪽 루트 오른쪽
    // 프리오더 : 전위순회 루트 왼쪽 오른쪽
    // 포스트오더 : 후위순회 왼쪽 오른쪽 루트

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        int[] inorder = new int[size];
        int[] postorder = new int[size];

        HashMap<Integer,Integer> hmap = new HashMap<>();

        String[] strs = br.readLine().split(" ");
        for(int i=0; i<size;i++){
            inorder[i] = Integer.parseInt(strs[i]);
            hmap.put(inorder[i],i);
        }

        strs = br.readLine().split(" ");
        for(int i=0; i<size;i++){
            postorder[i] = Integer.parseInt(strs[i]);
        }

        // 포스트오더의 마지막 노드 = 루트
        // 포스트오더의 마지막 노드에 해당하는 프리오더의 위치 기준으로 왼쪽 오른쪽 트리가 나뉘어진다..

        getAns(inorder,postorder,0,size-1,0,size-1,hmap);

        System.out.println(sb);

    }

    public static void getAns(int[] inorder, int[] postorder, int startIn, int endIn, int startPost, int endPost, HashMap<Integer,Integer> hmap){
        if(startIn > endIn){
            return;
        }

        if(startPost > endPost){
            return;
        }

        int root = postorder[endPost];
        sb.append(root+" ");
        int rootIdx = hmap.get(root);

        int leftSize = rootIdx - startIn;

        // 왼쪽
        getAns(inorder,postorder
                ,startIn,rootIdx -1
                ,startPost, startPost + leftSize -1
                ,hmap);
        // 오른쪽
        getAns(inorder,postorder
                ,rootIdx+1 , endIn
                , startPost + leftSize ,endPost -1
                ,hmap);
    }

}

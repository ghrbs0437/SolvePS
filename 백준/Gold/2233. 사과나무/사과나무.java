import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        String[] strs = br.readLine().split(" ");
        // 오른쪽부터 순회했을떄 그 트리(노드)가 최초에 생성된 시점, 노드를 마지막으로 방문하는 시점)

        TreeNode root = new TreeNode();
        root.first = 0;

        TreeNode current = root;
        HashMap<Integer,TreeNode> hmap = new HashMap<>();
        hmap.put(0,root);

        for(int i=1;i<str.length();i++){
            if(str.charAt(i)=='0'){
                if(current.right == null){
                    current.right = new TreeNode();
                    current.right.first = i;
                    current.right.parent = current;
                    current = current.right;
                    hmap.put(i,current);
                }else{
                    current.left = new TreeNode();
                    current.left.first = i;
                    current.left.parent = current;
                    current = current.left;
                    hmap.put(i,current);
                }
            }else if(str.charAt(i)=='1') {
                hmap.put(i,current);
                current.end = i;
                current = current.parent;
            }

        }

        int a = Integer.parseInt(strs[0])-1;
        int b = Integer.parseInt(strs[1])-1;
        TreeNode treeNode1 = hmap.get(a);

        TreeNode treeNode2 = hmap.get(b);

        while(true){
            if(treeNode1.parent==null && treeNode2.parent==null){
                break;
            }
            
            if (treeNode1.end == treeNode2.end) {
                break;
            }

            if(treeNode1.end > treeNode2.end && treeNode2.parent!=null){
                treeNode2 = treeNode2.parent;
            } else{
                treeNode1 = treeNode1.parent;
            }
        }
        System.out.println((treeNode1.first+1)+" " +(treeNode1.end+1));
    }
    public static class TreeNode{
        int first;
        int end;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

    }
}
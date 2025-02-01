import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        TreeNode root = new TreeNode();
        TreeNode current = root;

        HashMap<Integer,TreeNode> hmap = new HashMap<>();
        hmap.put(0,root);
        for(int i=1;i<=N;i++){
            String[] strs = br.readLine().split(" ");
            if("a".equals(strs[0])){
                current.setChild(new TreeNode());
                current.child.value = Integer.parseInt(strs[1]);
                current = current.child;
            }else if("s".equals(strs[0])){
                current = current.parent;
            }else if("t".equals(strs[0])){
                current = hmap.get(Integer.parseInt(strs[1])-1);
            }
            hmap.put(i,current);
            sb.append(current.value+"\n");
        }
        System.out.println(sb);


    }

    public static class TreeNode{
        int value = -1;
        TreeNode parent;
        TreeNode child;

        public void setChild(TreeNode treeNode){
            this.child = treeNode;
            treeNode.parent = this;
        }
    }
}
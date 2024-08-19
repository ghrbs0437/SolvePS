import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<String,Tree> hmap = new HashMap<>();
        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");

            String index = strs[0];
            if(hmap.get(index)==null){
                hmap.put(index,new Tree(index));
            }

            Tree tree = hmap.get(index);
            if(!strs[1].equals(".")){
                String leftIndex = strs[1];
                if(hmap.get(leftIndex)==null){
                    hmap.put(leftIndex,new Tree(leftIndex));
                }
                tree.left = hmap.get(leftIndex);
            }
            if(!strs[2].equals(".")){
                String rightIndex = strs[2];
                if(hmap.get(rightIndex)==null){
                    hmap.put(rightIndex,new Tree(rightIndex));
                }
                tree.right = hmap.get(rightIndex);
            }
        }
        Tree root = hmap.get("A");
        root.front();
        sb.append("\n");
        root.middle();
        sb.append("\n");
        root.back();
        sb.append("\n");
        System.out.println(sb);
    }
    public static class Tree{
        Tree left;
        Tree right;
        String value;

        public Tree(String string){
            this.value = string;
        }

        public void front(){
            sb.append(value);
            if(left!=null){
                left.front();
            }
            if(right!=null){
                right.front();
            }
        }

        public void middle(){
            if(left!=null){
                left.middle();
            }
            sb.append(value);
            if(right!=null){
                right.middle();
            }
        }

        public void back(){
            if(left!=null){
                left.back();
            }
            if(right!=null){
                right.back();
            }
            sb.append(value);
        }

    }
}
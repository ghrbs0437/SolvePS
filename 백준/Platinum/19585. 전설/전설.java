import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int colorSize = Integer.parseInt(strs[0]);
        int nickNameSize = Integer.parseInt(strs[1]);

        Tree colorTree = new Tree('\0');
        for(int i=0;i<colorSize;i++){
            String str = br.readLine();
            colorTree.set(str,0);
        }

        HashSet<String> hset = new HashSet<>();
        for(int i = 0; i< nickNameSize; i++){
            String str = br.readLine();
            hset.add(str);
        }

        int teamSize = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        team :
        for(int i=0;i<teamSize;i++){
            String str = br.readLine();

            ArrayList<Integer> offsetList = new ArrayList<>();
            colorTree.find(str,0,offsetList);
            if(offsetList.isEmpty()){
                sb.append("No\n");
                continue team;
            }

            for(Integer offset : offsetList){
                if(hset.contains(str.substring(offset))){
                    sb.append("Yes\n");
                    continue team;
                }
            }
            sb.append("No\n");
        }
        System.out.println(sb);

    }

    public static class Tree{
        int depth = 0;
        char curChar = '\0';
        boolean end = false;
        Tree[] tree = new Tree[26];
        // 생성시 문자로 세팅..
        Tree(char chr){
            this.curChar = chr;
        }

        void set(String str, int index){
            int tIndex = str.charAt(index) - 'a';
            if(tree[tIndex] == null){
                tree[tIndex] = new Tree(str.charAt(index));
            }
            tree[tIndex].depth = this.depth +1;
            if(index == str.length()-1){
                tree[tIndex].end = true;
            }else{
                tree[tIndex].set(str,index+1);
            }
        }

        void find(String str,int index, ArrayList<Integer> offsetList){
            int tIndex = str.charAt(index) - 'a';
            if(end){
                offsetList.add(depth);
            }
            if(tree[tIndex]==null){
                return;
            }
            if(index<str.length()-1){
                tree[tIndex].find(str,index+1,offsetList);
            }
        }

    }
}
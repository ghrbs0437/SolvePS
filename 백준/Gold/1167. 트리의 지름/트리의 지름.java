import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    static int MAX_VALUE = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashMap<Integer,Tree> treeMap = new HashMap<>();

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            int index = Integer.parseInt(strs[0]);
            if(treeMap.get(index)==null){
                treeMap.put(index,new Tree(index));
            }

            for(int j=1;;j+=2){
                if(strs[j].equals("-1")){
                    break;
                }
                int childIndex = Integer.parseInt(strs[j]);
                if(treeMap.get(childIndex)==null){
                    treeMap.put(childIndex,new Tree(childIndex));
                }
                treeMap.get(index).linked.add(treeMap.get(childIndex));
                treeMap.get(index).linkedValue.add(Integer.parseInt(strs[j+1]));
            }
        }
        Tree root = treeMap.get(1);
        root.setParent();
        // 트리 구성이 전부 끝났다...
        root.getLinkedMaxDistance();
        System.out.println(MAX_VALUE);

    }

    public static class Tree{
        int index;
        Tree parent;
        ArrayList<Tree> linked = new ArrayList<>();
        ArrayList<Integer> linkedValue = new ArrayList<>();
        Tree(int index){
            this.index = index;
        }

        public void setParent(){
            for(int i=0;i<linked.size();i++){
                if(parent!=null&& linked.get(i).index == parent.index){
                    linked.remove(i);
                    linkedValue.remove(i);
                    i--;
                    continue;
                }
                linked.get(i).parent = this;
                linked.get(i).setParent();
            }
        }

        public int getLinkedMaxDistance(){ // 자식의 거리최대값..
            if(linked.isEmpty()){
                return 0;
            }
            int first = 0;
            int second = 0;
            for(int i=0;i<linked.size();i++){
                int val = linked.get(i).getLinkedMaxDistance() + linkedValue.get(i);
                if(val>second){
                    second = val;
                    if(second>first){
                        int temp = first;
                        first = second;
                        second = temp;
                    }
                }
            }
            if(MAX_VALUE<first+second){
                MAX_VALUE = first+second;
            }
            return Math.max(first,second);
        }

        public String toString(){
            return ""+this.index;
        }
    }

}
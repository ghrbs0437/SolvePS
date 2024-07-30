import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1135

        int N = Integer.parseInt(br.readLine());

        String[] strs = br.readLine().split(" ");
        CTree root = new CTree(0);
        boolean[] contains = new boolean[N];

        HashMap<Integer, CTree> hmap = new HashMap<>();
        hmap.put(0, root);
        for(int i=1;i<N;i++) {
            int parentIndex = Integer.parseInt(strs[i]);
            if(hmap.get(parentIndex)==null) {
                hmap.put(parentIndex, new CTree(parentIndex));
            }
            CTree parent = hmap.get(parentIndex);
            CTree child = new CTree(i);
            parent.add(child);
            hmap.put(i, child);
//            parent.upperPrepergation();
        }
//        System.out.println(hmap.get(0)+ ""+hmap.get(0).underNodeDepth);
//        System.out.println(root.underNodeCnt);

        // root에서 출발.. childs중에서 underNodeDepth가 가장 큰걸 선택
        // 한단계 깊어질수록 카운팅
        // child가 없으면 return.
        // >>>>>>>>>>>>>>>>>
        // 루트에서 자식들 탐색을 DFS형식으로 작성..
        // 하위루트가 리프까지 탐색하는데 걸리는 탐색시간을 기준으로 명령을 하달하는게 베스트.
        //
        System.out.println(root.explore());
    }



    public static class CTree{
        int index;
        int time;
        CTree parent;
        ArrayList<CTree> childs = new ArrayList<>();


        CTree(int myIndex){
            this.index = myIndex;
        }


        public void add(CTree child){
            childs.add(child);
        }


        public int explore() {

            if(childs.size()==0){
                return 0;
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for(CTree child : childs){
                int val = child.explore();
                pq.add(val);
            }

            // 제일 큰거부터 꺼내쓸거야 // 비싼곳 탐색비용을 나타낸다.
            int max = pq.poll()+1; // 여기가 출발지..
            if(childs.size()>max){
                return childs.size();
            }
            for(int i=0;i<childs.size()-1;i++){
                int next = pq.poll()+1;
                if(max == next){
                    continue;
                }
                max--;

            }
//            System.out.println(index + " " + max + " " + childs.size());

            return childs.size()+max-1;

        }

        public String toString() {
            return index + ""+childs.toString();
        }

    }
}
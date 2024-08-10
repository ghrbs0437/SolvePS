import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int V = Integer.parseInt(strs[0]);
        int E = Integer.parseInt(strs[1]);

        HashMap<Integer, ArrayList<NodeToken>> hmap = new HashMap<Integer, ArrayList<NodeToken>>();
        int K = Integer.parseInt(br.readLine());

        inputs : for(int i=0;i<E;i++){
            strs = br.readLine().split(" ");
            int start = Integer.parseInt(strs[0]);
            int end = Integer.parseInt(strs[1]);
            int val = Integer.parseInt(strs[2]);

            if(hmap.get(start)==null){
                hmap.put(start, new ArrayList<>());
            }

            ArrayList<NodeToken> alist = hmap.get(start);
            // 그게 있는지 확인하고.. 값을 갱신해..
            for(int j=0;j<alist.size();j++){
                if(alist.get(j).index == end){
                    if(alist.get(j).value>val){
                        alist.get(j).value = val;
                    }
                    continue inputs;
                }
            }
            hmap.get(start).add(new NodeToken(end,val));
        }


        // 단방향 다익스트라 pq empty까지

        PriorityQueue<NodeToken> pq = new PriorityQueue<>((a,b)->{
            return a.value - b.value;
        });

        HashMap<Integer,NodeToken> answerTokens = new HashMap<>();
        ArrayList<NodeToken> alist = hmap.get(K);
        for(NodeToken node:alist){
            answerTokens.put(node.index,node);
            pq.add(node);
        }
        boolean[] visits = new boolean[V+1];

        visits[K] = true;
//        System.out.println(answerTokens);
        while(!pq.isEmpty()){
            NodeToken node = pq.poll(); // 가장 비용이 적은 정점..
            if(visits[node.index]){
                continue;
            }
            visits[node.index] = true;
            ArrayList<NodeToken> nextList = hmap.get(node.index); // 그리고 다음 연결정점들의 비용을 갱신해야한다..
            if(nextList==null){
                continue;
            }
//            System.out.println(nextList);
            for(NodeToken nl :nextList){
                if(visits[nl.index]){
                    continue;
                }
                int delayCost = answerTokens.get(node.index).value + nl.value;

                if(answerTokens.get(nl.index)==null){
                    answerTokens.put(nl.index,new NodeToken(nl.index,Integer.MAX_VALUE));
                }

                int directCost = answerTokens.get(nl.index).value;

                if(delayCost < directCost){
                    answerTokens.get(nl.index).value = delayCost;
                }
                pq.add(new NodeToken(nl.index,delayCost));
            }
        }
        Set<Integer> keys = answerTokens.keySet();
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=V;i++){
            if(i==K){ // 자기자신
                sb.append("0\n");
                continue;
            }
            if(!keys.contains(i)){
                sb.append("INF\n");
                continue;
            }
            sb.append(answerTokens.get(i).value+"\n");
        }
        System.out.println(sb);
    }


    public static class NodeToken{
        int index;
        int value;

        NodeToken(int index,int value){
            this.index = index;
            this.value = value;
        }

        public String toString(){
            return "["+index+" : " + value + " ]";
        }

        @Override
        public boolean equals(Object o){
            NodeToken t = (NodeToken)o;
            if (t.index == this.index){ // 잠재적 오류
                return true;
            }
            return false;
        }
    }
}
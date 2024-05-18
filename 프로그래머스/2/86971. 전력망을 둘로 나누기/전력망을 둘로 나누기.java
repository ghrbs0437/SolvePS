import java.util.*;


class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 99999;
        int size = wires.length;
        HashMap<Integer,HashSet<Integer>> hmap = new HashMap<>();
        
        // 반대거 = size - count - 1
        // 정답 = 반대거 - count 절대값
        for(int i=0;i<size;i++){
            int sPos = wires[i][0];
            int ePos = wires[i][1];
            
            if(hmap.get(sPos)==null){
                hmap.put(sPos,new HashSet<Integer>());
            }
            if(hmap.get(ePos)==null){
                hmap.put(ePos,new HashSet<Integer>());
            }
            hmap.get(sPos).add(ePos);
            hmap.get(ePos).add(sPos);
        }
        for(int i=0;i<size;i++){
           
            int count=0;
            Queue<Integer> sPoss = new LinkedList<>();
            HashSet<Integer> visit = new HashSet<>();
            sPoss.add(wires[i][0]);
            while(sPoss.size()!=0){
                Integer temp = sPoss.poll();
                // System.out.println(temp);
                // wires[i][0] , wires[i][1]을 무시한다..  즉, 간선 하나를 제거한다
                if(temp ==wires[i][1]){
                    continue;
                }
                if(!visit.contains(temp)){
                    visit.add(temp); 
                    count++;    
                    for(Integer m :hmap.get(temp)){
                         sPoss.add(m);
                    }
                }
            }
            // System.out.println(visit);
            int temp  = Math.abs(size - 2 * count+1);
            if(temp<answer){
                answer = temp;
            }
        }
         
        
        return answer;
    }
}
import java.util.*;


/**
* n 점의 개수
* s 출발지점
* a a의목적지
* b b의목적지
* fares 각 지점사이의 택시요금
*/
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] maze = new int[n+1][n+1];
        HashMap<Integer,HashSet<Integer>> accessable = new HashMap<>();
        for(int[] fare : fares){
            maze[fare[0]][fare[1]] = fare[2];
            maze[fare[1]][fare[0]] = fare[2];
            if(accessable.get(fare[0])==null){
                HashSet<Integer> hset = new HashSet<>();
                accessable.put(fare[0],hset);
            }
            accessable.get(fare[0]).add(fare[1]);
            if(accessable.get(fare[1])==null){
                HashSet<Integer> hset = new HashSet<>();
                accessable.put(fare[1],hset);
            }
            accessable.get(fare[1]).add(fare[0]);
        }
        
        
        int[] sMin = getAns(s, maze);
        int[] aMin = getAns(a, maze);
        int[] bMin = getAns(b, maze);
        
        int answer = 0;

        for(int i=1;i<n+1;i++){
            int temp = sMin[i]+aMin[i]+bMin[i];
            if(temp == 0){
                continue;
            }
            if(answer==0){
                answer = temp;
            }
            if(answer > temp){
                answer = temp;
            }
        }
        for(int i:sMin){
            System.out.print(i + " ");
        }
        System.out.println(" ");
        for(int i:aMin){
            System.out.print(i + " ");
        }
        System.out.println(" ");
        for(int i:bMin){
            System.out.print(i + " ");
        }
        
        return answer;
    }
    
    public int[] getAns(int start, int[][] distance){
        int size = distance.length;
        int[] minAns = new int[size];
        for(int i=0;i<size;i++){
            minAns[i] = distance[start][i];
        }
        
        HashSet<Integer> visit = new HashSet<>();
        visit.add(start);
        visit.add(0);
        while(true){
            // 최소값을 가지는 점을 찾아라
            int min = 0;
            int index = 0;
            for(int i=1;i<size;i++){
                if(minAns[i]==0){
                    continue;
                }
                if(visit.contains(i)){
                    continue;
                }
                if(min == 0 && minAns[i]!=0){
                    min =minAns[i];
                    index = i;
                    continue;
                }
                if(min>minAns[i]){
                    min =minAns[i];
                    index = i;
                }
            }
            
            // for(int i:minAns){
            //     System.out.print(i+" ");
            // }
            // System.out.println("");
            
            visit.add(index);
            if(min ==0){
                return minAns;
            }
            for(int i=1;i<size;i++){
                if(i==start){
                    continue;
                }
                if(distance[index][i]==0){
                    continue;
                }
                
                if(minAns[i] == 0){
                    minAns[i] = minAns[index] + distance[index][i];
                }
                if(minAns[i] > minAns[index] + distance[index][i]){
                    minAns[i] = minAns[index] + distance[index][i];
                }
            }
        }
        
    }
    
   
  

}
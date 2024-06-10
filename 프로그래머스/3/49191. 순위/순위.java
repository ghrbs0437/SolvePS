import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        
        int[][] players = new int[n][n];
        
        for(int[] result :results){
            players[result[0]-1][result[1]-1] = 1;
            players[result[1]-1][result[0]-1] = -1;
        }
        
        
        for(int i=0;i<n;i++){   
            winPropagation(i,players,n);
            losePropagation(i,players,n);
        }
        
        // for(int[] a : players){
        //     for(int i: a){
        //         System.out.print(i+" ");
        //     }
        //     System.out.println("");
        // }
        
        int answer = 0;
        for(int i=0;i<n;i++){
            int count = 0;
            for(int j=0;j<n;j++){
                if(players[i][j] !=0){
                    count++;
                }
            }
            if(count>=n-1){
                answer++;
            }
        }
        
        
        return answer;
    }
    
    public void winPropagation(int start, int[][] players,int number){
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visit = new HashSet<>();
        queue.add(start);
        while(queue.size()!=0){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int current = queue.poll();
                for(int j=0;j<number;j++){
                    if(visit.contains(j)){
                        continue;
                    }
                    
                    if(players[current][j]==1){
                        players[start][j] = 1;
                        queue.add(j); 
                    }
                }
                visit.add(current);
            }
        }
    }
    
    public void losePropagation(int start, int[][] players,int number){
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visit = new HashSet<>();
        queue.add(start);
        while(queue.size()!=0){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int current = queue.poll();
                for(int j=0;j<number;j++){
                    if(visit.contains(j)){
                        continue;
                    }
                    if(players[current][j]==-1){
                        players[start][j] = -1;
                        queue.add(j); 
                    }
                }
                visit.add(current);
            }
        }
    }
    

}
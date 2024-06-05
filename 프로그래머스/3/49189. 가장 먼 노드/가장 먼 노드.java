import java.util.*;


/**
*
* n 점의 개수
* edge 간선표기
*/
// 모든 점에 대해 최단경로 찾고 
class Solution {
    public int solution(int n, int[][] edge) {

        HashMap<Integer,ArrayList<Integer>> hmap = new HashMap<>();
        
        for(int i=0;i<edge.length;i++){
            if(hmap.get(edge[i][0]-1)==null){
                hmap.put(edge[i][0]-1, new ArrayList<Integer>());
            }
            if(hmap.get(edge[i][1]-1)==null){
                hmap.put(edge[i][1]-1, new ArrayList<Integer>());
            }
            hmap.get(edge[i][0]-1).add(edge[i][1]-1);
            hmap.get(edge[i][1]-1).add(edge[i][0]-1);
        }
        
        int[] distance = new int[n];
        ArrayList<Integer> list1 = hmap.get(0);
        for(Integer i : list1){
            distance[i] = 1;
        }
        
        // hmap.forEach((key,value)->{
        //     System.out.println(key + ": " + value);
        // });
        
        
        HashSet<Integer> visit = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visit.add(0);
        while(true){
            if(queue.size()==0){
                break;
            }
            
            int size = queue.size();
            // 큐에 든거 다 사용해봄..
            for(int i=0;i<size;i++){
                int next = queue.poll();
                ArrayList<Integer> nlist = hmap.get(next);
                // 그중에 연결되어 있는점을 찾는다.
                for(Integer temp : nlist){ 
                    if(visit.contains(temp)){
                        continue;
                    }
                    // System.out.println(visit + "[" + temp+"]");
                    // System.out.println("!  "+temp);
                    // temp = 연결된점
                    distance[temp] = distance[next] + 1;
                    queue.add(temp);
                    visit.add(temp);
                }
            }
            
        }        
        // for(int i : distance){
        //     System.out.print(i+" ");
        // }
        
        int count = 1;
        int max = -1;
        for(int i:distance){
            if(max == i){
                count++;
            }
            if(max<i){
                max = i;
                count=1;
            }
        }
        
        
        return count;
    }
    
     public int solution2(int n, int[][] edge) {
        int answer = 0;
        int[][] maze = new int[n][n];
        for(int[] ed : edge){
            maze[ed[0]-1][ed[1]-1] = 1;
            maze[ed[1]-1][ed[0]-1] = 1;
        }
        // for(int[] ma : maze){
        //     for(int i : ma){
        //         System.out.print(i+ " ");
        //     }
        //     System.out.println();
        // }
        int core = 0;
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visit = new HashSet<>();
        queue.add(0);
        visit.add(0);
        while(true){
            if(queue.size()==0){
                break;
            }    
            int qSize = queue.size(); 
            for(int i=0;i<qSize;i++){
                int point = queue.poll();
                for(int j=0;j<n;j++){
                    if(visit.contains(j)){
                        continue;
                    }
                    if(maze[point][j]!=0){ 
                        maze[core][j] = maze[core][point] + maze[point][j];
                        queue.add(j);
                        visit.add(j);
                    }
                }
            }

        }
        
        // for(int[] ma : maze){
        //     for(int i : ma){
        //         System.out.print(i+ " ");
        //     }
        //     System.out.println();
        // }
        
        
        int max = -1;
        int count = 0;
        for(int i=0;i<n;i++){
            if(max==maze[0][i]){
                count++;
            }
            if(max<maze[0][i]){
                count=1;
                max = maze[0][i];
            }
        }
        
        
        
        return count;
    }
}
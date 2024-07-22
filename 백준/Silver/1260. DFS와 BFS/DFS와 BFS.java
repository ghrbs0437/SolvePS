import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        int V = Integer.parseInt(strs[2]);

        int[][] arr = new int[N][N];
        for(int i=0;i<M;i++){
            String[] line = br.readLine().split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            arr[from-1][to-1] = 1;
            arr[to-1][from-1] = 1;
        }
        
        DFS(N,arr,V-1,new int[N]);
        System.out.println();

        BFS(N,arr,V-1, new int[N]);



    }

    public static void DFS(int N,int[][] arr , int start,int[] visit){
        visit[start] = 1;
        System.out.print(start+1);

        for(int i=0;i<N;i++){
            if(visit[i]==1){
                continue;
            }
            if(arr[start][i] == 1){
                System.out.print(" ");
                DFS(N,arr,i,visit);
            }
        }
    }

    public static void BFS(int N,int[][] arr , int start, int[] visit){


        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i=0;i<size;i++){
                int pos = queue.poll();
                if(visit[pos]==1){
                    continue;
                }
                System.out.print(pos+1+" ");
                visit[pos] = 1;
                for(int j=0;j<N;j++){
                    if(visit[j]==1){
                        continue;
                    }
                    if(arr[pos][j]==1){
                        queue.add(j);
                    }
                }
            }
        }
    }
}
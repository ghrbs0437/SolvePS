import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] directions = {
            {0,-1},{-1,0},{0,1},{1,0} // 위 왼 아 오
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
        String[] strs = br.readLine().split(" ");
        int M = Integer.parseInt(strs[0]);
        int N = Integer.parseInt(strs[1]);

        Node[][] maps = new Node[N][M];

        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                Node node = new Node(i,j,Integer.parseInt(strs[j]));
                maps[i][j] = node;
            }
        }

        boolean[][] visits = new boolean[N][M];
        int[][] groups = new int[N][M];
        Queue<Node> queue = new ArrayDeque<>();
        int groupNumber = 1;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(visits[i][j]){
                    continue;
                }
                queue.add(maps[i][j]);

                while(!queue.isEmpty()){
                    int size = queue.size();

                    for(int k = 0; k < size; k++){
                        Node poll = queue.poll();

                        int cy = poll.y;
                        int cx = poll.x;

                        if(visits[cy][cx]){
                            continue;
                        }
                        visits[cy][cx] = true;
                        groups[cy][cx] = groupNumber;
                        for(int m = 0; m < 4; m++){
                            if(poll.opens[m]){
                                int dy = directions[m][0];
                                int dx = directions[m][1];
                                int ny = cy + dy;
                                int nx = cx + dx;
                                if(ny<0||nx<0||ny>=N||nx>=M){
                                    continue;
                                }
                                queue.add(maps[ny][nx]);
                            }
                        }

                    }

                }
                groupNumber ++;
            }

        }



        HashMap<Integer, Integer> sizes = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> nearLists = new HashMap<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){

                if(sizes.get(groups[i][j]) == null){
                    sizes.put(groups[i][j],0);
                }

                int next =sizes.get(groups[i][j]) + 1;
                sizes.put(groups[i][j], next);

                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = i + dy;
                    int nx = j + dx;

                    if(ny < 0 || nx < 0 || ny >=N || nx>= M){
                        continue;
                    }
                    if(groups[ny][nx] != groups[i][j]){
                        if(nearLists.get(groups[i][j])==null){
                            nearLists.put(groups[i][j],new HashSet<>());
                        }
                        nearLists.get(groups[i][j]).add(groups[ny][nx]);
                    }
                }
            }
        }
        int maxSize = 0;
        int answer = 0;

        for(int i=1;i<groupNumber;i++){
            int currentSize = sizes.get(i);
            maxSize = Math.max(currentSize,maxSize);

            for(int num : nearLists.get(i)){
                answer = Math.max(answer,currentSize + sizes.get(num));
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(groupNumber-1).append("\n").append(maxSize).append("\n").append(answer);
        System.out.println(sb);

    }

    public static class Node{
        int y;
        int x;

        boolean[] opens = new boolean[4];

        Node(int y, int x, int number){

            this.y = y;
            this.x = x;

            if((number & 1) != 1){
                opens[0] = true;
            }

            if((number & 2) != 2){
                opens[1] = true;
            }

            if((number & 4) != 4){
                opens[2] = true;
            }
            if((number & 8) != 8){
                opens[3] = true;
            }

        }
    }
}
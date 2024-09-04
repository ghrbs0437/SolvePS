import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public static int[][] map;
    public static int[][] costMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 0;
        while(true){
            tc++;
            int size = Integer.parseInt(br.readLine());
            if(size==0){
                break;
            }
            map = new int[size][size];
            costMap = new int[size][size];
            for(int i=0;i<size;i++){
                String[] strs = br.readLine().split(" ");
                for(int j=0;j<size;j++){
                    map[i][j] = Integer.parseInt(strs[j]);
                    costMap[i][j] = Integer.MAX_VALUE;
                }
            }
            costMap[0][0] = map[0][0];
            PriorityQueue<Token> pq = new PriorityQueue<>((a,b)-> a.value-b.value);
            pq.add(new Token(0,0,map[0][0]));

            while(!pq.isEmpty()){
                Token current = pq.poll();
                int cy = current.y;
                int cx = current.x;
                for(int[] direction: directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy+ dy;
                    int nx = cx+ dx;
                    if(nx<0||ny<0||ny>= map.length||nx>=map[0].length){
                        continue;
                    }

                    if(costMap[ny][nx] > costMap[cy][cx] + map[ny][nx]){
                        costMap[ny][nx]  = costMap[cy][cx] + map[ny][nx];
                        pq.offer(new Token(ny,nx,map[ny][nx]));
                    }
                }
            }
            sb.append("Problem ").append(tc).append(": ").append(costMap[map.length-1][map.length-1]).append("\n");
        }
        System.out.println(sb);
    }

    public static class Token{
        int y;
        int x;
        int value;
        Token(int y,int x,int value){
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }
}
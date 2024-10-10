import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        tc :for(int tc=1;tc<=testCase;tc++){
            int n = Integer.parseInt(br.readLine());
            String[] strs = br.readLine().split(" ");
            int startY = Integer.parseInt(strs[0]);
            int startX = Integer.parseInt(strs[1]);
            Position[] nodes = new Position[n+1];
            for(int i=0;i<n;i++){
                strs = br.readLine().split(" ");
                nodes[i] = new Position(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]),i);
            }
            strs = br.readLine().split(" ");
            int endY = Integer.parseInt(strs[0]);
            int endX = Integer.parseInt(strs[1]);
            nodes[n] = new Position(endY,endX,n);

            Queue<Position> q = new ArrayDeque<>();
            q.add(new Position(startY,startX,-1));
            boolean[] visits = new boolean[n+1];
            while(!q.isEmpty()){
                int size = q.size();
                for(int i=0;i<size;i++){
                    Position pos = q.poll();
                    int cy = pos.y;
                    int cx = pos.x;
                    for(int j=0;j<=n;j++){
                        int distance = Math.abs(nodes[j].y - cy) + Math.abs(nodes[j].x - cx);
                        if(distance<=1000){
                            if(j==n){
                                sb.append("happy\n");
                                continue tc;
                            }else{
                                if(!visits[j]){
                                    q.add(nodes[j]);
                                    visits[j] = true;
                                }
                            }
                        }
                    }
                }
            }
            sb.append("sad\n");

        }
        System.out.println(sb);

    }
    public static class Position{
        int y;
        int x;
        int index;
        Position(int y,int x,int index){
            this.y = y;
            this.x = x;
            this.index = index;
        }
    }
}
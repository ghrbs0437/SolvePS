import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static int[][] directions = {
            {-1,0},{-1,-1},{0,-1},
            {1,-1},{1,0},{1,1},
            {0,1},{-1,1}
    };

    public static int ANSWER = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int line = 4;

        Node[][] nodes = new Node[line][line];
        for(int i=0;i<line;i++){
            String[] strs = br.readLine().split(" ");

            for(int j=0;j<4;j++){
                int index = Integer.parseInt(strs[2*j]);
                int direction = Integer.parseInt(strs[2*j+1])-1;
//                System.out.println(direction);
                Node node = new Node(index,direction,i,j);
                nodes[i][j] = node;
            }
        }
        PlayGame(0,0,nodes,0);

        System.out.println(ANSWER);
    }

    public static void MoveFish(Node[][] nodes,int sharkY, int sharkX){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            return a.index - b.index;
        });


        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(nodes[i][j] == null){
                }else{
                    pq.add(nodes[i][j]);
                }
            }
        }



        while(!pq.isEmpty()){
            Node poll = pq.poll();
            int cy = poll.y;
            int cx = poll.x;
            int dIndex = poll.direction;

            while(true){
                int dy = directions[dIndex][0];
                int dx = directions[dIndex][1];

                int ny = cy + dy;
                int nx = cx + dx;

                if(ny<0||nx<0||ny>=4||nx>=4){
                    dIndex = (dIndex+1)%8;
                    continue;
                }
                if(ny == sharkY && nx == sharkX){
                    dIndex = (dIndex+1)%8;
                    continue;
                }
                nodes[cy][cx].direction = dIndex;
                nodes[cy][cx].y = ny;
                nodes[cy][cx].x = nx;

                if(nodes[ny][nx]!=null){
                    nodes[ny][nx].y = cy;
                    nodes[ny][nx].x = cx;
                }

                nodes[cy][cx] = nodes[ny][nx];
                nodes[ny][nx] = poll;
                break;
            }

        }


    }


    // 먹고 물고기 움직이고 상어 움직이고.

    public static void PlayGame(int sharkY, int sharkX,Node[][] nodes,int cnt){

        int ySize = nodes.length;
        int xSize = nodes[0].length;
        Node[][] backup = new Node[ySize][xSize];
        for(int i=0;i<ySize;i++){
            for(int j=0;j<xSize;j++){
                if(nodes[i][j]==null){
                    continue;
                }
                backup[i][j] = new Node(nodes[i][j].index,nodes[i][j].direction,i,j);
            }
        }

        int sharkDirection = nodes[sharkY][sharkX].direction;
        int eatCnt = nodes[sharkY][sharkX].index;
        nodes[sharkY][sharkX] = null;

        MoveFish(nodes,sharkY,sharkX);

        int dy = directions[sharkDirection][0];
        int dx = directions[sharkDirection][1];
        int ny = sharkY;
        int nx = sharkX;
        while(true){
            ny += dy;
            nx += dx;
            if(ny <0 || nx< 0 || ny>=4 || nx>=4){
                break;
            }
            if(nodes[ny][nx] == null){
                continue;
            }
            PlayGame(ny,nx,nodes,cnt + eatCnt);
        }

        for(int i=0;i<ySize;i++){
            for(int j=0;j<xSize;j++){
                nodes[i][j] = backup[i][j];
            }
        }

        ANSWER = Math.max(ANSWER,eatCnt+cnt);

    }

    public static class Node{
        int index;
        int direction;

        int y;
        int x;

        Node(int index, int direction,int y, int x){
            this.index = index;
            this.direction = direction;
            this.y = y;
            this.x = x;
        }
    }

}
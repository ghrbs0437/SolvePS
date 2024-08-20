import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Main {

   
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);
        int R = Integer.parseInt(strs[2]);


        // 시뮬레이션 완탐 -> 300*300*1000 9천만. 1초안에 커버가 되긴할거같은데..

        int[][] arr = new int[N][M];
         for(int i=0;i<N;i++) {
            strs = br.readLine().split(" ");
            for(int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(strs[j]);
            }
        }

        int depth = 0;
        HashMap<Integer,ArrayList<Position>> hmap = new HashMap<>();

        while(depth!=M/2&&depth!=N/2) {
            ArrayList<Position> list = getCyclelist(depth,depth,arr,N-depth-1,M-depth-1);
            hmap.put(depth,list);
            depth++;
        }

        int[][] rotated = new int[N][M];
        for(int i=0;i<depth;i++){
            ArrayList<Position> alist = hmap.get(i);
            for(int j=0;j<alist.size();j++){
                int value = alist.get(j).value;
                int index = (j+R) % alist.size();
                int movedY = alist.get(index).y;
                int movedX = alist.get(index).x;
                rotated[movedY][movedX] = value;
            }
        }


        for(int i=0;i<rotated.length;i++) {
            for(int j=0;j<rotated[0].length;j++) {
                sb.append(rotated[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    static int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}}; // 회전방향

    public static ArrayList<Position> getCyclelist(int ystart, int xstart, int[][] arr, int yEnd, int xEnd) {
        int directionIndex = 0;
        int currentY = ystart;
        int currentX = xstart;
        int ysize = (yEnd-ystart)+1;
        int xsize = (xEnd-xstart)+1;
        int total = ysize*2+xsize*2 -4;
        ArrayList<Position> alist = new ArrayList<>();
        while(total>0) { // 다음 위치를 구하자..
            Position pos = new Position(currentY,currentX,arr[currentY][currentX]);
            alist.add(pos);

            // 다음 원소를 찾아봅시다.
            int[] direction = directions[directionIndex];
            int dy = direction[0];
            int dx = direction[1];
            // 현재 정의된 방향으로 나아가도 되나요?
            if(currentY+dy>yEnd || currentY+dy<ystart||currentX+dx>xEnd||currentX+dx<xstart) {
                directionIndex = (directionIndex+1)%4;
                direction = directions[directionIndex];
            }
            dy = direction[0];
            dx = direction[1];
            
            currentY+=dy;
            currentX+=dx;
            total--;
        }
        return alist;
    }


    public static class Position {
        int x;
        int y;
        int value;

        Position(int y, int x, int value) {
            this.y = y;
            this.x = x;
            this.value = value;
        }

        public String toString() {
            return "[Y : " + y + ", X : " + x + "]";
        }
    }
}
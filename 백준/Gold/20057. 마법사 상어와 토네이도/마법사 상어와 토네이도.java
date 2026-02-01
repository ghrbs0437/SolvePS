import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int[][] directions = {
            {0,-1},{1,0},{0,1},{-1,0}
    };

    static int ANSWER = 0 ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[][] maps = new int[N][N];

        for(int i=0;i<N;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                int n = Integer.parseInt(strs[j]);
                maps[i][j] = n;
            }
        }

        int sy = N/2;
        int sx = N/2;

        int cy = sy;
        int cx = sx;

        int time = 0;
        int dt = 1;
        int dIndex = 0;

        while(true){
            int dy = directions[dIndex][0];
            int dx = directions[dIndex][1];
            for(int i=0;i<dt;i++){
                cx += dx;
                cy += dy;
                if(cy>=N || cx>=N || cy<0 || cx<0){
                    System.out.println(ANSWER);
                    return;
                }
                tornado(maps,cy,cx,dy,dx);
            }

            dIndex = (dIndex+1) % 4;
            time++;
            if(time%2 ==0){
                dt++;
            }
        }


    }

    public static void tornado(int[][] map, int y, int x, int dy, int dx){
        int origin = map[y][x];

//        for(int i=0;i<map.length;i++){
//            for(int j=0;j<map.length;j++){
//                System.out.print(map[i][j]+" " );
//            }
//            System.out.println();
//        }
//        System.out.println(y+ " " + x );
//        System.out.println();


//        ANSWER += setIfPossibleAndReturnImpossibleQuantity(map,y,x,0);
        map[y][x] = 0;
        int moveAmount = 0;
        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y+2*dy,x+2*dx,(int)Math.floor(origin*0.05));

        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y + dx,x + dy,(int)Math.floor(origin*0.07));
        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y - dx,x - dy,(int)Math.floor(origin*0.07));

        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y+dx*2,x+dy*2,(int)Math.floor(origin*0.02));
        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y-dx*2,x-dy*2,(int)Math.floor(origin*0.02));

        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y - dy + dx,x - dx + dy,(int)Math.floor(origin*0.01));
        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y - dy - dx,x - dx - dy,(int)Math.floor(origin*0.01));

        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y  + dy + dx,x + dx + dy,(int)Math.floor(origin*0.1));
        moveAmount += setIfPossibleAndReturnImpossibleQuantity(map,y  + dy - dx,x + dx - dy,(int)Math.floor(origin*0.1));

        setIfPossibleAndReturnImpossibleQuantity(map,y+dy,x+dx,origin - moveAmount);
    }

    public static int setIfPossibleAndReturnImpossibleQuantity(int[][] map, int y, int x, int amount){
        if(y>=map.length || x>=map[0].length || y<0 || x<0){
            ANSWER +=amount;
        }else{
            map[y][x] += amount;
        }
        return amount;
    }
}
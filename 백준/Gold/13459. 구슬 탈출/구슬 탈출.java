import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);
        char[][] map = new char[ySize][xSize];
        for(int i=0;i<ySize;i++){
            String str = br.readLine();
            for(int j=0;j<str.length();j++){
                map[i][j] = str.charAt(j);
            }
        }
        getAns(0,map);

        if(FINISHED){
            System.out.println(1);
        }else{
            System.out.println(0);
        }

    }

    public static boolean FINISHED = false;
    public static char[][] copyMap(char[][] map){
        char[][] newMap = new char[map.length][map[0].length];
        for(int i=0;i< map.length;i++){
            for(int j=0;j<map[0].length;j++){
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    public static void getAns(int moveCnt,char[][] map){
        if(FINISHED){
            return;
        }
        if(moveCnt>=10){
            return;
        }

        for(int[] direction : directions){
            int dy = direction[0];
            int dx = direction[1];

            char[][] newMap = copyMap(map);
            int code = move(dy,dx,newMap);
            if(code ==-1){

            }else if(code == 0){
                getAns(moveCnt+1,newMap);
            }else if(code == 1){
                FINISHED = true;
                return;
            }
        }
    }

    // -1 망함 0 다음스텝 1 종료
    public static int move(int dy,int dx,char[][] map){


        if(dy==-1||dx==-1){
            for(int i=0;i<map.length;i++){
                for(int j=0;j< map[0].length;j++){
                    if(map[i][j]=='R' || map[i][j] == 'B'){
                        int cy = i;
                        int cx = j;
                        // 옮기는 작업..
                        while(true){
                            int ny = cy + dy;
                            int nx = cx + dx;
                            if(ny<0||nx<0||ny>=map.length||nx>=map[0].length){
                                break;
                            }
                            if(map[ny][nx] == '.'){
                                map[ny][nx] = map[cy][cx];
                                map[cy][cx] = '.';
                                cy = ny;
                                cx = nx;
                                continue;
                            }else if(map[ny][nx] =='O'){
                                map[cy][cx] = '.';
                            }
                            break;
                        }
                    }
                }
            }
        }


        if(dy==1||dx==1){
            for(int i=map.length-1;i>=0;i--){
                for(int j=map[0].length-1;j>=0;j--){
                    if(map[i][j]=='R' || map[i][j] == 'B'){
                        int cy = i;
                        int cx = j;
                        // 옮기는 작업..
                        while(true){
                            int ny = cy + dy;
                            int nx = cx + dx;
                            if(ny<0||nx<0||ny>=map.length||nx>=map[0].length){
                                break;
                            }
                            if(map[ny][nx] == '.'){
                                map[ny][nx] = map[cy][cx];
                                map[cy][cx] = '.';
                                cy = ny;
                                cx = nx;
                                continue;
                            }else if(map[ny][nx] =='O'){
                                map[cy][cx] = '.';
                            }
                            break;
                        }
                    }
                }
            }
        }

        boolean blue = false;
        boolean red = false;

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]=='B'){
                    blue = true;
                }
                if(map[i][j]=='R'){
                    red = true;
                }
            }
        }

        if(blue && red){
            return 0;
        }

        if(blue && !red){
            return 1;
        }
        return -1;
    }
}
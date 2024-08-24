import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        char[][] map = new char[ySize][xSize];

        Position red = new Position(0,0);
        Position blue = new Position(0,0);
        Position hole = new Position(0,0);
        for(int i=0;i<ySize;i++){
            String str = br.readLine();
            for(int j=0;j<str.length();j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='R'){
                    red.y = i;
                    red.x = j;
                }
                if(map[i][j] =='B'){
                    blue.y = i;
                    blue.x = j;
                }
                if(map[i][j] =='O'){
                    hole.y = i;
                    hole.x = j;
                }
            }
        }
        getAns(map,red.y, red.x, blue.y, blue.x, 0,null);
        if(MIN==Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(MIN+1);
        }
    }
    static int MIN = Integer.MAX_VALUE;


    public static void getAns(char[][] map,int redY,int redX,int blueY,int blueX,int depth,Command pastCommand){
        if(depth>MIN){
            return;
        }

        if(depth==10){
            return;
        }

        if(pastCommand == null){
            char[][] map1 = copyMap(map);
            Position red = new Position(redY,redX);
            Position blue = new Position(blueY,blueX);
            boolean ok = move(map1,red,blue,Command.UP,depth);
            if(ok)
                getAns(map1,red.y,red.x,blue.y,blue.x,depth+1,Command.UP);

            char[][] map2 = copyMap(map);
            red = new Position(redY,redX);
            blue = new Position(blueY,blueX);
            ok = move(map2,red,blue,Command.DOWN,depth);
            if(ok) getAns(map2,red.y,red.x,blue.y,blue.x,depth+1,Command.DOWN);
//
            char[][] map3 = copyMap(map);
            red = new Position(redY,redX);
            blue = new Position(blueY,blueX);
            ok = move(map3,red,blue,Command.LEFT,depth);
            if(ok) getAns(map3,red.y,red.x,blue.y,blue.x,depth+1,Command.LEFT);
//
            char[][] map4 = copyMap(map);
            red = new Position(redY,redX);
            blue = new Position(blueY,blueX);
            ok = move(map4,red,blue,Command.RIGHT,depth);
            if(ok) getAns(map4,red.y,red.x,blue.y,blue.x,depth+1,Command.RIGHT);

            return;
        }

        if(pastCommand.dy==0){
            char[][] map1 = copyMap(map);
            Position red = new Position(redY,redX);
            Position blue = new Position(blueY,blueX);
            boolean ok = move(map1,red,blue,Command.UP,depth);
            if(ok) getAns(map1,red.y,red.x,blue.y,blue.x,depth+1,Command.UP);

            char[][] map2 = copyMap(map);
            red = new Position(redY,redX);
            blue = new Position(blueY,blueX);
            ok = move(map2,red,blue,Command.DOWN,depth);
            if(ok) getAns(map2,red.y,red.x,blue.y,blue.x,depth+1,Command.DOWN);

            return;
        }
//
        if(pastCommand.dx==0){
            char[][] map1 = copyMap(map);
            Position red = new Position(redY,redX);
            Position blue = new Position(blueY,blueX);
            boolean ok = move(map1,red,blue,Command.RIGHT,depth);
            if(ok) getAns(map1,red.y,red.x,blue.y,blue.x,depth+1,Command.RIGHT);

            char[][] map2 = copyMap(map);
            red = new Position(redY,redX);
            blue = new Position(blueY,blueX);
            ok = move(map2,red,blue,Command.LEFT,depth);
            if(ok) getAns(map2,red.y,red.x,blue.y,blue.x,depth+1,Command.LEFT);
            return;
        }


    }

    public static boolean move(char[][] map,Position red, Position blue,Command command, int depth){
        int dy = command.dy;
        int dx = command.dx;

        int redY = red.y;
        int redX = red.x;
        int blueY = blue.y;
        int blueX = blue.x;

        boolean redGoal = false;
        boolean blueGoal = false;
        while(true){
            int nextRedX = redX+dx;
            int nextRedY = redY+dy;
            int nextBlueX = blueX+dx;
            int nextBlueY = blueY+dy;

            boolean redNomove = false;
            boolean blueNomove = false;

            if(redGoal||map[nextRedY][nextRedX] == '#' || map[nextRedY][nextRedX] == 'B'){

                redNomove = true;
            }else if(map[nextRedY][nextRedX]=='O'){
                map[redY][redX] = '.';
                redGoal = true;
            }else{
                map[redY][redX] = '.';
                redX = nextRedX;
                redY = nextRedY;
                map[redY][redX] = 'R';
            }

            if(blueGoal||map[nextBlueY][nextBlueX] == '#' || map[nextBlueY][nextBlueX] == 'R'){
                blueNomove = true;
            }else if(map[nextBlueY][nextBlueX]=='O'){
                map[blueY][blueX] = '.';
                blueGoal = true;
                break;
            }else{
                map[blueY][blueX] = '.';
                blueX = nextBlueX;
                blueY = nextBlueY;
                map[blueY][blueX] = 'B';
            }
            if(redNomove&&blueNomove) {
                break;
            }
        }
        red.x = redX;
        red.y = redY;
        blue.y = blueY;
        blue.x = blueX;

        if(blueGoal){
            return false;
        }

        if(redGoal){
            if(MIN>depth){
                MIN = depth;

            }
            return false;
        }

        return true;
    }


    public static char[][] copyMap(char[][] map){
        char[][] newMap = new char[map.length][map[0].length];
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }


    public enum Command{
        UP(-1,0),DOWN(1,0),LEFT(0,-1),RIGHT(0,1);

        int dy;
        int dx;
        Command(int dy,int dx){
            this.dy = dy;
            this.dx = dx;
        }
    }


    public static class Position{
        int y;
        int x;
        Position(int y,int x){
            this.y = y;
            this.x = x;
        }
    }
}
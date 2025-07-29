import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int[][] directions = {{1,-1},{1,0},{1,1},{0,-1},{0,0},{0,1},{-1,-1},{-1,0},{-1,1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int y = Integer.parseInt(strs[0]);
        int x = Integer.parseInt(strs[1]);

        char[][] maps = new char[y][x];

        Position me = null;

        List<Position> arduino = new ArrayList<>();
        for(int i=0;i<y;i++){
            String str = br.readLine();
            for(int j=0;j<x;j++){
//                maps[i][j] = str.charAt(j);
//                if(maps[i][j] == 'I'){
//                    me = new Position(i,j);
//                }else if(maps[i][j] == 'R'){
//                    Position pos = new Position(i,j);
//                    arduino.add(pos);
//                }

                maps[i][j] = str.charAt(j);
                if(maps[i][j] == 'I'){
                    me = new Position(i,j);
                }else if(maps[i][j] == 'R'){
                    Position pos = new Position(i,j);
                    arduino.add(pos);
                }
                maps[i][j] = '.';

            }
        }
        String str = br.readLine();




        for(int i=0;i<str.length();i++){
            maps = new char[y][x];
            int command = str.charAt(i) - '1';
            // 나의 이동
            me.y +=directions[command][0];
            me.x +=directions[command][1];

            for(Position ard : arduino){
                if(ard.y == me.y && ard.x == me.x){
                    System.out.println("kraj "+(i+1));
                    return;
                }
            }


            for(Position ard : arduino){
                move(ard,me);
            }

            for(Position ard : arduino){
                if(ard.y == me.y && ard.x == me.x){
                    System.out.println("kraj "+(i+1));
                    return;
                }
            }

            for(int j=0;j<arduino.size();j++){
                int cy = arduino.get(j).y;
                int cx = arduino.get(j).x;
                if(maps[cy][cx] == 0){
                    maps[cy][cx] = (char)(j + '0');
                }else{
                    arduino.get(maps[cy][cx]-'0').flag = true;
                    arduino.get(j).flag = true;
                }
            }

            for(int j=0;j<arduino.size();j++){
                if(arduino.get(j).flag){
                    arduino.remove(j);
                    j--;
                }
            }
        }

        maps = new char[y][x];
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                maps[i][j] = '.';
            }
        }
        maps[me.y][me.x] = 'I';
        for(Position ard : arduino){
            maps[ard.y][ard.x] = 'R';
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){

                    sb.append(maps[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);




    }

    public static void move(Position arduino, Position target){
        int dy = getUnderOne(target.y - arduino.y);
        int dx = getUnderOne(target.x - arduino.x);
        arduino.y +=dy;
        arduino.x += dx;
    }


    public static int getUnderOne(int num){
        if(num < 0){
            return -1;
        }else if(num > 0){
            return 1;
        }else{
            return 0;
        }
    }

    public static class Position{
        int y;
        int x;

        boolean flag = false;

        Position(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

}
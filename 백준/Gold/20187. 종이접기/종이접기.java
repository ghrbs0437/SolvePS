import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Main {

    static int startY = 0;
    static int startX = 0;

    static int endY = 0;
    static int endX = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        String[] command = br.readLine().split(" ");
        int blankPos = Integer.parseInt(br.readLine())+1;

        int mapSize = 1<<k;
        int[][] map = new int[mapSize*4][mapSize*4];

        startY = mapSize*2;
        startX = mapSize*2;
        endY = startY + 1;
        endX = startX + 1;

        map[startY][startX] = 1;
        map[startY][endX] = 2;
        map[endY][startX] = 3;
        map[endY][endX] = 4;

        for(int i=command.length-1;i>=0;i--){
            switch(command[i]){
                case "D":
                    Down(map);
                    break;
                case "U":
                    Up(map);
                    break;
                case "L":
                    Left(map);
                    break;
                case "R":
                    Right(map);
                    break;
            }
        }


        int cy = startY;
        int cx = startX;
        while(true){
            if(cy > endY){
                break;
            }
            if(cx>endX){
                cx = startX;
                cy+=2;
                System.out.println("");
                continue;
            }

            int cnt = 0 ;
            for(int y = cy ; y<cy+2;y++){
                for(int x = cx; x<cx+2;x++){
                    if(map[y][x] == blankPos){
                        System.out.print(cnt+ " ");
                    }
                    cnt++;
                }
            }
            cx +=2;
        }

//        System.out.println(startY + " " + startX + " " +endY+ " " +endX);

        // startY startX 는 좌상단 좌표
        // endY endX 는 우하단 좌표

    }

    public static void printMap(int[][] map) {
        for(int i=0;i<map.length;i++) {
            for(int j=0;j<map.length;j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }



    public static void Left(int[][] map) {
        for(int i=startY;i<=endY;i++) {
            for(int j=endX;j>=startX;j--) {
                map[i][endX+endX-j+1] = map[i][j];
            }
        }
        endX += (endX-startX)+1;
    }


    public static void Right(int[][] map) {

        for(int i=startY;i<=endY;i++) {
            for(int j=startX;j<=endX;j++) {
                map[i][startX+startX-j-1] = map[i][j];
            }
        }
        startX -= (endX-startX)+1;

    }



    public static void Down(int[][] map) {
        for(int i=startY;i<=endY;i++) {
            for(int j=startX;j<=endX;j++) {
                map[startY+startY-i-1][j] = map[i][j];
            }
        }
        startY -= (endY-startY)+1;
    }


    public static void Up(int[][] map) {
        for(int i=endY;i>=startY;i--) {
            for(int j=startX;j<=endX;j++) {
                map[endY+endY-i+1][j] = map[i][j];
            }
        }
        endY += (endY-startY)+1;
    }



}
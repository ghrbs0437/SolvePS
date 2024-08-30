import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {

    static int [][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int mapSize = Integer.parseInt(br.readLine());
        char[][] map = new char[mapSize][mapSize];
        for(int i=0;i<mapSize;i++){
            String str = br.readLine();
            for(int j=0;j<str.length();j++){
                map[i][j] = str.charAt(j);
            }
        }


        int[][] section = new int[mapSize][mapSize];
        int sectionIndex =1;
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){
                if(section[i][j]!=0){
                    continue;
                }
                makeSection(map,new char[]{map[i][j]},section,i,j,sectionIndex);
                sectionIndex++;
            }
        }
        sb.append(sectionIndex-1+" ");
        section = new int[mapSize][mapSize];
        sectionIndex = 1;
        for(int i=0;i<mapSize;i++){
            for(int j=0;j<mapSize;j++){

                if(section[i][j]!=0){
                    continue;
                }
                if(map[i][j] == 'R'|| map[i][j] =='G'){
                    makeSection(map,new char[]{'R','G'},section,i,j,sectionIndex);
                }else{
                    makeSection(map,new char[]{'B'},section,i,j,sectionIndex);
                }
                sectionIndex++;
            }
        }

        sb.append(sectionIndex-1);
        System.out.println(sb);

    }

    public static void makeSection(char[][] map,char[] sectionChar, int[][] section,int y,int x,int sectionIndex){
        Queue<Integer> qx = new ArrayDeque<>();
        Queue<Integer> qy = new ArrayDeque<>();
        qx.add(x);
        qy.add(y);
        while(!qx.isEmpty()){
            int size = qx.size();
            for(int i=0;i<size;i++){
                int cx = qx.poll();
                int cy = qy.poll();

                if(section[cy][cx]!=0){
                    continue;
                }
                section[cy][cx] = sectionIndex;

                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int ny = cy + dy;
                    int nx = cx + dx;

                    if(ny<0||nx<0||ny>=map.length||nx>=map[0].length){
                        continue;
                    }
                    for(char c : sectionChar){
                        if(map[ny][nx]==c){
                            qx.add(nx);
                            qy.add(ny);
                        }
                    }
                }
            }
        }
    }
}
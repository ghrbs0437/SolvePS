import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        char[][] arr = new char[size][size];
        for(int i=0;i<size;i++){
            String str = br.readLine();
            for(int j=0;j<size;j++){
                arr[i][j] = str.charAt(j);
            }
        }

        boolean[][] normals = new boolean[size][size];
        boolean[][] abNormals = new boolean[size][size];

        int normalCnt = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(normals[i][j]){
                    continue;
                }
                normalCnt++;
                // 방문한적이 없는거라면..
                char c = arr[i][j];
                normals[i][j] = true;

                Queue<Integer> qx = new LinkedList<>();
                Queue<Integer> qy = new LinkedList<>();
                qx.add(j);
                qy.add(i);
                while(!qx.isEmpty()){
                    int qSize = qx.size();
                    for(int k=0;k<qSize;k++){
                        int x = qx.poll();
                        int y = qy.poll();
                        normals[y][x] = true;
                        for(int[] direction : directions){
                            int dy = direction[0];
                            int dx = direction[1];
                            int nextX = x+dx;
                            int nextY = y+dy;
                            if(nextX==size||nextY==size||nextX<0||nextY<0){
                                continue;
                            }
                            if(c!=arr[nextY][nextX]){
                                continue;
                            }
                            if(normals[nextY][nextX]){
                                continue;
                            }
                            qx.add(nextX);
                            qy.add(nextY);
                            normals[nextY][nextX] = true;
                        }
                    }
                }

            }
        }



        int abNormalCnt = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(abNormals[i][j]){
                    continue;
                }
                abNormalCnt++;
                // 방문한적이 없는거라면..
                char c = arr[i][j];
                abNormals[i][j] = true;

                Queue<Integer> qx = new LinkedList<>();
                Queue<Integer> qy = new LinkedList<>();
                qx.add(j);
                qy.add(i);
                while(!qx.isEmpty()){
                    int qSize = qx.size();
                    for(int k=0;k<qSize;k++){
                        int x = qx.poll();
                        int y = qy.poll();
                        abNormals[y][x] = true;
                        for(int[] direction : directions){
                            int dy = direction[0];
                            int dx = direction[1];
                            int nextX = x+dx;
                            int nextY = y+dy;
                            if(nextX==size||nextY==size||nextX<0||nextY<0){
                                continue;
                            }
                            if(abNormals[nextY][nextX]){
                                continue;
                            }

                            char nextChar = arr[nextY][nextX];
                            if(c!=nextChar){
                                if( (c=='R' || c=='G') && (nextChar=='R' || nextChar=='G')){

                                }else{
                                    continue;
                                }
                            }
                            qx.add(nextX);
                            qy.add(nextY);
                            abNormals[nextY][nextX] = true;
                        }
                    }
                }

            }
        }

        System.out.println(normalCnt+" "+abNormalCnt);

    }

}
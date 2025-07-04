import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static int ANSWER = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]); // 연구실 사이즈
        int M = Integer.parseInt(strs[1]); // 최초 활성 바이러스 개수

        int[][] maps = new int[N][N];


        int blank = 0;

        ArrayList<Virus> virusList = new ArrayList<>();
        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                 maps[i][j] =Integer.parseInt(strs[j]);
                 if(maps[i][j] == 0){
                     blank++;
                 }
                 if(maps[i][j] == 2){
                     Virus virus = new Virus(j, i);
                     virusList.add(virus);
                 }
            }
        }
        
        

        solve(maps,virusList, new ArrayList<>(),0,virusList.size(),M);

        if(ANSWER == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ANSWER);
        }
    }

    public static void solve(int[][] maps, ArrayList<Virus> virusList, ArrayList<Integer> select, int current, int max, int size){
        if(current>max){
            return;
        }

        if(select.size() == size){
            Queue<Integer> yq = new ArrayDeque<>();
            Queue<Integer> xq = new ArrayDeque<>();

            int[][] newMap = new int[maps.length][maps[0].length];

            for(int i=0;i<maps.length;i++){
                for(int j=0;j<maps[0].length;j++){
                    newMap[i][j] = maps[i][j];
                }
            }


            for(Integer i : select){
                newMap[virusList.get(i).yPos][virusList.get(i).xPos] = 3;
                yq.add(virusList.get(i).yPos);
                xq.add(virusList.get(i).xPos);
            }



            boolean flag = true;
            for (int i = 0; i < maps.length; i++) {
                for (int j = 0; j < maps.length; j++) {
                    if(newMap[i][j] == 0){
                        flag = false;
                    }
                }
            }
            if(flag){
                ANSWER = Math.min(ANSWER,0);
                return;
            }
            int count = 0;

            qq: while(!yq.isEmpty()) {

                if(count>ANSWER){
                    return;
                }


                int qSize = yq.size();
                for (int i = 0; i < qSize; i++) {
                    Integer cy = yq.poll();
                    Integer cx = xq.poll();

                    for (int[] direction : directions) {
                        int dy = direction[0];
                        int dx = direction[1];

                        int ny = dy + cy;
                        int nx = dx + cx;

                        if (ny >= newMap.length || nx >= newMap[0].length || ny < 0 || nx < 0) {
                            continue;
                        }

                        if (newMap[ny][nx] == 0 || newMap[ny][nx] == 2) {
                            newMap[ny][nx] = 3;
                            yq.add(ny);
                            xq.add(nx);
                        }

                    }
                }
                count++;
                for (int i = 0; i < maps.length; i++) {
                    for (int j = 0; j < maps.length; j++) {
                        if(newMap[i][j] == 0){
                            continue qq;
                        }
                    }
                }
                ANSWER = Math.min(ANSWER,count);
                return;
            }
            return;
        }

        ArrayList<Integer> alist1 = new ArrayList<>(select);
        ArrayList<Integer> alist2 = new ArrayList<>(select);
        alist2.add(current);
        solve(maps,virusList,alist1,current+1,max,size);
        solve(maps,virusList,alist2,current+1,max,size);

    }


    public static class Virus{
        int xPos;
        int yPos;

        Virus(int xPos, int yPos){
            this.yPos = yPos;
            this.xPos = xPos;
        }
    }
}
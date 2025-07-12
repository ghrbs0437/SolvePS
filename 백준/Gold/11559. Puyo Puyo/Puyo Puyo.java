import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] map = new char[12][6];

        for(int i=0;i<12;i++){
            String str = br.readLine();
            for(int j=0;j<6;j++){
                map[i][j] = str.charAt(j);
            }
        }
        
        // 1. 맵의 상태를 판별한다
        // 2. 맵의 상태에서 터지는 그룹들을 식별한다
        // 3. 그룹들을 모두 터트린 다음, 맵의 상태를 확인한다.
        // 4. 이후의 맵의 상태에서 중력을 적용한다 > 까지가 1사이클.


        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> yq = new ArrayDeque<>();

        int cnt = 0;
        while(true){

            boolean boom = false;
            for(int i=0;i<12;i++){
                for(int j=0;j<6;j++){
                    if(map[i][j] == '.'){
                        continue;
                    }else{
                        boolean[][] visits = new boolean[12][6];
                        char key = map[i][j];
                        xq.add(j);
                        yq.add(i);

                        Queue<Integer> endX = new ArrayDeque<>(); // 4개 이상이 터지는게 확인되면 그 후처리용도
                        Queue<Integer> endY = new ArrayDeque<>();
                        while(!xq.isEmpty()){
                            Integer cx = xq.poll();
                            Integer cy = yq.poll();
                            if(visits[cy][cx]){
                                continue;
                            }
                            endY.add(cy);
                            endX.add(cx);
                            visits[cy][cx] = true;
                            for(int[] direction : directions){
                                int dy = direction[0];
                                int dx = direction[1];
                                int nx = cx + dx;
                                int ny = cy + dy;
                                if(ny<0 || ny >= 12 || nx<0 || nx>=6){
                                    continue;
                                }
                                if(map[ny][nx]==key){
                                    yq.add(ny);
                                    xq.add(nx);
                                }
                            }
                        }

                        if(endX.size()>=4){
                            boom = true;
                            while(!endX.isEmpty()){
                                Integer cx = endX.poll();
                                Integer cy = endY.poll();
                                map[cy][cx] = '.';
                            }
                        }
                    }

                }
            }



            if(boom){
                cnt++;
            }else{
                break;
            }
            // 이후 중력처리

            for(int j=0;j<6;j++) {
                int move = 0;
                for (int i = 11; i >= 0; i--) {
                    if (map[i][j] == '.') {
                        move++;
                    }else{
                        if(move!=0){
                            map[i+move][j] = map[i][j];
                            map[i][j] = '.';
                        }
                    }
                }
            }
        }

        System.out.println(cnt);


    }
}

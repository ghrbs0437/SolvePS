import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int CALL_CNT = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mapSize = Integer.parseInt(br.readLine());

        int[][] map = new int[mapSize][mapSize];
        HashMap<Integer,ArrayList<Fish>> hmap = new HashMap<>();
        Fish shark = new Fish(0,0,0);
        for(int i=0;i<mapSize;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<strs.length;j++){

                map[i][j] = Integer.parseInt(strs[j]);
                if(map[i][j]!= 9 && map[i][j]!=0){
                    if(hmap.get(map[i][j])==null){
                        hmap.put(map[i][j],new ArrayList<>());
                    }
                    hmap.get(map[i][j]).add(new Fish(i,j,map[i][j]));
                }
                if(map[i][j]==9){
                    shark = new Fish(i,j,2);
                    shark.eatCnt = 0;
                    map[i][j]=0;
                }

            }
        }
//        for(int i=0;i<mapSize;i++){
//            for(int j=0;j<mapSize;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println("");
//        }


        // 9 아기상어..
        // 1. 먹을 수 있는 물고기가 1개 이상이라면, 가장 가까운거
        // 2. 가장 가까운거리에 있는 물고기가 많다면, 가장 위에
        // 3. 가장 위에 있는 물고기가 둘 이상이면, 왼쪽에 있는거
        // 4. 도달하는 길이 없다면, 엄마콜

        int answer = 0;
        tc : while(true){
            // 1. 크기가 더 작은 물고기들의 목록..
            ArrayList<Fish> eatable = new ArrayList<>();
            for(int i=1;i< shark.size;i++){
                if(hmap.get(i)==null){
                    continue;
                }
                eatable.addAll(hmap.get(i));
            }


            final Fish tmpShark = shark;


            // 2. 크기가 더 작은 물고기들의 목록을 거리가 가까운 순으로 정렬한다. ( 이때 물고기의 크기는 상관하지 않는다.)
            // 3. 만약 거리가 같다면, Y 값이 큰순, X가 작은순으로 한다.
            eatable.forEach((a)->{
                a.distance = FindRoute(map,tmpShark,a);
            });

            Collections.sort(eatable,(a,b)->{
//                int aDistance = Math.abs(a.x - tmpX)+ Math.abs(a.y-tmpY);        // << 이게 틀렸다.. 거리는 "상어가 이동한 거리" 의 최소... 즉 BFS..............
//                int bDistance = Math.abs(b.x - tmpX) + Math.abs(b.y-tmpY);
                if(a.distance==b.distance){
                    if(a.y==b.y){
                        return a.x-b.x;
                    }else{
                        return a.y-b.y;
                    }
                }
                return a.distance-b.distance;
            });

            // 4, eatable의 순회가 끝날떄까지 먹지못한다면 엄마~~
            // 5. 먹었다면 다시 처음부터 하자.


            for(int i=0;i<eatable.size();i++){
                Fish fish = eatable.get(i);
                int moveCnt = fish.distance;
                if(moveCnt==-1){
                    continue;
                }
                // 경로가 있었다면?
                // 물고기는 먹었음 처리
                shark.x = fish.x;
                shark.y = fish.y;
                map[fish.y][fish.x] = 0;
                shark.eatCnt++;
                if(shark.eatCnt==shark.size){
                    shark.size++;
                    shark.eatCnt = 0;
                }
                answer += moveCnt;
                hmap.get(fish.size).remove(fish);
                continue tc;
            }
            break;
        }

        System.out.println(answer);

    }

    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    public static int FindRoute(int[][] map, Fish shark, Fish fish){
        int fishX = fish.x;
        int fishY = fish.y;
        int sharkX = shark.x;
        int sharkY = shark.y;

        Queue<Integer> xQ = new ArrayDeque<>();
        Queue<Integer> yQ = new ArrayDeque<>();
        boolean ate = false;
        boolean[][] visits = new boolean[map.length][map[0].length];
        xQ.add(sharkX);
        yQ.add(sharkY);
//        System.out.println("fish : " + fishY + " "+fishX);
        int moveCnt = 0;
        tc : while(!xQ.isEmpty()){
            int size = xQ.size();
            for(int i=0;i<size;i++){
                int x = xQ.poll();
                int y = yQ.poll();
                if(x==fishX && y==fishY){
                    ate = true;
//                    shark.x = fishX;
//                    shark.y = fishY;
//                    map[fishY][fishX] = 0;
//                    shark.eatCnt++;
//                    if(shark.eatCnt==shark.size){
//                        shark.size++;
//                        shark.eatCnt = 0;
//                    }
                    // 물고기를 먹는다!
                    break tc;
                }
//                System.out.println(y + " " + x);
                if(visits[y][x]){
                    continue;
                }
                visits[y][x] = true;

                for(int[] direction : directions){
                    int dy = direction[0];
                    int dx = direction[1];
                    int nextY = dy+y;
                    int nextX = dx+x;
                    if(nextY<0 || nextX<0 || nextY==map.length || nextX==map[0].length){
                        continue;
                    }
                    if(visits[nextY][nextX]){
                        continue;
                    }
                    if(map[nextY][nextX]<=shark.size){// 이동가능하다
                        xQ.add(nextX);
                        yQ.add(nextY);
                    }

                }

            }
            moveCnt++;
        }
        if(ate){ // 먹은상태야!
            return moveCnt;
        }
        return -1;
    }


    public static class Fish{
        int x;
        int y;
        int size;
        int eatCnt;
        int distance;
        public Fish(int y,int x,int size){
            this.y = y;;
            this.x = x;
            this.size = size;
        }

        public String toString(){
            return "[x : "+x + ", y : "+y+" , size : "+size+"]";
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] directions = {
            {-1,0},{1,0},{0,-1},{0,1} // 상하좌우
    };


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]); // 맵 크기
        int M = Integer.parseInt(strs[1]); // 상어 숫자
        int K = Integer.parseInt(strs[2]); // 냄새가 남는 시간

        ArrayList<Shark> sharks = new ArrayList<>();
        ArrayDeque<Smell> smells = new ArrayDeque<>();
        int[][] map = new int[N][N];

        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                int num = Integer.parseInt(strs[j]);
                if(num !=0){
                    Shark shark = new Shark(i,j,num);
                    sharks.add(shark);
                }
            }
        }

        Collections.sort(sharks,(o1, o2) -> o1.number - o2.number);

        strs = br.readLine().split(" ");
        for(int i=0;i<M;i++){
            sharks.get(i).direction = Integer.parseInt(strs[i]) - 1 ;
        }

        for(int i=0;i<M;i++){
            for(int j=0;j<4;j++){
                strs = br.readLine().split(" ");
                for(int k=0;k<4;k++){
                    int priority = Integer.parseInt(strs[k]) - 1;
                    sharks.get(i).priority[j][k] = priority;
                }
            }
        }





        int time = 0;
//
        for(Shark shark : sharks){
            shark.spreadSmell(map,K,smells);
        }

        while(time<=1000){

            sharksMove(sharks,map);

            int size = smells.size();
            for(int i=0;i<size;i++){
                Smell poll = smells.poll();
                poll.remain--;
                if(poll.remain>0){
                    smells.add(poll);
                }else{
                    map[poll.y][poll.x] = 0;
                }
            }
            for(Smell s : smells){
                map[s.y][s.x] = s.number;
            }

            time++;

            for(Shark shark : sharks){
                shark.spreadSmell(map,K,smells);
            }

            if(getRemainSharkCnt(sharks)==1){
                break;
            }

        }
        if(time>1000){
            System.out.println(-1);
        }else{
            System.out.println(time);
        }
    }

    public static int getRemainSharkCnt(ArrayList<Shark> sharks){
        int cnt = 0;
        for(Shark shark : sharks){
            if(!shark.dead){
                cnt++;
            }
        }
        return cnt;
    }

    public static void sharksMove(ArrayList<Shark> sharks, int[][] map){
        int size = map.length;

        int[][] newMap = new int[size][size];

        for(Shark shark : sharks){
            if(shark.dead){
                continue;
            }
             int[] d = shark.priority[shark.direction];
             int cy = shark.y;
             int cx = shark.x;
             boolean moved = false;
             for(int nextDirection : d){
                 int dy = directions[nextDirection][0];
                 int dx = directions[nextDirection][1];
                 int ny = cy + dy;
                 int nx = cx + dx;
                 if(ny<0||nx<0||ny>=map.length||nx>=map.length){
                     continue;
                 }

                 if(map[ny][nx] == 0){
                     shark.y = ny;
                     shark.x = nx;
                     shark.direction = nextDirection;
                     moved = true;
                     if(newMap[ny][nx] == 0){
                         newMap[ny][nx] = shark.number;
                     }else{
                         if(shark.number < newMap[ny][nx]){
                             sharks.get(newMap[ny][nx]-1).dead = true;
                             newMap[ny][nx] = shark.number;
                         }else{
                             shark.dead = true;
                         }
                     }
                     break;
                 }
             }

             if(!moved){
                 for(int nextDirection : d){
                     int dy = directions[nextDirection][0];
                     int dx = directions[nextDirection][1];
                     int ny = cy + dy;
                     int nx = cx + dx;
                     if(ny<0||nx<0||ny>=map.length||nx>=map.length){
                         continue;
                     }
                     if(map[ny][nx] == shark.number){
                         shark.y = ny;
                         shark.x = nx;
                         shark.direction = nextDirection;
                         if(newMap[ny][nx] == 0){
                             newMap[ny][nx] = shark.number;
                         }else{
                             if(shark.number < newMap[ny][nx]){
                                 sharks.get(newMap[ny][nx]-1).dead = true;
                                 newMap[ny][nx] = shark.number;

                             }else{
                                 shark.dead = true;
                             }
                         }
                         break;
                     }

                 }
             }

        }
//        for(int i=0;i<size;i++){
//            for(int j=0;j<size;j++){
//                System.out.print(newMap[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();


    }

    public static class Shark{

        int y;
        int x;

        int number;
        int direction;
        int[][] priority = new int[4][4];

        boolean dead;

        Shark(int y,int x,int number){
            this.y = y;
            this.x = x;
            this.number = number;
        }

        public void spreadSmell(int[][] map, int K, ArrayDeque<Smell> smells){
            if(dead){
                return;
            }
            map[y][x] = number;
            smells.add(new Smell(y,x,number,K));

        }

        public void printPriority(){
            for(int i=0;i< priority.length;i++){
                for(int j=0;j< priority.length;j++){
                    System.out.print(priority[i][j]+" " );
                }
                System.out.println();
            }
        }

    }

    public static class Smell{
        int y;
        int x;

        int number;
        int remain;

        Smell(int y, int x, int number, int remain){
            this.y = y;
            this.x = x;
            this.number = number;
            this.remain = remain;
        }
    }
}

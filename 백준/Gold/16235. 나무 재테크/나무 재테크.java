import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    static int [][] directions = {
            {-1,0},{1,0},{0,-1},{0,1},
            {-1,-1},{-1,1},{1,-1},{1,1}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String [] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]); // 맵 크기
        int M = Integer.parseInt(strs[1]); // 나무 숫자
        int K = Integer.parseInt(strs[2]); // 년도

        Position[][] map = new Position[N][N];
        int[][] pMap = new int[N][N];

        for(int i=0;i<N;i++){
            strs = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = new Position(5);
                pMap[i][j] = Integer.parseInt(strs[j]);
            }
        }
        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int y = Integer.parseInt(strs[0])-1;
            int x = Integer.parseInt(strs[1])-1;
            int age = Integer.parseInt(strs[2]);
            map[y][x].addTree(age);
        }

        for(int k= 0; k<K;k++){
            int[][] next = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){

                    Position pos = map[i][j];
                    int nextPower = 0;

                    //봄
                    int size = pos.trees.size();
                    ArrayList<Integer> temp = new ArrayList<>();
                    for(int x =0; x<size;x++){
                        int tree = pos.trees.poll();
                        if (pos.power - tree >= 0) {
                            pos.power -= tree;
                            temp.add(tree+1);
                        }else{
                            nextPower += tree/2;
                        }
                    }
                    pos.trees.addAll(temp);
                    //여름
                    pos.power+=nextPower;

                    //가을
                    for(Integer age : pos.trees){
                        if(age%5==0){
                            for(int[] direction : directions){
                                int dy = i +direction[0];
                                int dx = j +direction[1];
                                if(dy<0||dx<0||dy>=N||dx>=N){
                                    continue;
                                }else{
//                                    System.out.println("##");
                                    next[dy][dx]++;
                                }
                            }
                        }
                    }

                }
            }
            // 겨울
            for(int i =0; i<N;i++){
                for(int j=0;j<N;j++){
                    map[i][j].power += pMap[i][j];
                    for(int x=0;x<next[i][j];x++){
                        map[i][j].trees.add(1);
                    }
                }
            }


        }

        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                answer+=map[i][j].trees.size();
            }
        }
        System.out.println(answer);




    }

    public static class Position{
        int power;
        PriorityQueue<Integer> trees = new PriorityQueue<>();

        Position(int power){
            this.power = power;
        }

        public void addTree(Integer age){
            trees.add(age);
        }

    }
}
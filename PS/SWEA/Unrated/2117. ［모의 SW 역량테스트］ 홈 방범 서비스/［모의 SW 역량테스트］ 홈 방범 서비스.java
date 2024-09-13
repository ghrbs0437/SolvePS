import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc=1;tc<=testCase;tc++){
            MAX_CNT = 0;
            String[] strs = br.readLine().split(" ");
            int mapSize = Integer.parseInt(strs[0]);
            int cost = Integer.parseInt(strs[1]);
            int[][] map = new int[mapSize][mapSize];

            ArrayList<House> houseList = new ArrayList<>();
            for(int i=0;i<mapSize;i++){
                strs = br.readLine().split(" ");
                for( int j=0;j<mapSize;j++){
                    map[i][j] = Integer.parseInt(strs[j]);
                    if(map[i][j] ==1){
                        House house = new House(i,j,mapSize);
                        house.BFS();
                        houseList.add(house);
                    }
                }
            }
            getAns(houseList,mapSize,cost);
            sb.append("#"+tc+" "+MAX_CNT+"\n");
        }
        System.out.println(sb);
    }

    static int MAX_CNT = 0;

    public static void getAns(ArrayList<House> houseList, int mapSize,int houseCost){
        int distance = mapSize;
        int[] costs = new int[distance+1];
        costs[0] = 1;
        for(int i=1;i<costs.length;i++){
            costs[i] = (i+1)*(i+1) + i*i;
        }
//        setDistanceCost(costs,0);
//        System.out.println(Arrays.toString(costs));
        while(true){
            if(distance==-1){
                return;
            }
            for(int i=0;i<mapSize;i++){
                for(int j=0;j<mapSize;j++){
                    int cnt = 0;
                    for(House house : houseList){
                        if(house.distanceMap[i][j] <= distance){
                            cnt++;
                        }
                    }
                    if(costs[distance]<= cnt*houseCost && cnt>MAX_CNT){
                        MAX_CNT=cnt;
                    }
                }
            }
            distance--;
        }
    }

    public static void setDistanceCost(int[] map,int index){
        if(index==map.length){
            return;
        }
        if(index==0){
            map[index] = 1;
        }else{
            map[index] = map[index-1]+ index*4;
        }
        setDistanceCost(map,index+1);
    }

    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static class House{
        int y;
        int x;
        int[][] distanceMap;
        House(int y,int x,int size){
            this.y = y;
            this.x = x;
            distanceMap = new int[size][size];
            BFS();
        }
        private void BFS(){
            Queue<Integer> xq = new ArrayDeque<>();
            Queue<Integer> yq = new ArrayDeque<>();
            xq.add(x);
            yq.add(y);
            int depth = 0;
            while(!xq.isEmpty()){
                int size = xq.size();
                for(int i=0;i<size;i++){
                    int cx = xq.poll();
                    int cy = yq.poll();
                    if(distanceMap[cy][cx]!=0){
                        continue;
                    }
                    distanceMap[cy][cx] = depth;

                    for(int[] direction : directions) {
                        int dy = direction[0];
                        int dx = direction[1];
                        int ny = cy + dy;
                        int nx = cx + dx;
                        if (ny < 0 || nx < 0 || ny >= distanceMap.length || nx >= distanceMap[0].length) {
                            continue;
                        }
                        if (distanceMap[ny][nx] != 0) {
                            continue;
                        }
                        if(ny==y&&nx==x){
                            continue;
                        }
                        xq.add(nx);
                        yq.add(ny);
                    }
                }
                depth++;
            }
        }

    }
}
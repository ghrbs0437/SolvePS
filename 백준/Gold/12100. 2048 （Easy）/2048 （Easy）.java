import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int mapSize = Integer.parseInt(br.readLine());
        int[][] map = new int[mapSize][mapSize];
        for(int i=0;i<mapSize;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<mapSize;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }
        getAns(map,0);
        System.out.println(MAX);
    }

    public static void getAns(int[][] map,int depth){
        if(depth==5){
            return;
        }

        int[][] map1 = acopy(map);
        executeCommand3(map1,"up");
        getAns(map1,depth+1);

        int[][] map2 = acopy(map);;
        executeCommand3(map2,"down");
        getAns(map2,depth+1);

        int[][] map3 = acopy(map);
        executeCommand3(map3,"left");
        getAns(map3,depth+1);

        int[][] map4 = acopy(map);
        executeCommand3(map4,"right");
        getAns(map4,depth+1);

    }


    public static void executeCommand3(int[][] map, String command) {
        switch (command) {
            case "up":
                moveMapY(1, 0, map.length - 1, map);
                break;
            case "down":
                moveMapY(-1, map.length - 1, 0, map);
                break;
            case "left":
                moveMapX(1,0, map.length - 1, map);
                break;
            case "right":
                moveMapX(-1,map.length - 1, 0, map);
                break;
        }
    }

    public static void moveMapY(int delta, int startPos, int endPos, int[][] map) {
        int mapSize = map.length;
        for (int x = 0; x < mapSize; x++) {
            Queue<Integer> queue = new ArrayDeque<>();
            for (int y = startPos; y - delta != endPos; y += delta) {
                if (map[y][x] == 0) {
                    continue;
                }
                queue.add(map[y][x]);
                map[y][x] = 0;
            }
            // 1개씩 꺼내며 앞에서 부터 채운다.
            int putIndex = startPos;
            int pastValue = 0;
            while (!queue.isEmpty()) {

                int nextValue = queue.poll();

                if(MAX<nextValue){
                    MAX = nextValue;
                }
                if (pastValue == nextValue) {
                    map[putIndex - delta][x] = nextValue * 2;
                    if(MAX<map[putIndex-delta][x]){
                        MAX = map[putIndex-delta][x];
                    }

                    pastValue = 0;

                } else { // 근데 합칠수 있는 숫자가 아니었어..
                    map[putIndex][x] = nextValue;
                    pastValue = nextValue;
                    putIndex += delta;

                }
            }
        }


        return;
    }

    public static int[][] acopy(int[][] arr){
        int[][] newArr = new int[arr.length][arr[0].length];
        for(int i=0;i< arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }

    static int MAX = 0;

    public static void moveMapX(int delta, int startPos, int endPos, int[][] map) {
        int mapSize = map.length;
        for (int y = 0; y < mapSize; y++) {
            Queue<Integer> queue = new ArrayDeque<>();
            for (int x = startPos; x - delta != endPos; x += delta) {
                if (map[y][x] == 0) {
                    continue;
                }
                queue.add(map[y][x]);
                map[y][x] = 0;
            }
            // 1개씩 꺼내며 앞에서 부터 채운다.
            int putIndex = startPos;
            int pastValue = 0;

            while (!queue.isEmpty()) {
                int nextValue = queue.poll();
                if(MAX<nextValue){
                    MAX = nextValue;
                }

                if (pastValue == nextValue) {
                    map[y][putIndex - delta] = nextValue * 2;
                    if(MAX<map[y][putIndex-delta]){
                        MAX = map[y][putIndex-delta];
                    }
                    pastValue = 0;
                } else { // 근데 합칠수 있는 숫자가 아니었어..
                    map[y][putIndex] = nextValue;
                    pastValue = nextValue;
                    putIndex += delta;
                }
            }
        }
        return;
    }
}
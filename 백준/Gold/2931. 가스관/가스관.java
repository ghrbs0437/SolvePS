import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");

        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);


        char[][] map = new char[ySize][xSize];
        for(int i=0;i<ySize;i++) {
            String str = br.readLine();
            for(int j=0;j<xSize;j++) {
                map[i][j] = str.charAt(j);
            }
        }

        ///////////////////////////// 입력 모두 받았다..


        HashMap<Character, int[][]> hmap = new HashMap<>();
        hmap.put('|', new int[][]{{-1,0},{1,0}});
        hmap.put('-', new int[][]{{0,1},{0,-1}});
        hmap.put('+', new int[][]{{-1,0},{1,0},{0,1},{0,-1}});
        hmap.put('1', new int[][]{{1,0},{0,1}});
        hmap.put('2', new int[][]{{-1,0},{0,1}});
        hmap.put('3', new int[][]{{-1,0},{0,-1}});
        hmap.put('4', new int[][]{{1,0},{0,-1}});

        boolean[][] visits = new boolean[ySize][xSize];

        ArrayList<int[]> answerList = getAns(visits,map,hmap);

        Set<Character> keySet = hmap.keySet();

        key : for(Character key : keySet) {
            int[][] arr = hmap.get(key);
            if(arr.length!=answerList.size()) {
                continue;
            }
            for(int i=0;i<arr.length;i++) {

                boolean matched = false;

                for(int[] direction : answerList) {
                    if(Arrays.equals(direction, arr[i])) {
                        matched = true;
                        // 같은게있으면 괜찮은데 없으면 continue해야해..
                    }
                }
                if(!matched) {
                    continue key;
                }
            }
            sb.append(answerY+1).append(" ").append(answerX+1).append(" ").append(key);
            System.out.println(sb);
            return;
        }

    }
    static int answerY = 0;
    static int answerX = 0;

    public static ArrayList<int[]> getAns(boolean[][] visits,char[][] map, HashMap<Character, int[][]> hmap) {

        ArrayList<int[]> alist = new ArrayList<>();

        for(int i=0;i<visits.length;i++) {
            for(int j=0;j<visits[0].length;j++) {

                if(map[i][j]=='.'
                        ||map[i][j]=='M'
                        ||map[i][j]=='Z') {
                    continue;
                }
                if(visits[i][j]) {
                    continue;
                }
                visits[i][j] = true;
                int[][] nDirections = hmap.get(map[i][j]);
                for(int[] direction: nDirections) {
                    int ny = i+direction[0];
                    int nx = j+direction[1];
                    if(ny<0 || nx<0 || ny>=map.length || nx >=map[0].length) {
                        continue;
                    }

                    if(map[ny][nx]=='.') {
                        answerY = ny;
                        answerX = nx;
                        int[] answerDelta = {-direction[0],-direction[1]};
                        alist.add(answerDelta);
                    }
                }

            }
        }
        return alist;
    }
}
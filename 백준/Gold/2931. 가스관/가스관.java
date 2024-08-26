import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Main {
    static int MAX_VALUE = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");

        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        int homeY = 0 ;
        int homeX = 0;
        int kdY = 0;
        int kdX = 0;

        char[][] map = new char[ySize][xSize];
        for(int i=0;i<ySize;i++) {
            String str = br.readLine();
            for(int j=0;j<xSize;j++) {
                map[i][j] = str.charAt(j);

                if(map[i][j]=='M') {
                    homeY = i;
                    homeX = j;
                }

                if(map[i][j] =='Z') {
                    kdY = i;
                    kdX = j;
                }
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


//		파이프중 아무데서나 시작해서 파이브를 따라 쭉 간다.. Z 나 M이나오면 그만. .

        boolean[][] visits = new boolean[ySize][xSize];

        ArrayList<int[]> answerList = getAns(visits,map,hmap);

        Set<Character> keySet = hmap.keySet();

        key : for(Character key : keySet) {
            int[][] arr = hmap.get(key);
            if(arr.length!=answerList.size()) {
                continue;
            }

            for(int i=0;i<arr.length;i++) {
                boolean okay = false;
                for(int[] direction : answerList) {
                    if(Arrays.equals(direction, arr[i])) {
                        okay = true;
                        // 같은게있으면 괜찮은데 없으면 continue해야해..
                    }
                }
                if(!okay) {
                    continue key;
                }
            }
            System.out.println((answerY+1)+" " +(answerX+1) + " "+ key);
            return;
        }


        // 이아래는 일단 출발은 가능해..

    }
    static int answerY = 0;
    static int answerX = 0;

    public static ArrayList<int[]> getAns(boolean[][] visits,char[][] map, HashMap<Character, int[][]> hmap) {

        int targetY = 0;
        int targetX = 0;

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
                        int[] rvdirec = {-direction[0],-direction[1]};
                        alist.add(rvdirec);
                    }
                }

            }
        }

        return alist;
    }
}
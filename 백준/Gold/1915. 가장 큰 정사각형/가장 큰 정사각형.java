import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();


        String[] strs = br.readLine().split(" ");

        int y = Integer.parseInt(strs[0]);
        int x = Integer.parseInt(strs[1]);

        int[][] map = new int[y][x];

        for(int i=0;i<y;i++){
            String str = br.readLine();
            for(int j=0;j<x;j++){
                map[i][j] = str.charAt(j)-'0';
            }
        }

        boolean[][] visits = new boolean[y][x];
        int[][] arr = new int[y][x];

        getAns(map,visits,arr,0,0);

        int answer = 0;
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                answer = Math.max(arr[i][j],answer);
            }
        }
        System.out.println(answer*answer);

    }
    public static int getAns(int[][] map, boolean[][] visits, int[][] arr,int y, int x){
        if(y>=map.length || x>=map[0].length){
            return 0;
        }
        if(visits[y][x]){
            return arr[y][x];
        }
        visits[y][x] = true;

        int a = getAns(map,visits,arr,y+1,x+1);
        int b = getAns(map,visits,arr,y,x+1);
        int c = getAns(map,visits,arr,y+1,x);
        if(map[y][x]==0){
            return 0;
        }
        arr[y][x] = Math.min(a, Math.min(b,c))+1;
        return arr[y][x];
    }

}
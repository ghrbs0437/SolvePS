import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int num = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");

        int[] arr = new int[3];
        boolean[][][] state = new boolean[61][61][61];

        for(int i=0;i<num;i++){
            arr[i] =  Integer.parseInt(strs[i]);
        }

        Queue<Integer> xq = new ArrayDeque<>();
        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> zq = new ArrayDeque<>();

        xq.add(arr[0]);
        yq.add(arr[1]);
        zq.add(arr[2]);
        state[arr[0]][arr[1]][arr[2]] = true;
        int cnt = 0;
        while(!xq.isEmpty()){
            int size = xq.size();
            cnt++;
            for(int i=0;i<size;i++){
                Integer x = xq.poll();
                Integer y = yq.poll();
                Integer z = zq.poll();

                boolean flag = true;
                if(flag &calc(xq,yq,zq,state,x,y,z,-9,-3,-1)|
                calc(xq,yq,zq,state,x,y,z,-9,-1,-3)|
                calc(xq,yq,zq,state,x,y,z,-3,-9,-1)|
                calc(xq,yq,zq,state,x,y,z,-3,-1,-9)|
                calc(xq,yq,zq,state,x,y,z,-1,-9,-3)|
                calc(xq,yq,zq,state,x,y,z,-1,-3,-9)){
                    System.out.println(cnt);
                    return;
                }

            }
        }

    }

    public static boolean calc(Queue<Integer> xq, Queue<Integer> yq, Queue<Integer> zq, boolean[][][] state, int x,int y,int z, int dx,int dy,int dz){
        int nx = x + dx;
        int ny = y + dy;
        int nz = z + dz;
        if(nx<0){
            nx = 0;
        }
        if(ny<0){
            ny = 0;
        }
        if(nz<0){
            nz = 0;
        }
        if(state[nx][ny][nz]){
            return false;
        }

        if(nx==0 && ny==0 && nz==0){
            return true;
        }

        state[nx][ny][nz] = true;
        xq.add(nx);
        yq.add(ny);
        zq.add(nz);
        return false;
    }


}
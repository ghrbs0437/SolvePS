import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Position> alist = new ArrayList<>();
        for(int i=0;i<N;i++){

            String[] split = br.readLine().split(" ");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1]);
            alist.add(new Position(start,end));
        }

        Collections.sort(alist);

        int current = 0;
        int count =0 ;
        for(Position pos : alist){
            if(pos.x<current){
                continue;
            }
            count++;
            current = pos.y;
        }
        System.out.println(count);
    }

    public static class Position implements Comparable{
        int x;
        int y;

        Position(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof Position){
                Position pos = (Position)o;
                if(this.y==pos.y){
                    return this.x-pos.x;
                }else{
                    return this.y-pos.y;
                }
            }
            return 0;
        }
        @Override
        public String toString(){
            return "["+x+","+y+"]";
        }
    }
}
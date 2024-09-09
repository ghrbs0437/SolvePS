import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");


        // 최대 100
        // 최대 100
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);
        // 최대 10000...
        int M = Integer.parseInt(strs[2]);
        if(M==0){
            System.out.println(0);
            return;
        }
        // -> 연산량 최대 100만..
        LinkedShark<Shark> rootShark = new LinkedShark(new Shark(0,0,0,Direction.UP,0));
        LinkedShark currentPoint = rootShark;

        HashMap<Integer,LinkedShark<Shark>> sharkMap = new HashMap<>();

        for(int i=0;i<M;i++){
            strs = br.readLine().split(" ");
            int sy = Integer.parseInt(strs[0])-1;
            int sx = Integer.parseInt(strs[1])-1;
            int speed = Integer.parseInt(strs[2]);
            Direction sdirection = Direction.of(Integer.parseInt(strs[3])-1);
            int sSize = Integer.parseInt(strs[4]);
            LinkedShark<Shark> shark = new LinkedShark<>(new Shark(sy,sx,speed,sdirection,sSize));
            sharkMap.put(sSize,shark);

            shark.past = currentPoint;
            currentPoint.next = shark;
            currentPoint = shark;
        }

//        currentPoint = rootShark;
//        while(currentPoint.next!=null){
//            System.out.println(currentPoint.current.size);
//            currentPoint = currentPoint.next;
//        }

        int answer = 0;
        for(int i=0;i<xSize;i++){
            int[][] map = makeMap(sharkMap,rootShark,ySize,xSize);
//            for(int j=0;j<ySize;j++){
//                for(int k=0;k<xSize;k++){
//                    System.out.print(map[j][k] + " " );
//                }
//                System.out.println();
//            }
            int size = catchShark(sharkMap,map,ySize,i);
//            System.out.println(size);
            answer +=size;
            moveShark(rootShark, ySize, xSize);
        }
        System.out.println(answer);
    }

    public static void moveShark(LinkedShark<Shark> shark, int ySize, int xSize){
        if(shark==null){
            return;
        }
        while(true){
            shark.current.move(ySize,xSize);
            if(shark.next==null){
                break;
            }
            shark = shark.next;
        }
    }

    public static int[][] makeMap(HashMap<Integer,LinkedShark<Shark>> sharkMap,LinkedShark<Shark> sharks,int ySize,int xSize){
        int[][] map = new int[ySize][xSize];

        if(sharks == null){
            return map;
        }

        LinkedShark<Shark> current = sharks;
        while(true){

            if(map[current.current.y][current.current.x] !=0){
                int removeSize = 0;
                if(map[current.current.y][current.current.x] > current.current.size){
                    removeSize = current.current.size;
                }else{
                    removeSize = map[current.current.y][current.current.x];
                    map[current.current.y][current.current.x] = current.current.size;
                }

                if(sharkMap.get(removeSize).next==null){
                    sharkMap.get(removeSize).past.next = null;
                }else{
                    sharkMap.get(removeSize).past.next = sharkMap.get(removeSize).next;
                    sharkMap.get(removeSize).next.past = sharkMap.get(removeSize).past;
                }
                
            }else{
                map[current.current.y][current.current.x] = current.current.size;
            }
            if(current.next==null){
                break;
            }
            current = current.next;

        }

        return map;
    }

    public static int catchShark(HashMap<Integer,LinkedShark<Shark>> sharkMap, int[][] map, int ySize,int x){
        for(int i=0;i<ySize;i++){
            if(map[i][x] !=0){
                if(sharkMap.get(map[i][x]).next!=null){
                    sharkMap.get(map[i][x]).past.next = sharkMap.get(map[i][x]).next;
                    sharkMap.get(map[i][x]).next.past = sharkMap.get(map[i][x]).past;
                }else{
                    sharkMap.get(map[i][x]).past.next = null;
                }

                return map[i][x];
            }
        }
        return 0;
    }


    public static class LinkedShark<E extends Shark>{
        final Shark current;
        LinkedShark<Shark> next;
        LinkedShark<Shark> past;
        LinkedShark(Shark shark){
            current =  shark;
        }
    }

    public static class Shark{
        int y;
        int x;
        int dy;
        int dx;
        int size; // 고유하다..
        int speed;

        Shark(int y,int x,int speed, Direction direction, int size){
            this.y = y;
            this.x = x;
            this.speed = speed;
            this.dy = direction.dy;
            this.dx = direction.dx;
            this.size = size;
        }

        public void move(int ySize, int xSize){

            int cy = y;
            int cx = x;
            int remain = speed;
            int dy = this.dy;
            int dx = this.dx;
            if(dy == 0){
                remain = remain % (2*xSize-2);
            }
            if(dx == 0){
                remain = remain % (2*ySize-2);
            }

            while(remain!=0){
                remain--;
                int ny = cy + dy;
                int nx = cx + dx;
                if(ny<0 || nx<0 || ny==ySize || nx==xSize){
                    dy *= -1;
                    dx *= -1;
                    ny = cy + dy;
                    nx = cx + dx;
                }

                cy = ny;
                cx = nx;
            }
            this.dy = dy;
            this.dx =dx;
            this.y = cy;
            this.x = cx;
        }


        public boolean equals(Object o ){
            Shark s = (Shark)o;
            return s.size == this.size;
        }

    }

    public enum Direction{
        UP(-1,0),DOWN(1,0),RiGHT(0,1),LEFT(0,-1);

        int dy;
        int dx;

        Direction(int dy,int dx){
            this.dy = dy;
            this.dx = dx;
        }

        public static Direction of(int ordinal){
            for(Direction d : Direction.values()){
                if(d.ordinal()==ordinal){
                    return d;
                }
            }
            return null;
        }
    }
}
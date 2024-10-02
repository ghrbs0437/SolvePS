import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int N = Integer.parseInt(strs[0]);
        int M = Integer.parseInt(strs[1]);

        char[][] map = new char[N][N];
        ArrayList<Node> alist = new ArrayList<>();
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] =='S'){
                    alist.add(new Node(i,j,'S'));
                }else if(map[i][j] == 'K'){
                    alist.add(new Node(i,j,'K'));
                }
            }
        }
//        for(int i=0;i<N;i++){
//            System.out.println(Arrays.toString(map[i]));
////            for(int j=0;j<N;j++){
////                System.out.print(map[i][j]+" ");
////            }
//            System.out.println();
//        }
//
//        System.out.println(alist);
        PriorityQueue<Line> lineList = new PriorityQueue<>((a,b)-> a.distance-b.distance);

        for(int z = 0 ; z<alist.size();z++){
            Node node = alist.get(z);
            Queue<Integer> xq = new LinkedList<>();
            Queue<Integer> yq = new LinkedList<>();
            yq.add(node.y);
            xq.add(node.x);
//            System.out.println("["+node.y + " " + node.x+"]");
            boolean[][] visits = new boolean[N][N];
            int[][] distanceMap = new int[N][N];
            int distance = 0;
            while(!xq.isEmpty()){
                int size = xq.size();

                for(int i=0;i<size;i++){
                    int cx = xq.poll();
                    int cy = yq.poll();

                    if(visits[cy][cx]){
                        continue;
                    }
                    visits[cy][cx] = true;
                    distanceMap[cy][cx] = distance;

                    for(int[] direction : directions){
                        int dy = direction[0];
                        int dx = direction[1];
                        int ny = cy + dy;
                        int nx = cx + dx;

                        if(ny<0||nx<0|| ny>=N|| nx>=N){
                            continue;
                        }
                        if(visits[ny][nx]){
                            continue;
                        }
                        if(map[ny][nx]=='1'){
                            continue;
                        }
                        distanceMap[ny][nx] = distance;
                        xq.add(nx);
                        yq.add(ny);
                    }
                }
                distance++;
            }


//            for(int i=0;i<N;i++) {
////                System.out.println(Arrays.toString(map[i]));
//                for (int j = 0; j < N; j++) {
//                    System.out.print(distanceMap[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();


            for(int j=z+1;j<alist.size();j++){
                int ty = alist.get(j).y;
                int tx = alist.get(j).x;
                if(distanceMap[ty][tx]!=0){
                    lineList.add(new Line(node.y,node.x,ty,tx,distanceMap[ty][tx]));
                }
            }
        }
//        System.out.println(lineList);
        int[] parents = init(M+1);
        int cnt = 0;
        int cost = 0;


        while(cnt<M){
            if(lineList.isEmpty()){
                System.out.println(-1);
                return;
            }
            Line line = lineList.poll();
            int startY = line.startY;
            int startX = line.startX;

            int endY = line.endY;
            int endX = line.endX;

            int nodeA = 0;
            int nodeB = 0;
            for(int i=0;i<alist.size();i++){
                if(alist.get(i).y ==startY &&alist.get(i).x ==startX){
                    nodeA = i;
                }
                if(alist.get(i).y ==endY &&alist.get(i).x ==endX) {
                    nodeB = i;
                }
            }
            if(union(nodeA,nodeB,parents)){

                cnt++;
                cost += line.distance;
            }
        }
        System.out.println(cost);
    }

    public static int[] init(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = i;
        }
        return arr;
    }

    public static int findRoot(int value, int[] parents){
        if(value== parents[value]){
            return value;
        }
        return parents[value] = findRoot(parents[value],parents);
    }

    public static boolean union(int valA, int valB, int[] parents){
        int rootA = findRoot(valA,parents);
        int rootB = findRoot(valB,parents);
        if(rootB == rootA){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }


    public static class Line{
        int startY;
        int startX;
        int endY;
        int endX;
        int distance;
        Line(int startY, int startX, int endY, int endX, int distance){
            this.startX = startX;
            this.startY = startY;
            this.endY = endY;
            this.endX = endX;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "startY=" + startY +
                    ", startX=" + startX +
                    ", endY=" + endY +
                    ", endX=" + endX +
                    ", distance=" + distance +
                    '}';
        }
    }

    public static class Node{
        char type;
        int y;
        int x;
        Node(int y,int x,char type){
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }
}
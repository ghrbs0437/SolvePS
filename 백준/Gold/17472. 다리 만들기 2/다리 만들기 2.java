import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 섬을 구한다.
        // 2. 섬간의 거리를 구하여 간선화한다.
        // 3. 크루스칼? 경우의수 완탐? 둘중하나.

        String[] strs = br.readLine().split(" ");
        int ySize = Integer.parseInt(strs[0]);
        int xSize = Integer.parseInt(strs[1]);

        int[][] map = new int[ySize][xSize];
        for(int i=0;i<ySize;i++){
           strs = br.readLine().split(" ");
           for(int j=0;j<xSize;j++){
               map[i][j] = Integer.parseInt(strs[j]);
           }
        }

        int sectionIndex = 1;
        ArrayList<Island> alist = new ArrayList<>();
        int[][] section = new int[ySize][xSize];
        for(int i=0;i<ySize;i++){
            for(int j=0;j<xSize;j++){
                if(section[i][j]!=0){
                    continue;
                }
                if(map[i][j]==0){
                    continue;
                }

                Queue<Integer> yq = new ArrayDeque<>();
                Queue<Integer> xq = new ArrayDeque<>();
                yq.add(i);
                xq.add(j);

                while(!xq.isEmpty()){
                    int size = yq.size();
                    for(int g =0 ; g<size ; g++){
                        int cx = xq.poll();
                        int cy = yq.poll();
                        if(map[cy][cx]==0){
                            continue;
                        }
                        if(section[cy][cx] != 0){
                            continue;
                        }
                        section[cy][cx] = sectionIndex;
                        for(int[] direction : directions){
                            int dy = direction[0];
                            int dx = direction[1];
                            int ny = cy + dy;
                            int nx = cx + dx;
                            if(ny<0||nx<0||ny>=ySize||nx>=xSize){
                                continue;
                            }
                            if(map[ny][nx]==0){
                                continue;
                            }
                            xq.add(nx);
                            yq.add(ny);
                        }
                    }
                }
                sectionIndex++;
            }
        }

//        for(int i=0;i<ySize;i++){
//            for(int j=0;j<xSize;j++){
//                System.out.print(section[i][j]+ " ");
//            }
//            System.out.println();
//        }


        ArrayList<Line> lineList = new ArrayList<>();

        for(int i=0;i<ySize;i++){
            boolean startFlag = false;
            int distance = 0;
            int startIndex = 0;
            int endIndex = 0;
            for(int j=0;j<xSize;j++){
                if(!startFlag&&section[i][j]!=0){
                    startIndex = section[i][j];
                    startFlag = true;
                    continue;
                }

                if(startFlag && section[i][j] == 0){
                    distance ++;
                }else if(startFlag && section[i][j] !=startIndex){
                    if(distance>1){
                        endIndex = section[i][j];
                        lineList.add(new Line(startIndex,endIndex,distance));
                    }
                    // LINE이 중복발생할수도 있어..
                    distance =0;
                    startIndex = section[i][j];
                }else{
                    startIndex = section[i][j];
                    distance = 0;
                }
            }
        }

        for(int i=0;i<xSize;i++){
            boolean startFlag = false;
            int distance = 0;
            int startIndex = 0;
            int endIndex = 0;
            for(int j=0;j<ySize;j++){
                if(!startFlag&&section[j][i]!=0){
                    startIndex = section[j][i];
                    startFlag = true;
                    continue;
                }

                if(startFlag && section[j][i] == 0){
                    distance ++;
                }else if(startFlag && section[j][i] !=startIndex){
                    if(distance>1){
                        endIndex = section[j][i];
                        lineList.add(new Line(startIndex,endIndex,distance));
                    }
                    // LINE이 중복발생할수도 있어..
                    distance = 0;
                    startIndex = section[j][i];
                }else{
                    startIndex = section[j][i];
                    distance = 0;
                }
            }
        }

        Collections.sort(lineList,(a,b)->{
            return a.distance - b.distance;
        });

        int cnt = 0;
        int sum = 0;
        int[] parents = init(sectionIndex+1);
        for(Line line : lineList){
//            System.out.println(line);
            int start = line.start;
            int end = line.end;
            int value = line.distance;
            if(union(start,end,parents)){
                cnt++;
                sum+= value;
            }
            if(cnt == sectionIndex-2){
                System.out.println(sum);
                return;
            }
        }
        System.out.println(-1);
    }

    public static boolean union(int val1,int val2 , int[]parents){
        int rootA = findRoot(val1,parents);
        int rootB = findRoot(val2,parents);
        if(rootA == rootB){
            return false;
        }
        parents[rootB] = rootA;
        return true;
    }

    public static int findRoot(int value , int[] parents){
        if(value == parents[value]){
            return value;
        }
        return parents[value] = findRoot(parents[value],parents);
    }

    public static int[] init(int size){
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = i;
        }
        return arr;
    }



    public static class Line{
        int start;
        int end;
        int distance;
        Line(int start,int end,int distance){
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        public String toString(){
            return "[start : "+start+",end :" + end + ", dis : "+distance+"]";
        }
    }

    public static class Island{
        int minX;
        int maxX;
        int minY;
        int maxY;
        int index;
        Island(int minY,int minX,int maxY,int maxX,int index){
            this.minY = minY;
            this.minX = minX;
            this.maxY = maxY;
            this.maxX = maxX;
            this.index = index;
        }
    }
}
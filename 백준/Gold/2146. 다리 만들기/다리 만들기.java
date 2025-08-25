import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int size = Integer.parseInt(br.readLine());

        int[][] map = new int[size][size];

        for(int i=0;i<size;i++){
            String[] strs = br.readLine().split(" ");
            for(int j=0;j<size;j++){
                map[i][j] = Integer.parseInt(strs[j]);
            }
        }

        Queue<Integer> yq = new ArrayDeque<>();
        Queue<Integer> xq = new ArrayDeque<>();
        HashMap<Integer,ArrayList<Node>> hmap = new HashMap<>();
        int num = 2;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(map[i][j] == 1){
                    yq.add(i);
                    xq.add(j);
                    hmap.put(num,new ArrayList<>());

                    while(!yq.isEmpty()){
                        int qSize = yq.size();
                        for(int k=0;k<qSize;k++){
                            int cy = yq.poll();
                            int cx = xq.poll();
                            if(map[cy][cx] == 1){
                                map[cy][cx] = num;
                                Node node = new Node(cy,cx);
                                hmap.get(num).add(node);
                            }else{
                                continue;
                            }

                            for(int[] direction :directions){
                                int dy = direction[0];
                                int dx = direction[1];
                                int ny = dy + cy;
                                int nx = dx + cx;

                                if(ny<0||nx<0||ny>=size||nx>=size){
                                    continue;
                                }
                                if(map[ny][nx]==1){
                                    yq.add(ny);
                                    xq.add(nx);
                                }
                            }
                        }
                    }
                    num++;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        boolean[][] visits = new boolean[size][size];

        for(int i=2;i<num;i++){
            ArrayList<Node> nodes = hmap.get(i);
            yq = new ArrayDeque<>();
            xq = new ArrayDeque<>();
            for(int j=0;j<size;j++){
                for(int k=0;k<size;k++){
                    visits[j][k] = false;
                }
            }
            for(Node node : nodes){
                yq.add(node.y);
                xq.add(node.x);
            }

            int cnt = 0;
            wh: while(!yq.isEmpty()){
                int qSize = yq.size();
                for(int k=0;k<qSize;k++){
                    int cy = yq.poll();
                    int cx = xq.poll();

                    if(visits[cy][cx]){
                        continue;
                    }
                    visits[cy][cx] = true;
                    if(map[cy][cx] !=0 && map[cy][cx]!=i){
                        answer = Math.min(cnt,answer);
                        break wh;
                    }

                    for(int[] direction : directions){
                        int ny = cy + direction[0];
                        int nx = cx + direction[1];

                        if(ny<0||nx<0||ny>=size||nx>=size){
                            continue;
                        }
                        if(map[ny][nx] == i){
                            continue;
                        }
                        if(visits[ny][nx]){
                            continue;
                        }
                        yq.add(ny);
                        xq.add(nx);
                    }
                }
                cnt++;
            }


        }

        System.out.println(answer-1);

    }

    public static class Node{
        int y;
        int x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

}

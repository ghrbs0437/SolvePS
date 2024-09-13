import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        for(int tc=0;tc<testCase;tc++){
            int answer = 0;
            HashSet<Character> containsKey = new HashSet<>();
            HashMap<Character, LinkedList<Position>> hmap = new HashMap<>();

            String[] strs = br.readLine().split(" ");
            int ySize = Integer.parseInt(strs[0]);
            int xSize = Integer.parseInt(strs[1]);

            char[][] map = new char[ySize][xSize];
            ArrayList<Position> startPos = new ArrayList<>();
            for(int i=0;i<ySize;i++){
                String str = br.readLine();
                for(int j=0;j<xSize;j++){
                    map[i][j] = str.charAt(j);
                    if(i==0 || j==0 || i==ySize-1 || j==xSize-1){
                        if(Character.isLowerCase(map[i][j])){ // 열쇠
                            containsKey.add(map[i][j]);
                            map[i][j] = '.';
                        }else if(Character.isUpperCase(map[i][j])){ // 문
                            if(hmap.get(map[i][j])==null){
                                hmap.put(map[i][j],new LinkedList<>());
                            }
                            hmap.get(map[i][j]).add(new Position(i,j));
                        }else if(map[i][j] == '$'){ // 정답
                            map[i][j] = '.';
                            answer++;
                        }
                        if(map[i][j] == '.'){
                            startPos.add(new Position(i,j));
                        }
                    }
                }
                

            }

            String str = br.readLine();
            for(char c : str.toCharArray()){
                containsKey.add(c);
            }


            Queue<Integer> xq = new ArrayDeque<>();
            Queue<Integer> yq = new ArrayDeque<>();
            for(Position pos : startPos){
                yq.add(pos.y);
                xq.add(pos.x);
            }
            boolean[][] visits = new boolean[ySize][xSize];


            while(true){
                int size = xq.size();
                if(size==0){
                    for(Character key : containsKey){
                        if(hmap.get(Character.toUpperCase(key))==null){
                            continue;
                        }
                        for(Position pos : hmap.get(Character.toUpperCase(key))){
                            if(visits[pos.y][pos.x]){
                                continue;
                            }
                            map[pos.y][pos.x] = '.';
                            yq.add(pos.y);
                            xq.add(pos.x);
                        }
                    }
                }
                if(xq.isEmpty()){
                    break;
                }

                for(int i=0;i<size;i++){
                    int cx = xq.poll();
                    int cy = yq.poll();

                    if(visits[cy][cx]){
                        continue;
                    }

                    visits[cy][cx] = true;


                    for(int[] direction : directions){
                        int dy = direction[0];
                        int dx = direction[1];
                        int ny = cy + dy;
                        int nx = cx + dx;
                        if(ny>=ySize || nx>=xSize || ny<0|| nx<0){
                            continue;
                        }

                        if(map[ny][nx]=='.'){
                            xq.add(nx);
                            yq.add(ny);
                        }else if(Character.isUpperCase(map[ny][nx])){
                            if(hmap.get(map[ny][nx])==null){
                                hmap.put(map[ny][nx],new LinkedList<>());
                            }
                            hmap.get(map[ny][nx]).add(new Position(ny,nx));
                        }else if(Character.isLowerCase(map[ny][nx])){
                            containsKey.add(map[ny][nx]);
                            map[ny][nx] = '.';
                            yq.add(ny);
                            xq.add(nx);
//                            System.out.println(containsKey);
                        }else if(map[ny][nx]=='$'){
                            map[ny][nx] = '.';
                            yq.add(ny);
                            xq.add(nx);
                            answer++;
                        }
                    }
                }
            }
            sb.append(answer+"\n");

        }
        System.out.println(sb);

    }

    public static class Position{
        int y;
        int x;
        Position(int y,int x){
            this.y = y;
            this.x = x;
        }
    }
}
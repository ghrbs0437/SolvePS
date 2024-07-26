import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 보드의 크기
        int N = Integer.parseInt(br.readLine());
        // 사과의 개수
        int K = Integer.parseInt(br.readLine());


        int[][] arr = new int[N][N];

        // 사과의 좌표
        for (int i = 0; i < K; i++) {
            String[] strs = br.readLine().split(" ");
            int x = Integer.parseInt(strs[0]);
            int y = Integer.parseInt(strs[1]);
            arr[x - 1][y - 1] = 1;
        }
        int L = Integer.parseInt(br.readLine());

        // L 은 명령어들의 집합..
        PriorityQueue<Command> pq = new PriorityQueue<>((a, b) -> a.time - b.time);
        for (int i = 0; i < L; i++) {
            String[] strs = br.readLine().split(" ");
            int time = Integer.parseInt(strs[0]);
            String Direction = strs[1];
            pq.add(new Command(time, Direction));
        }

        int time = 1;
        Snake snake = new Snake(arr, N);
        while (true) {
            Command comm = pq.peek();
            if (!snake.move()) {
                System.out.println(time);
                return;
            }
            if(comm == null){
                time++;
                continue;
            }
            if (time == comm.time) {
                if (comm.command.equals("D")) {
                    snake.direction = snake.direction.rightRotation();
                } else {
                    snake.direction = snake.direction.leftRotation();
                }
                pq.poll();
            }
            time++;
        }


    }

    public static class Snake {
        int[][] map;
        int mapSize;
        Direction direction = Direction.right;
        LinkedList<Position> list = new LinkedList<>();

        Snake(int[][] map, int N) {
            this.map = map;
            this.mapSize = N;
            list.add(new Position(0, 0));
        }

        public boolean move() {
            // 일단 늘려
            if (!extend()) {
                return false;
            }
            // 사과가 있으면 줄이지 않아
            Position pos = list.getLast();
            if (!(map[pos.y][pos.x] == 1)) {
                shrink();
            }
            map[pos.y][pos.x] = 0;
            return true;
        }

        public boolean extend() {
            int curX = list.getLast().x;
            int curY = list.getLast().y;
            if (direction.dx + curX < 0 || direction.dx + curX >= mapSize || direction.dy + curY < 0 || direction.dy + curY >= mapSize) {
                return false;
            }
            // 현재 몸상태 체크

            Position nextPos = new Position(direction.dx + curX, direction.dy + curY);
            if (list.contains(nextPos)) {
                return false;
            }

            list.addLast(nextPos);
            return true;
        }

        public void shrink() {
            list.removeFirst();
        }

    }

    public enum Direction {
        right(0, 1), up(1, 0), left(0, -1), down(-1, 0);
        static Direction[] di = Direction.values();
        int dx;
        int dy;

        Direction(int dy, int dx) {
            this.dx = dx;
            this.dy = dy;
        }

        Direction leftRotation() {
            return di[(ordinal() + 3) % 4];
        }

        Direction rightRotation() {
            return di[(ordinal() + 1) % 4];
        }
    }
    public static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Position) {
                Position pos = (Position)o;
                if (this.x != pos.x) {
                    return false;
                }
                if (this.y != pos.y) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public static class Command {
        int time;
        String command;

        Command(int time, String command) {
            this.time = time;
            this.command = command;
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int SIZE = 1001;
        int offSet = SIZE / 2;

        int[][] parents = init(SIZE);
        boolean[][] visits = new boolean[SIZE][SIZE];

        for (int i = 0; i < N; i++) {
            String[] strs = br.readLine().split(" ");
            int startX = Integer.parseInt(strs[0]) + offSet;
            int startY = Integer.parseInt(strs[1]) + offSet;
            int endX = Integer.parseInt(strs[2]) + offSet;
            int endY = Integer.parseInt(strs[3]) + offSet;

            // 직사각형의 네 변을 모두 방문 처리하고, 각 좌표를 union 처리
            // 왼쪽과 오른쪽 경계
            for (int y = startY; y <= endY; y++) {
                visits[y][startX] = true;
                visits[y][endX] = true;
                if (y > startY) {
                    union((y - 1) * SIZE + startX, y * SIZE + startX, parents); // 왼쪽 변 연결
                    union((y - 1) * SIZE + endX, y * SIZE + endX, parents);     // 오른쪽 변 연결
                }
            }
            // 위쪽과 아래쪽 경계
            for (int x = startX; x <= endX; x++) {
                visits[startY][x] = true;
                visits[endY][x] = true;
                if (x > startX) {
                    union(startY * SIZE + (x - 1), startY * SIZE + x, parents); // 위쪽 변 연결
                    union(endY * SIZE + (x - 1), endY * SIZE + x, parents);     // 아래쪽 변 연결
                }
            }
        }

        // 부모 집합을 HashSet으로 관리하여 고유한 집합 개수 계산
        HashSet<Integer> hset = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (visits[i][j]) {
                    hset.add(findRoot(i * SIZE + j, parents)); // 경로 압축된 부모 추가
                }
            }
        }

        // (0, 0)에서 출발할 때 PU 명령이 필요한지 확인
        if (visits[offSet][offSet]) {
            System.out.println(hset.size() - 1); // (0, 0)이 직사각형에 포함되어 있으면 PU 필요 없음
        } else {
            System.out.println(hset.size()); // (0, 0)이 직사각형에 포함되지 않으면 PU 필요
        }
    }

    // 부모 배열 초기화 함수
    public static int[][] init(int size) {
        int[][] parents = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                parents[i][j] = i * size + j; // 자신을 부모로 설정
            }
        }
        return parents;
    }

    // findRoot 함수 (경로 압축 포함)
    public static int findRoot(int a, int[][] parents) {
        int y = a / parents.length;
        int x = a % parents.length;

        if (parents[y][x] == a) {
            return a;
        }
        // 경로 압축
        return parents[y][x] = findRoot(parents[y][x], parents);
    }

    // union 함수 (두 좌표의 부모를 병합)
    public static boolean union(int a, int b, int[][] parents) {
        int rootA = findRoot(a, parents);
        int rootB = findRoot(b, parents);

        if (rootA == rootB) {
            return false; // 이미 같은 집합이면 병합할 필요 없음
        }

        // 더 작은 값이 부모가 되도록 병합
        if (rootA < rootB) {
            parents[rootB / parents.length][rootB % parents.length] = rootA;
        } else {
            parents[rootA / parents.length][rootA % parents.length] = rootB;
        }

        return true;
    }
}
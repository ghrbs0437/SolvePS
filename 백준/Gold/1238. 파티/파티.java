import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strs = br.readLine().split(" ");
		// 마을개수
		int N = Integer.parseInt(strs[0]);
		// 간선개수
		int M = Integer.parseInt(strs[1]);
		// 목표마을인덱스
		int X = Integer.parseInt(strs[2]) - 1;

		int[][] inArr = new int[N][N];
		int[][] outArr = new int[N][N];

		HashMap<Integer, ArrayList<Integer>> startMap = new HashMap<>();
		HashMap<Integer, ArrayList<Integer>> endMap = new HashMap<>();
		for (int i = 0; i < M; i++) {
			strs = br.readLine().split(" ");
			int start = Integer.parseInt(strs[0]) - 1;
			int end = Integer.parseInt(strs[1]) - 1;
			int value = Integer.parseInt(strs[2]);
			inArr[start][end] = value;
			outArr[start][end] = value;
			if (startMap.get(start) == null) {
				startMap.put(start, new ArrayList<Integer>());
			}
			if (endMap.get(end) == null) {
				endMap.put(end, new ArrayList<>());
			}
			endMap.get(end).add(start);
			startMap.get(start).add(end);
		}
		//	x에서 모든정점을 방문하는 다익스트라..
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
			return a.value - b.value;
		});

		// X에서 출발하는거 먼저 구해
		for (Integer i : startMap.get(X)) {
			pq.add(new Node(i, inArr[X][i]));
		}
		boolean[] visits = new boolean[N];
		visits[X] = true;

		while (!pq.isEmpty()) {
			Node next = pq.poll();// 최소비용 노드 선택
			if (visits[next.index]) { // 근데 이미 방문한건 뺴고..
				continue;
			}
			visits[next.index] = true; // 선택한거 방문처리하고
			ArrayList<Integer> alist = startMap.get(next.index); // 그거랑 연결된 노드들 최소값 갱신

			for (Integer i : alist) {
				if (visits[i]) {
					continue;
				}
//                System.out.println("[X :"+X + " || I : "+i);
				int directRoute = inArr[X][i]; // 0이면 그건 무한대야..
				int delayRoute = inArr[X][next.index] + inArr[next.index][i]; // 절대 0은 아님..

				if (directRoute == 0) {
					inArr[X][i] = delayRoute;
				} else {
					if (directRoute < delayRoute) {

					} else {
						inArr[X][i] = delayRoute;
					}
				}
				pq.add(new Node(i, inArr[X][i]));
			}
		}

//      X로 들어오는것을 확인하는 다익스트라 , 즉 X가 목적지인경우
		PriorityQueue<Node> pq2 = new PriorityQueue<>((a, b) -> {
			return a.value - b.value;
		});

		for (Integer i : endMap.get(X)) { // X 가 종점인 정점들
			pq2.add(new Node(i, outArr[i][X]));
		}
		boolean[] visits2 = new boolean[N];
		visits2[X] = true;

		while (!pq2.isEmpty()) {
			;
			Node before = pq2.poll();
			if (visits2[before.index]) {
				continue;
			}
			visits2[before.index] = true;
			ArrayList<Integer> alist = endMap.get(before.index); // 그거랑 연결된 노드들 최소값 갱신해야하지..

			for (Integer i : alist) { // i에서 출발해서 x까지/..
				if (visits2[i]) {
					continue;
				}

				int directRoute = outArr[i][X]; // 0이면 그건 무한대야..
				int delayRoute = outArr[i][before.index] + outArr[before.index][X]; // 절대 0은 아님..

				if (directRoute == 0) {
					outArr[i][X] = delayRoute;
				} else {
					if (directRoute > delayRoute) {
						outArr[i][X] = delayRoute;
					}
				}
				pq2.add(new Node(i, outArr[i][X]));
			}

		}

//
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(outArr[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(inArr[i][j] + " ");
//			}
//			System.out.println();
//		}

//    System.out.println();
//    for(int i=0;i<N;i++){
//        for(int j=0;j<N;j++){
//            System.out.print(inArr[i][j]+outArr[j][i]+" ");
//        }
//        System.out.println();
//    }
//    
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (max < inArr[X][i] + outArr[i][X]) {
				max = inArr[X][i] + outArr[i][X];
			}
		}

		System.out.println(max);

	}

	public static class Node {
		int value;
		int index;

		Node(int index, int value) {
			this.value = value;
			this.index = index;
		}

		public String toString() {
			return "[" + index + " : " + value + "]";
		}
	}
}
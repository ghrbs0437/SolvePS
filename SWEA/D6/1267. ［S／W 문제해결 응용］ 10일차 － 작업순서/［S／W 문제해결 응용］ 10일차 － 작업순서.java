import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			String[] strs = br.readLine().split(" ");

			int maxNumber = Integer.parseInt(strs[0]);
			int lineCnt = Integer.parseInt(strs[1]);

			strs = br.readLine().split(" ");
			HashMap<Integer, ArrayList<Integer>> startMap = new HashMap<>();
			int[] indgree = new int[maxNumber + 1];
			boolean[] visits = new boolean[maxNumber+1];
	
			for (int i = 0; i < lineCnt; i++) {
				int start = Integer.parseInt(strs[i * 2]);
				int end = Integer.parseInt(strs[i * 2 + 1]);

				if (startMap.get(start) == null) {
					startMap.put(start, new ArrayList<>());
				}

				if (startMap.get(end) == null) {
					startMap.put(end, new ArrayList<>());
				}
				indgree[end]++;
				startMap.get(start).add(end);
			}
            
			Queue<Integer> queue = new ArrayDeque<>();
			for (int i = 1; i <= maxNumber; i++) {
				if (indgree[i] == 0&&startMap.get(i)!=null) {
					queue.add(i);
					visits[i] = true;
				}
			}

			sb.append("#" + tc);
			
			while (!queue.isEmpty()) {
				int current = queue.poll();
				sb.append(" " + current);
				for (int next : startMap.get(current)) {
					indgree[next]--;
					if (indgree[next] == 0) {
						queue.add(next);
						visits[next] = true;
					}
				}
			}
			for(int i=1;i<visits.length;i++) {
				if(!visits[i]) {
					sb.append(" "+i);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
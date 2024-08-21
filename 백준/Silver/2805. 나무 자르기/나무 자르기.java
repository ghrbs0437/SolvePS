import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		
		String[] strs = br.readLine().split(" ");
		
		int N = Integer.parseInt(strs[0]);
		int M = Integer.parseInt(strs[1]);
		
		strs = br.readLine().split(" ");
		int[] arr = new int[N];
		
		int maxWoodLength = 0;
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(strs[i]);
			if(maxWoodLength<arr[i]) {
				maxWoodLength = arr[i];
			}
		}
		
		
		int height = 0;
		int dHeight = maxWoodLength;
		
		int answer = 0;
		
		HashSet<Integer> visits = new HashSet<>();
		checkCycle : while(true) {
			if(dHeight!=1) {
				dHeight>>=1;
			}else {
				if(visits.contains(height)) {
					break;
				}
			}
//			System.out.println(height);
			visits.add(height);
			int sum = 0;
			for(int i=0;i<N;i++) {
				if(arr[i]<height) {
					continue;
				}
				
				sum+=arr[i]-height;
				if(sum<0) { // 오버플로우 발생.. 톱의 높이를 높여라.
					height += dHeight;
					continue checkCycle;
				}

//				최적화
//				if(sum>=M) { // 톱의 높이를 높여라. 
//					answer = height;
//				}
			}
			if(sum<M) { // 높의 높이를 낮춰라.
				height -= dHeight;
			}else if(sum>=M){ // 톱의 높이를 높여라.
//				System.out.println(height);
				answer = Math.max(height, answer);
				height += dHeight;
			}
		}
		
		System.out.println(answer);
		
		
		
	}
}
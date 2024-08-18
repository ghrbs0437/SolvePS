import java.util.*;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution{
	public static void main(String args[]) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++){
            int N =  sc.nextInt(); // 총 주머니의 개수
            int K = sc.nextInt(); // 사용 주머니의 개수
            // 목표 > 사용 주머니중 그 개수의 최대값 - 최소값이 최소가 되게 하는것
            int[] arr = new int[N];
            for(int i=0;i<N;i++){
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            
            int startIndex = 0;
            int min = Integer.MAX_VALUE;
            for(int i=0;i<N-K+1;i++){
                if(min> arr[i+K-1] - arr[i]){
                    min = arr[i+K-1] - arr[i];
                }
            }
            
            System.out.println("#"+test_case+" "+min);
		}
	}
}
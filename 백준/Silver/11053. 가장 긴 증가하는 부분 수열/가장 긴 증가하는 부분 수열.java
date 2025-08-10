import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int length = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		String[] strs = str.split(" ");
		int[] arr = new int[length];
		for(int i=0;i<length;i++) {
			arr[i] = Integer.parseInt(strs[i]);
		}
		int[] maxArr = new int[length];

		maxArr[length-1] = 1;
		getAnswer(arr,maxArr,length-2,length);
		int max = 1;
		for(int i : maxArr) {
			max = max>i?max:i;
		}
		System.out.println(max);
    }
	
	
	/*
	 * arr2 = 그 index에서 가능한 최고점수
	 */
	public static void getAnswer(int[] arr, int[] maxArr,int index,int length) {
		if(index==-1) {
			return;
		}
		for(int i=index;i<length;i++) {
			// 순회중인것이 더 클경우
			if(arr[index] < arr[i]) {
				maxArr[index] = Math.max(maxArr[index],maxArr[i] + 1);
			}
			// 순회중인것과 같은경우
			if(arr[index] == arr[i]) {
				maxArr[index] = Math.max(maxArr[index],maxArr[i]);
			}
			// 현재값이 더 큰경우
			if(arr[index] > arr[i]) {
				maxArr[index] = Math.max(maxArr[index],1);
			}
		}
		getAnswer(arr,maxArr,index-1,length);
		return;
	}
}
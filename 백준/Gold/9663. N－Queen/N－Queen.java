import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int answer = 0 ;
	static int[][] delta = {{0,-1},{0,1},{-1,0},{1,0},{-1,-1},{-1,1},{1,-1},{1,1}}; //8방위
	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] arr = new int[N][N];
		int[][] range = new int[N][N];
		for(int i=0;i<N;i++) {
			back(arr,range,i,0,1,N); // x를 증가시킨다..
		}
		System.out.println(answer);
		
		
    }

	
	// count = 체스말의 수, N 둬야하는 체스말의 수
	public static void back(int[][] arr,int[][] range, int y,int x, int count, int N) {

		if(count==N) {
			answer++;
			return;
		}

		range[y][x]++;
		for(int[] del : delta) {
			int tmpY = y;
			int tmpX = x;
			while(true) {
				if(tmpY+del[0]==N || tmpY+del[0]==-1 || tmpX+del[1]==N || tmpX+del[1]==-1) {
					break;
				}
				tmpY +=del[0];
				tmpX +=del[1];
				range[tmpY][tmpX]++;
			}
		}
		
		for(int i=0;i<N;i++) {
			if(range[i][x+1]==0) {
				back(arr,range,i,x+1,count+1,N);

			}
		}		
		
		for(int[] del : delta) {
			int tmpY = y;
			int tmpX = x;
			while(true) {
				if(tmpY+del[0]==N || tmpY+del[0]==-1 || tmpX+del[1]==N || tmpX+del[1]==-1) {
					break;
				}
				tmpY +=del[0];
				tmpX +=del[1];
				range[tmpY][tmpX]--;
			}
		}
		range[y][x]--;
	
	}
	
}
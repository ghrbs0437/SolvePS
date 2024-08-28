import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] strs = br.readLine().split(" ");

        // 총 카드수 ( 최대 번호수) (~400만)
        int N = Integer.parseInt(strs[0]);

        // 선택 카드수
        int M = Integer.parseInt(strs[1]);

        // 싸움수 (~1만)
        int K = Integer.parseInt(strs[2]);

        boolean[] arr = new boolean[N+1];

        strs = br.readLine().split(" ");
        for(int i=0;i<strs.length;i++){
            int val = Integer.parseInt(strs[i])-1;
            arr[val] = true;
        }
        ArrayList<Integer> alist = new ArrayList<>();
        for(int i=1;i<arr.length;i++){
            if(arr[i]){
                alist.add(i);
            }
        }
        int[] parents = init(alist,N+1);
//        System.out.println(Arrays.toString(parents));
        strs = br.readLine().split(" ");

        for(int i=0;i< strs.length;i++){
            int enemy = Integer.parseInt(strs[i]);
            int userCard = findRoot(enemy,parents);
            sb.append(userCard+1+"\n");
//            System.out.println(Arrays.toString(parents));
            changeRoot(userCard,parents);
        }
        System.out.println(sb);

    }

    public static void changeRoot(int userCard, int[] parents){
        if(userCard==parents.length){
            return;
        }
//        System.out.println(parents[userCard-1]+" "+parents[userCard]);
        parents[userCard-1] = parents[userCard];
    }

    public static int findRoot(int number, int[] parents){
        if(number == parents[number-1]){
            return parents[number-1];
        }else{
            return findRoot(parents[number],parents);
        }
    }

    public static int[] init(ArrayList<Integer> alist,int size){
        int[] arr = new int[size];
        int past = 0;

        for(Integer current : alist){
            // past~ current까지 다 current.
            for(int i=past;i<current;i++){
                arr[i] = current;
            }
            past = current;
        }
//        for(int i = past ; i<arr.length;i++){
//            arr[i] = Integer.MAX_VALUE;
//        }

        return arr;
    }
}
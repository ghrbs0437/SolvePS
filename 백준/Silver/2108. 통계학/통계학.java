import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int sum = 0;
        for(int i=0;i<N;i++){
            sum+=arr[i];
        }

        HashMap<Integer, Integer> hmap = new HashMap<>();
        for(int i=0;i<N;i++){
            if(hmap.get(arr[i])==null){
                hmap.put(arr[i],0);
            }
            hmap.put(arr[i],hmap.get(arr[i])+1);
        }

        int max = 0;
        ArrayList<Integer> alist = new ArrayList<>();
        for(Integer i : hmap.keySet()){
            if(max<hmap.get(i)){
                max = hmap.get(i);
                alist = new ArrayList<>();
                alist.add(i);
            }else if(max == hmap.get(i)){
                alist.add(i);
            }
        }
        Collections.sort(alist);
        System.out.println((int)Math.round(sum/(float)N));
        System.out.println(arr[N/2]);
        if(alist.size()>1){
            System.out.println(alist.get(1));
        }else{
            System.out.println(alist.get(0));
        }
        System.out.println(arr[N-1] - arr[0]);
    }
}
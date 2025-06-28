import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] strs = br.readLine().split(" ");

        int N = Integer.parseInt(strs[0]);
        int K = Integer.parseInt(strs[1]);

        // 보석이 N개
        // 보석의 가치 M V
        // 가방이 K개
        // 가방에 최대 무게 C

        ArrayList<Jewel> jewels = new ArrayList<>();

        for(int i=0; i<N;i++){
            strs = br.readLine().split(" ");
            int weight = Integer.parseInt(strs[0]);
            int value = Integer.parseInt(strs[1]);
            Jewel jewel = new Jewel(weight, value);
            jewels.add(jewel);
        }

        ArrayList<Bag> bags = new ArrayList<>();
        for(int i=0;i<K;i++){
            int weight = Integer.parseInt(br.readLine());
            Bag bag = new Bag(weight);
            bags.add(bag);
        }

        // 가방에는 보석이 1개 들어간다.
        // 보석의 가치를 가장 크게 넣는방법??

        // 1. 가방에 하나씩 넣으면서 가방의 보석을 옮기는 방법?
        // 2. 보석을 넣을때 최적 가방을 찾는방법?

        // 1로 가면..
        // 보석의 무게를 무게를 크게 가져가고
        // 큰 배낭에부터 하나씩 넣기 시작하자.
        // 배낭에 담을 수 없는 경우..??
        // 이미 담아져 있는 배낭에서 가치의 최소값을 비교한다음에 이게 더 크다면 최소의 배낭에서 꺼내고 새로운 보석을 넣자..

        Collections.sort(jewels,(a,b)->{
            return b.weight - a.weight;
        });

        PriorityQueue<Bag> unCompleteBag = new PriorityQueue<>((a,b)->{
            return b.weight - a.weight;
        });

        PriorityQueue<Bag> completeBag = new PriorityQueue<>((a,b)->{
            return a.jewel.value - b.jewel.value;
        });


        unCompleteBag.addAll(bags);

        for(Jewel jewel : jewels){
            if(!unCompleteBag.isEmpty()){
                Bag bag = unCompleteBag.peek();
                if(bag.weight >= jewel.weight){ // 넣을 수 있는거지.
                    Bag poll = unCompleteBag.poll();
                    poll.jewel = jewel;
                    completeBag.add(poll);
                    continue;
                }
//                else{ // 못넣는경우, 이미 넣은 배낭에서 확인하자.
//                    if(completeBag.isEmpty()){
//                        continue;
//                    }
//                    if(completeBag.peek().jewel.value <= jewel.value){ // 가치가 더 크다면? 바꿔야지
//                        Bag poll = completeBag.poll();
//                        poll.jewel = jewel;
//                        completeBag.add(poll);
//                    }
//                }
            }
            if(completeBag.isEmpty()){
                continue;
            }
            if(completeBag.peek().jewel.value <= jewel.value){ // 가치가 더 크다면? 바꿔야지
                Bag poll = completeBag.poll();
                poll.jewel = jewel;
                completeBag.add(poll);
            }

        }

        long sum = 0;
        for(Bag bag : completeBag){
            sum += bag.jewel.value;
        }
        System.out.println(sum);
        


    }
    public static class Jewel{
        int weight;
        int value;
        boolean used = false;

        public Jewel(int weight,int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static class Bag{
        int weight;
        Jewel jewel;

        public Bag(int weight){
            this.weight = weight;
        }
    }

}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= testCase; tc++) {
            String[] strs = br.readLine().split(" ");
            int n = Integer.parseInt(strs[0]); // 부품의 개수
            int money = Integer.parseInt(strs[1]); // 상근이의 예산 (10억)
            // type name price quality
            HashMap<String,PriorityQueue<Token>> hmap = new HashMap<>();
            for(int i=0;i<n;i++) {
                strs = br.readLine().split(" ");
                String type = strs[0];
                String name = strs[1];
                String price = strs[2];
                String quality = strs[3];
                Token token = new Token(type,name,price,quality);
                if(hmap.get(type)==null){
                    hmap.put(type,new PriorityQueue<>((a,b)->a.price-b.price));
                }
                hmap.get(type).add(token);
            }

            PriorityQueue<Token> tokens = new PriorityQueue<>((a,b)->a.quality- b.quality);

            Set<String> keys = hmap.keySet();
            Integer prices = 0;
            Integer performance = Integer.MAX_VALUE;
            for(String key : keys){
                Token poll = hmap.get(key).poll();
                prices += poll.price;
                tokens.add(poll);
            }

            while(!tokens.isEmpty()){
                Token current = tokens.peek();
                performance = current.quality;
                Token next = hmap.get(current.type).poll();
                if(next == null){
                    break;
                }
                if(next.price < current.price){ // 지금게 더 비싸면 성능구린거 아니면
                    if(next.quality > current.quality){
                        tokens.poll();
                        tokens.add(next);
                        prices -= current.price;
                        prices += next.price;
                    }
                }else{
                    if(next.quality > current.quality){
                        if(prices - current.price + next.price > money){
                        }else{
                            tokens.poll();
                            tokens.add(next);
                            prices -= current.price;
                            prices += next.price;
                        }
                    }
                }
            }
            System.out.println(performance);
        }
    }

    public static class Token{
        String type;
        String name;
        int price;
        int quality;

        Token(String type, String name, String price, String quality){
            this.type = type;
            this.name = name;
            this.price = Integer.parseInt(price);
            this.quality = Integer.parseInt(quality);
        }

    }
}
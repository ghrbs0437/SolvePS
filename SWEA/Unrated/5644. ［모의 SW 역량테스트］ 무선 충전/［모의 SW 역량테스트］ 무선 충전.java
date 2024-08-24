import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for(int tc =1 ; tc<=testCase;tc++) {
            Map map = new Map();
            String[] strs = br.readLine().split(" ");
            int moveCnt = Integer.parseInt(strs[0]);
            int batteryCnt = Integer.parseInt(strs[1]);

            Person personA = new Person(0,0);
            map.persons[0] = personA;
            strs = br.readLine().split(" ");
            for(int i=0;i<moveCnt;i++) {
                int ordinal = Integer.parseInt(strs[i]);
                Move direction = Move.of(ordinal);
                map.person0Move.add(direction);
            }

            Person personB = new Person(9,9);
            map.persons[1] = personB;
            strs = br.readLine().split(" ");
            for(int i=0;i<moveCnt;i++) {
                int ordinal = Integer.parseInt(strs[i]);
                Move direction = Move.of(ordinal);
                map.person1Move.add(direction);
            }

            for(int i=0;i<batteryCnt;i++) {
                strs = br.readLine().split(" ");
                int x = Integer.parseInt(strs[0])-1;
                int y = Integer.parseInt(strs[1])-1;
                int distance = Integer.parseInt(strs[2]);
                int power = Integer.parseInt(strs[3]);
                BateryCharger bc = new BateryCharger(y, x, power, distance);
                map.chargers.add(bc);
            }

            for(int i=0;i<moveCnt;i++) {
                map.Proceed();
            }
            map.charge();
            sb.append("#"+tc+" " +  (map.persons[0].point+map.persons[1].point) +"\n");
        }
        System.out.println(sb);
    }


    public static class Map{
        Person[] persons = new Person[2];
        ArrayList<BateryCharger> chargers = new ArrayList<>();
        int time = 0;
        ArrayList<Move> person0Move = new ArrayList<>();
        ArrayList<Move> person1Move = new ArrayList<>();

        public void Proceed() {
            charge();
            persons[0].move(person0Move.get(time));
            persons[1].move(person1Move.get(time));
            time++;
        }

        private void charge() {
            PriorityQueue<BateryCharger> chargersOnPerson0 = getPossibleCharger(persons[0]);
            PriorityQueue<BateryCharger> chargersOnPerson1 = getPossibleCharger(persons[1]);

            if(chargersOnPerson0.isEmpty() && chargersOnPerson1.isEmpty()) {
                return;
            }

            if(chargersOnPerson0.isEmpty()) {
                persons[1].point+=chargersOnPerson1.peek().power;
                return;
            }

            if(chargersOnPerson1.isEmpty()) {
                persons[0].point+=chargersOnPerson0.peek().power;
                return;
            }

            // 아래는 이제 적어도 하나의..
            if(chargersOnPerson0.peek().equals(chargersOnPerson1.peek())) {
                chargersOnPerson0.poll();
                BateryCharger goodCharger = chargersOnPerson1.poll();

                if(chargersOnPerson0.isEmpty()&&chargersOnPerson1.isEmpty()) {
                    persons[0].point += goodCharger.power;
                    return;
                }

                if(chargersOnPerson0.isEmpty()) {
                    persons[0].point += goodCharger.power;
                    persons[1].point += chargersOnPerson1.peek().power;
                    return;
                }
                if(chargersOnPerson1.isEmpty()) {
                    persons[0].point += chargersOnPerson0.peek().power;
                    persons[1].point += goodCharger.power;
                    return;
                }

                BateryCharger next0 = chargersOnPerson0.peek();
                BateryCharger next1 = chargersOnPerson1.peek();
                if(next0.power>=next1.power) {
                    persons[0].point += next0.power;
                    persons[1].point += goodCharger.power;
                }else {
                    persons[0].point += goodCharger.power;
                    persons[1].point += next1.power;
                }
            }else {
                BateryCharger BateryCharger0 = chargersOnPerson0.peek();
                persons[0].point += BateryCharger0.power;
                BateryCharger BateryCharger1 = chargersOnPerson1.peek();
                persons[1].point += BateryCharger1.power;
            }
        }

        public PriorityQueue<BateryCharger> getPossibleCharger(Person person) {
            PriorityQueue<BateryCharger> possible = new PriorityQueue<>((a,b)->{
                return -1 * (a.power - b.power);
            });
            for(BateryCharger charger : chargers) {
                if(isOnCharger(person,charger)) {
                    possible.add(charger);
                }
            }
            return possible;
        }

        private boolean isOnCharger(Person person, BateryCharger charger) {
            int num = Math.abs(person.y - charger.y) + Math.abs(person.x - charger.x);
            return charger.range>=num;
        }
    }

    public static class Person{
        int y;
        int x;
        int point = 0;

        Person(int y,int x){
            this.y = y;
            this.x = x;
        }

        public void move(Move direction){
            y+= direction.dy;
            x+= direction.dx;
        }

    }

    public static class BateryCharger{
        int y;
        int x;
        int power;
        int range;
        BateryCharger(int y, int x, int power,int range){
            this.y = y;
            this.x = x;
            this.power = power;
            this.range = range;
        }
    }

    public enum Move{
        STOP(0,0),UP(-1,0),RIGHT(0,1),DOWN(1,0),LEFT(0,-1);
        final int dy;
        final int dx;

        Move(int dy,int dx){
            this.dy = dy;
            this.dx = dx;
        }

        public static Move of(int ordinal) {
            for(Move v : Move.values()) {
                if(v.ordinal()==ordinal) {
                    return v;
                }
            }
            return null;
        }
    }
}
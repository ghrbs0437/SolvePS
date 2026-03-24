import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Wheel[] wheels = new Wheel[4];
        for(int i=0;i<4;i++){
            String str = br.readLine();
            wheels[i] = new Wheel(str);
        }
        for(int i=0;i<3;i++){
            wheels[i].right = wheels[i+1];
            wheels[3-i].left = wheels[2-i];
        }
//
//        System.out.println();
//        for(int i=0;i<4;i++){
//            wheels[i].printPole();
//        }
        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            String[] strs = br.readLine().split(" ");
            int num = Integer.parseInt(strs[0]) - 1;
            int direction = Integer.parseInt(strs[1]);
            wheels[num].rotate(direction);
        }
        int sum = 0;

//        System.out.println();
//        for(int i=0;i<4;i++){
//            wheels[i].printPole();
//        }

        for(int i=0;i<4;i++){
            Wheel wheel = wheels[i];
            if(wheel.poles[(wheel.offset+800)%8]=='S'){
                sum += 1<<i;
            }
        }

        System.out.println(sum);


    }


    public static class Wheel{
        char[] poles = new char[8];
        Wheel left;
        Wheel right;
        int offset = 0;

        void printPole(){
            for(int i=0;i<8;i++){
                System.out.print(poles[(i+800+offset)%8]);
            }
            System.out.println();
        }

        Wheel(String str){
            for(int i=0;i<8;i++){
                if(str.charAt(i)=='0'){
                    poles[i] = 'N';
                }else{
                    poles[i] = 'S';
                }
            }
        }

        void rotate(int direction){
            // direction 1 = 시계방향
            // direction -1 = 반시계방향
            propagateLeft(direction);
            propagateRight(direction);
            offset -= direction;
            // offset 개념 =  0 4 6 위치를 찾아야해.. 그런데 톱니가 돌아간다고 생각하지 말고 화면이 돌아간다고 생각해..
        }
        void propagateLeft(int pastDirection){
            if(left == null){
                return;
            }
            if(this.poles[(offset+800+6)%8] != left.poles[(left.offset+800+2)%8]){
                // 다르면, 영향을 준다..
                int aroundPole = this.poles[(this.offset+800+6)%8];
                if(aroundPole != left.poles[(left.offset+800+2)%8]){
                    int nextDirection = pastDirection * -1;
                    left.propagateLeft(nextDirection);
                    left.offset-=nextDirection;
                }
            }
        }
        void propagateRight(int pastDirection){
            if(right == null){
                return;
            }
            if(this.poles[(offset+800+2)%8] != right.poles[(right.offset+800+6)%8]){
                // 다르면, 영향을 준다..
                int aroundPole = this.poles[(this.offset+800+2)%8];
                if(aroundPole != right.poles[(right.offset+800+6)%8]){
                    int nextDirection = pastDirection * -1;
                    right.propagateRight(nextDirection);
                    right.offset-=nextDirection;
                }
            }
        }
    }

}
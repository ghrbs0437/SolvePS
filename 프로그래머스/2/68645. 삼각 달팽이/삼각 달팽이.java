import java.util.*;


class Solution {
    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        
        Direction currentDir = Direction.down;
        if(n==1){
            int[] temp = new int[1];
            temp[0]=1;
            return temp;
        }
        if(n==2){
            int[] temp = new int[3];
            temp[0] = 1;
            temp[1] = 2;
            temp[2] = 3;
            return temp;
        }
        
        int num = 1;
        int x = 0;
        int y = 0;
        while(true){
            boolean flag = false;
            switch(currentDir){
                case down:
                    arr[x][y] = num;
                    if(x==n-1){
                        currentDir = Direction.right;
                        num--;
                        break;
                    }         
                    if(arr[x+1][y]!=0){
                        currentDir = Direction.right;
                        if(arr[x][y+1]!=0){
                            flag=true;
                        }
                        y++;
                        break;
                    }
                    x++;
                    break;
                case right:
                    arr[x][y] = num;
                    if(y==n-1){
                        currentDir = Direction.up;
                        num--;
                        break;
                    }
                    if(arr[x][y+1]!=0){
                        currentDir = Direction.up;
                        if(arr[x-1][y-1]!=0){
                            flag = true;
                        }
                        x--;
                        y--;
                        break;
                    }
                    y++;
                    break;
                case up:
                    arr[x][y] = num;
                    if(arr[x-1][y-1]!=0){
                        currentDir = Direction.down;
                        if(arr[x+1][y]!=0){
                            flag = true;
                        }
                        x++;
                        break;
                    }
                    x--;
                    y--;
                    break;
            }
            if(flag){
                break;
            }
            num++;
        }
        ArrayList<Integer> alist = new ArrayList<>();
        
//         for(int[] arrs : arr){
//             for(int i : arrs){
//                 System.out.print(i+" ");
//             }
//             System.out.println();
//         }
        
        
        for(int[] arrs : arr){
            for(int i : arrs){
                if(i!=0){
                    alist.add(i);
                }
            }
        }
        
        
        int[] answer = new int[alist.size()];
        for(int i=0;i<alist.size();i++){
            answer[i] = alist.get(i);
        }
        return answer;
    }
    
    public enum Direction{
        down,right,up;
    }
}
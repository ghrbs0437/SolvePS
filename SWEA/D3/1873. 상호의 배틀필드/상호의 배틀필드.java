import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	
	public static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		for(int tc =1 ; tc<=testCase;tc++) {
			String[] strs = br.readLine().split(" ");
			int ySize = Integer.parseInt(strs[0]);
			int xSize = Integer.parseInt(strs[1]);
			Map map = new Map(ySize,xSize);
			for(int i=0;i<ySize;i++) {
				String str = br.readLine();
				for(int j=0;j<str.length();j++) {
					map.map[i][j] = str.charAt(j);
					if(map.map[i][j] =='^'
							||map.map[i][j] =='v'
							||map.map[i][j] =='<'
							||map.map[i][j] =='>') {
						map.tankY = i;
						map.tankX = j;
						map.tankShape=map.map[i][j];
					}
				}
			}
			int commandSize = Integer.parseInt(br.readLine());
			String str = br.readLine();
			map.initCommand(str.length());
			for(int i=0;i<commandSize;i++) {
				map.commands[i] = Command.of(str.charAt(i));
			}
			// 입력 끝
//			System.out.println(Arrays.toString(map.commands));
			sb.append("#"+tc+" ");
			map.execute();
			for(int i=0;i<map.map.length;i++) {
				for(int j=0;j<map.map[0].length;j++) {
					sb.append(map.map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	public static class Map{
		char[][] map;
		Command[] commands;
		int tankY;
		int tankX;
		char tankShape;
		Map(int ySize,int xSize){
			map = new char[ySize][xSize];
		}
		
		void initCommand(int size) {
			commands = new Command[size];
		}
		
		void execute() {
			for(Command command : commands){
				changeTankShape(command);
				switch(command) {
				
				case UP:
				case DOWN:
				case LEFT:
				case RIGHT:
					int ny = tankY + command.dy;
					int nx = tankX + command.dx;
					map[tankY][tankX] = tankShape;
					if(ny<0||nx<0||nx>=map[0].length || ny>=map.length) {
						break;
					}
					if(map[ny][nx] =='.') {
						map[tankY][tankX] = '.';
						tankY = ny;
						tankX = nx;
					}
					map[tankY][tankX] = tankShape;
					break;
				case SHOOT:
					int dy = getDyfromTankShape();
					int dx = getDxfromTankShape();
					int nextY = tankY+dy;
					int nextX = tankX+dx;
					while(true) {
						if(nextY<0||nextX<0||nextX>=map[0].length || nextY>=map.length) {
							break;
						}
						if(map[nextY][nextX]=='#') {
							break;
						}
						
						if(map[nextY][nextX]=='*') {
							map[nextY][nextX] ='.'; 
							break;
						}
						nextY +=dy;
						nextX +=dx;
					}
				}
				
			}
		}
		
		private int getDyfromTankShape() {
			if(tankShape=='^') {
				return -1;
			}else if(tankShape=='v'){
				return 1;
			}
			return 0;
		}
		
		private int getDxfromTankShape() {
			if(tankShape=='<') {
				return -1;
			}else if(tankShape=='>'){
				return 1;
			}
			return 0;
		}
		
		private void changeTankShape(Command command) {
			switch(command) {
			case UP : 
				tankShape = '^';
				break;
			case DOWN:
				tankShape = 'v';
				break;
			case LEFT:
				tankShape = '<';
				break;
			case RIGHT:
				tankShape= '>';
				break;
			}
		}
		
	}

	
	public static enum Command{
		UP('U',-1,0),DOWN('D',1,0),LEFT('L',0,-1),RIGHT('R',0,1),SHOOT('S',0,0);
		
		char comm;
		int dy;
		int dx;
		
		Command(char c,int dy,int dx){
			this.comm = c;
			this.dy = dy;
			this.dx = dx;
		}
		
		static public Command of(char c) {
			for(Command command : Command.values()) {
				if(command.comm == c)
				return command;
			}
			return null;
		}
	}
}
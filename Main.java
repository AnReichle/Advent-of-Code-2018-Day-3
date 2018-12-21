import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<Integer> posX = new ArrayList<Integer>();
		ArrayList<Integer> posY = new ArrayList<Integer>();
		ArrayList<Integer> height = new ArrayList<Integer>();
		ArrayList<Integer> width = new ArrayList<Integer>();
		String path = "src\\input.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String str;
			while((str = br.readLine()) != null) {
				String[] strArr = str.split("#| @ |,|: |x");
				id.add(Integer.parseInt(strArr[1]));
				posX.add(Integer.parseInt(strArr[2]));
				posY.add(Integer.parseInt(strArr[3]));
				height.add(Integer.parseInt(strArr[4]));
				width.add(Integer.parseInt(strArr[5]));
			}
		}
		int maxX = 1;
		int maxY = 1;
		int aux;
		for(int i = 0; i < id.size(); i++) {
			if(maxX < (aux = (posX.get(i) + width.get(i)))) {
				maxX = aux;
			}
			if(maxY < (aux = (posY.get(i) + height.get(i)))) {
				maxY = aux;
			}
		}
		int[][] grid = new int[maxX][maxY];
		for(int i = 0; i < id.size(); i++) {
			for(int a = posX.get(i); a < (posX.get(i) + width.get(i)); a++) {
				for(int b = posY.get(i); b < (posY.get(i) + height.get(i)); b++){
					grid[a][b] += 1;
				}
			}
		}
		int count = 0;
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j<grid[0].length; j++) {
				if(grid[i][j] > 1) count++;

			}
		}
		System.out.println("answer for (a): " + count);	
		ArrayList<Integer> noOverlaps = new ArrayList<Integer>();
		for(int i = 0; i < id.size(); i++) {
			boolean overlap = false;
			for(int a = posX.get(i); a < (posX.get(i) + width.get(i)); a++) {
				for(int b = posY.get(i); b < (posY.get(i) + height.get(i)); b++) if(grid[a][b] > 1) overlap = true;
			}
			if(overlap == false) noOverlaps.add(id.get(i));
		}
		if(noOverlaps.size() > 0) {
			System.out.println("answer for (b): " + noOverlaps.get(0));
		} else System.out.println("all areas have overlaps");
	}
}

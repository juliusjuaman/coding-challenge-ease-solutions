import java.io.File;
import java.util.Scanner;
import java.util.stream.Stream;

public class MapData {
	
	private int[][] mapValues;
	private String filePath;
	
	public MapData(String filePath) {
		
		this.filePath = filePath;
	}
	
	public boolean readMapFile() {
		
		System.out.println("Loading map.txt file...");
		
		File file = new File(filePath);		
		try (Scanner scan = new Scanner(file))
		{
			int[] mapSize = Stream.of(scan.nextLine().split(" "))
					  .mapToInt(Integer::parseInt)
					  .toArray();
			
			int[][] values = new int[mapSize[0]][mapSize[1]];
			
			int i = 0;
			while(scan.hasNextLine()) {
				values[i] = Stream.of(scan.nextLine().split(" "))
				  .mapToInt(Integer::parseInt)
				  .toArray();
				i++;
			}
			
			mapValues = values;
		} catch (Exception e) {
			return false;
		} 
		
		System.out.println("map.txt loaded");
		return true;
	}
	
	public int[][] getMapValues(){
		return this.mapValues;
	}
}

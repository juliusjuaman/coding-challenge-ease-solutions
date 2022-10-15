import java.util.ArrayList;
import java.util.List;

public class SkiResort {
	
	private static enum Directions{North, South, East, West};
	private static final int[][] mountainMap = {{4,8,7,3}, {2,5,9,3}, {6,3,2,5}, {4,4,1,6}};
	private PathDetails[][] wholeMapDetails;
	
	public static void main(String[] args) {
		SkiResort resort = new SkiResort();
		System.out.println("test");
    }
	
	public SkiResort() {
		wholeMapDetails = translateMapSizeToPathDetails(mountainMap);
	}
	
	private int[] getLongestPathEntireMap() {
		int xLongest = 0;
		int yLongest = 0;
		int[] longestPath;
		
		for(int x = 0; x < mountainMap.length; x++) {
			for(int y = 0; y < mountainMap.length; y++) {
				if(getLongestPathByIndex(x, y).getLength() > getLongestPathByIndex(xLongest, yLongest).getLength()) {
					xLongest = x;
					yLongest = y;
				}
			}
		}
		
		return new int[1];
	}
	
	private PathDetails[][] translateMapSizeToPathDetails(int[][] map) {
		PathDetails[][] pathDetails = new PathDetails[map.length][];
		for(int x = 0; x < map.length; x++) {
			pathDetails[x] = new PathDetails[map[x].length];
		}
		
		return pathDetails;
	}
	
	private PathDetails getLongestPathByIndex(int x, int y) {
		
		List<PathDetails> listOfPossibleNextPath = new ArrayList<PathDetails>();
		PathDetails pathDetails = new PathDetails(x, y, mountainMap[x][y], mountainMap[x][y], 1, -1, -1);
		
		// Check North
		if(x != 0 && mountainMap[x - 1][y] < mountainMap[x][y])
        {
			pathDetails = getLongestPathByIndex(x - 1, y);
			listOfPossibleNextPath.add(mergePaths(pathDetails, Directions.North));
        }
		
		
		return pathDetails;
	}
	
	private PathDetails mergePaths(PathDetails pd, Directions dir) {
		PathDetails merged = null;
		
		if(dir.equals(Directions.North)) {
			merged = new PathDetails(pd.getX(), pd.getY(), mountainMap[pd.getX()][pd.getY()], pd.getEndHeight(), pd.getLength() + 1, pd.getX() - 1, pd.getY());
		}
		
		return merged;
	}
}



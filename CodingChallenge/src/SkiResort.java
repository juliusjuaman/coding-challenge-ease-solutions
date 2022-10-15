import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkiResort {
	
	private static enum Directions{North, South, East, West};
	private static final int[][] mountainMap = {{4,8,7,3}, {2,5,9,3}, {6,3,2,5}, {4,4,1,6}};
	
	// Cache for path details for each location in map
	private PathDetails[][] wholeMapPathDetailCache;
	
	public static void main(String[] args) {
		SkiResort resort = new SkiResort();
		System.out.println(resort.getLongestPathEntireMap());
    }
	
	public SkiResort() {
		wholeMapPathDetailCache = translateMapSizeToPathDetails(mountainMap);
	}
	
	private int[] getLongestPathEntireMap() {
		int xLongest = 0;
		int yLongest = 0;
		
		for(int x = 0; x < mountainMap.length; x++) {
			for(int y = 0; y < mountainMap[x].length; y++) {
				if(getPathDetailFromCache(x, y).compareTo(getPathDetailFromCache(xLongest, yLongest)) == 1 ) {
					xLongest = x;
					yLongest = y;
				}
			}
		}
		
		PathDetails temp = wholeMapPathDetailCache[xLongest][yLongest];
        int[] path = new int[temp.getLength()];
        
        for(int i = 0; i < temp.getLength() - 1; i++) {
        
        	path[i] = mountainMap[temp.getX()][temp.getY()];
            temp = getPathDetailFromCache(temp.getXNext(), temp.getYNext());
        }
        
        return path;
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
			pathDetails = getPathDetailFromCache(x - 1, y);
			listOfPossibleNextPath.add(mergePaths(pathDetails, Directions.North));
        }
		
		// Check South
		if(x != mountainMap.length - 1 && mountainMap[x + 1][y] < mountainMap[x][y])
        {
			pathDetails = getPathDetailFromCache(x + 1, y);
			listOfPossibleNextPath.add(mergePaths(pathDetails, Directions.South));
        }
		
		// Check East
		if(y != 0 && mountainMap[x][y - 1] < mountainMap[x][y])
        {
			pathDetails = getPathDetailFromCache(x, y - 1);
			listOfPossibleNextPath.add(mergePaths(pathDetails, Directions.East));
            
        }
		
		// Check West
		if(y != mountainMap[x].length -1 && mountainMap[x][y + 1] < mountainMap[x][y])
        {
			pathDetails = getPathDetailFromCache(x, y + 1);
			listOfPossibleNextPath.add(mergePaths(pathDetails, Directions.West));
            
        }
		
		if(listOfPossibleNextPath.size() == 0)
        {
            return pathDetails;
        }
		
		PathDetails tempTest = Collections.max(listOfPossibleNextPath);
		return tempTest;
	}
	
	private PathDetails mergePaths(PathDetails pd, Directions dir) {
		PathDetails merged = null;
		
		if(dir.equals(Directions.North)) {
			merged = new PathDetails(pd.getX(), pd.getY(), mountainMap[pd.getX()][pd.getY()], pd.getEndHeight(), pd.getLength() + 1, pd.getX() - 1, pd.getY());
		}
		else if(dir.equals(Directions.South)) {
			merged = new PathDetails(pd.getX(), pd.getY(), mountainMap[pd.getX()][pd.getY()], pd.getEndHeight(), pd.getLength() + 1, pd.getX() + 1, pd.getY());
			
		}
		else if(dir.equals(Directions.East)) {
			merged = new PathDetails(pd.getX(), pd.getY(), mountainMap[pd.getX()][pd.getY()], pd.getEndHeight(), pd.getLength() + 1, pd.getX(), pd.getY() - 1);
			
		}
		else if(dir.equals(Directions.West)) {
			merged = new PathDetails(pd.getX(), pd.getY(), mountainMap[pd.getX()][pd.getY()], pd.getEndHeight(), pd.getLength() + 1, pd.getX(), pd.getY() + 1);
		}
		
		return merged;
	}
	
	private PathDetails getPathDetailFromCache(int x, int y) {
		if(wholeMapPathDetailCache[x][y] == null){
			wholeMapPathDetailCache[x][y] = getLongestPathByIndex(x, y);
        }
        return wholeMapPathDetailCache[x][y];
	}
}



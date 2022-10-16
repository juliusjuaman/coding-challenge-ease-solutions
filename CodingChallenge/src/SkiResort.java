import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SkiResort {
	
	/* Choices for possible path directions */
	private static enum Directions{North, South, East, West};
	private static final int[][] mountainMap = {{4,8,7,3}, {2,5,9,3}, {6,3,2,5}, {4,4,1,6}};
	
	/* Cache for details of longest path from each location in map */
	private PathDetails[][] wholeMapPathDetailCache;
	
	public static void main(String[] args) {
		SkiResort resort = new SkiResort();
		System.out.println( Arrays.toString(resort.getLongestPathEntireMap()));
    }
	
	public SkiResort() {
		wholeMapPathDetailCache = translateMapSizeToPathDetails(mountainMap);
	}
	
	/* Get longest and steepest path in map */
	private int[] getLongestPathEntireMap() {
		int xLongest = 0;
		int yLongest = 0;
		
		for(int x = 0; x < mountainMap.length; x++) 
		{
			for(int y = 0; y < mountainMap[x].length; y++) 
			{
				PathDetails currentPath = getPathDetailFromCache(x, y);
				PathDetails longestPath = getPathDetailFromCache(xLongest, yLongest);
				if(isLongerPath(currentPath, longestPath))
				{
					xLongest = x;
					yLongest = y;
				}
			}
		}
		
		PathDetails longestPath = getPathDetailFromCache(xLongest, yLongest);
        int[] path = new int[longestPath.getLength()];

        for(int i = 0; i < wholeMapPathDetailCache[xLongest][yLongest].getLength(); i++) {
        	
        	path[i] = mountainMap[longestPath.getX()][longestPath.getY()];
        	
        	// Check if current path detail is at the end of the path i.e final point in map
        	if(longestPath.getXNext() >= 0 && longestPath.getYNext() >= 0) 
        	{
        		longestPath = getPathDetailFromCache(longestPath.getXNext(), longestPath.getYNext());
        	}
        }
        return path;
	}
	
	/* Create cache matrix same size as map */
	private PathDetails[][] translateMapSizeToPathDetails(int[][] map) 
	{
		PathDetails[][] pathDetails = new PathDetails[map.length][];
		for(int x = 0; x < map.length; x++) {
			pathDetails[x] = new PathDetails[map[x].length];
		}
		
		return pathDetails;
	}
	
	/* Get longest and steepest path from given point in map */
	private PathDetails getLongestPathByIndex(int x, int y) {
		
		List<PathDetails> listOfPossibleNextPath = new ArrayList<PathDetails>();
		
		// Check if North is a possible path
        if(x != 0 && mountainMap[x - 1][y] < mountainMap[x][y])
        {
        	listOfPossibleNextPath.add(mergePath(x, y, Directions.North));
        }

        // Check if South is a possible path
        if(x != mountainMap.length - 1 && mountainMap[x + 1][y] < mountainMap[x][y])
        {
        	listOfPossibleNextPath.add(mergePath(x, y, Directions.South));
        }

        // Check if East is a possible path
        if(y != 0 && mountainMap[x][y - 1] < mountainMap[x][y])
        {
        	listOfPossibleNextPath.add(mergePath(x, y, Directions.East));
        }

        // Check if West is a possible path
        if(y != mountainMap[x].length -1 && mountainMap[x][y + 1] < mountainMap[x][y])
        {
        	listOfPossibleNextPath.add(mergePath(x, y, Directions.West));
        }

        PathDetails longestPath;
        
        if(listOfPossibleNextPath.isEmpty())
        {
        	// When end of path is reached i.e. no where else to go
        	longestPath = new PathDetails(x,y,1, mountainMap[x][y], mountainMap[x][y], -1, -1);   
        }
        else 
        {
        	longestPath = listOfPossibleNextPath.get(0);
        	// Find longest and steepest path from all possible path choices
            for (PathDetails path : listOfPossibleNextPath) 
            { 
                if(isLongerPath(path, longestPath)) {
                	longestPath = path;
                }
            }
        }
        
        return longestPath;
	}
	
	/* Used to retrieve path data from given point in map */
	private PathDetails getPathDetailFromCache(int x, int y) {
		
		if(wholeMapPathDetailCache[x][y] == null){
			wholeMapPathDetailCache[x][y] = getLongestPathByIndex(x, y);
        }
        return wholeMapPathDetailCache[x][y];
	}
	
	/* Used to compare length and steepness of 2 given paths */
	private boolean isLongerPath(PathDetails path, PathDetails pathToCompare) {
	
		if(path.getLength() > pathToCompare.getLength() || 
				(path.getLength() == pathToCompare.getLength() && 
						path.getStartHeight() - path.getEndHeight() > pathToCompare.getStartHeight() - pathToCompare.getEndHeight())){
			return true;
		}
		return false;
	}
	
	/* Merging path details of 2 points in map depending on which direction to move */
	PathDetails mergePath (int x, int y, Directions dir) {
		
		int xNext = x;
		int yNext = y;
		
		if(dir.equals(Directions.North)) {
			
			// Move to North
			xNext = x - 1;
		}
		else if(dir.equals(Directions.South)) {
			
			// Move to South
			xNext = x + 1;
		}
		else if(dir.equals(Directions.East)) {
			
			// Move to East
			yNext = y - 1;
		}
		else if(dir.equals(Directions.West)) {
			
			// Move to West
			yNext = y + 1;
		}
		
		PathDetails p = getPathDetailFromCache(xNext, yNext);
    	return new PathDetails(x,y, mountainMap[x][y], p.getEndHeight(), p.getLength() + 1, xNext, yNext);
	}
}
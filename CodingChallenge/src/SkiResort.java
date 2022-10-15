
public class SkiResort {
	
	private static final int[][] mountainMap = {{4,8,7,3}, {2,5,9,3}, {6,3,2,5}, {4,4,1,6}};
	
	
	public static void main(String[] args) {
		SkiResort resort = new SkiResort();
		System.out.println("test");
    }
	
	public SkiResort() {
		PathDetails[][] wholeMapDetails = translateMapSizeToPathDetails(mountainMap);
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
		
		return new PathDetails(1,1,1,1,1);
	}
}



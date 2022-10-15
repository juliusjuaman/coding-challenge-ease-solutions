
public class SkiResort {
	
	private static final int[][] mountainMap = {{4,8,7,3}, {2,5,9,3}, {6,3,2,5}, {4,4,1,6}};
	private PathDetails[][] wholeMapDetails; 
	
	public static void main(String[] args) {
		SkiResort resort = new SkiResort();
		System.out.println("test");
    }
	
	public SkiResort() {
		wholeMapDetails = new PathDetails[mountainMap.length][];
		for(int x = 0; x < mountainMap.length; x++) {
			wholeMapDetails[x] = new PathDetails[mountainMap[x].length];
		}
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
	
	private PathDetails getLongestPathByIndex(int x, int y) {
		
		return new PathDetails(1,1,1,1,1);
	}
}



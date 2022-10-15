
public class SkiResort {
	private static final int[][] mountainMap = {{4,8,7,3}, {2,5,9,3}, {6,3,2,5}, {4,4,1,6}};
	private PathDetails[][] wholeMapDetails; 
	
	public static void main(String[] args)
    {
		SkiResort resort = new SkiResort();
    }
	
	public SkiResort() {
		wholeMapDetails = new PathDetails[mountainMap.length][];
		for(int x = 0; x < mountainMap.length; x++) {
			wholeMapDetails[x] = new PathDetails[mountainMap[x].length];
		}
	}
	
	/*
	private int[] getLongestPath() {
		int[] longestPath;
		
		
	}
	*/
}




public class PathDetails {

	private int startHeight, endHeight, length, xNext, yNext;
	
	public PathDetails(int startHeight, int endHeight, int length, int xNext, int yNext) {
		this.startHeight = startHeight;
		this.endHeight = endHeight;
		this.length = length;
		this.xNext = xNext;
		this.yNext = yNext;
	}
	
	public int getLength() {
		return this.length;
	}
}


public class PathDetails {

	private int x, y, startHeight, endHeight, length, xNext, yNext;
	
	public PathDetails(int x, int y, int startHeight, int endHeight, int length, int xNext, int yNext) {
		this.x = x;
		this.y = y;
		this.startHeight = startHeight;
		this.endHeight = endHeight;
		this.length = length;
		this.xNext = xNext;
		this.yNext = yNext;
	}
	
	public int getLength() {
		return this.length;
	}
	
	public int getEndHeight() {
		return this.endHeight;
	}
	
	public void incrementLength() {
		this.length++;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.x;
	}
}

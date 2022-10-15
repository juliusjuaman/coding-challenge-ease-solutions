
public class PathDetails implements Comparable<PathDetails>{

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
	
	@Override
    public int compareTo(PathDetails o) {
        if(this.length < o.length || 
        		(this.length == o.length && this.startHeight - this.endHeight < o.startHeight - o.endHeight)){
            return -1;
        }
        else if(this.length > o.length 
        		|| (this.length == o.length && this.startHeight - this.endHeight > o.startHeight - o.endHeight)){
            return 1;
        }
        return 0;
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
	
		
	public int getXNext() {
		return this.xNext;
	}
	
	public int getYNext() {
		return this.yNext;
	}
}

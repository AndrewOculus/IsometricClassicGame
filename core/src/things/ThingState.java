package things;

public class ThingState {

	private long x , y;
	private boolean isTaken;
	private ThingClass thingClass;
	
	public long getX() {
		return x;
	}
	public void setX(long x) {
		this.x = x;
	}
	public long getY() {
		return y;
	}
	public void setY(long y) {
		this.y = y;
	}
	public boolean isTaken() {
		return isTaken;
	}
	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	public ThingClass getThingClass() {
		return thingClass;
	}
	public void setThingClass(ThingClass thingClass) {
		this.thingClass = thingClass;
	}
	
	
}

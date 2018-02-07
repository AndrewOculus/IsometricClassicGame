package stats;

public class Transform {

	private float x , y;
	private float angle;
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public void addX(float x) {
		this.x += x;
	}
	public void addY(float y) {
		this.y += y;
	}
}

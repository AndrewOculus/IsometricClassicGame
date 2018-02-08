package stats;

public class State {

	private long experience;
	private int health;
	private String name;
	
	public void setExperience(long exp)
	{
		this.experience = exp;
	}
	public void setHealth(int hp)
	{
		this.health = hp;
	}
	
	public long getExperience()
	{
		return experience;
	}
	public int getHealth()
	{
		return health;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

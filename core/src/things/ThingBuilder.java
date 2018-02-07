package things;

public class ThingBuilder {

	public static ThingState build()
	{
		ThingState thingState = new ThingState();
		thingState.setTaken(false);
		thingState.setX(50);
		thingState.setY(0);
		thingState.setThingClass(ThingClass.AID);
		return thingState;
		
	}
}

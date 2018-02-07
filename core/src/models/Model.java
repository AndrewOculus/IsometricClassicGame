package models;

import stats.State;
import stats.Transform;

public class Model {

	private State state;
	private Transform transform;
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Transform getTransform() {
		return transform;
	}
	public void setTransform(Transform transform) {
		this.transform = transform;
	}
	
}

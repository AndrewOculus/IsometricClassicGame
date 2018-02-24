package com.noname.trpg.character.stats;

import com.badlogic.gdx.math.Vector2;

public class CharacterStats {

	private int health = 100;
	private int mana = 0;
	private long exp = 0;
	private	Vector2 characterSpeed;
	private String name;
	
	public CharacterStats(String name)
	{
		setExp(0);
		setHealth(100);
		setMana(0);
		setName(name);
		characterSpeed = new Vector2(100, 70);
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public long getExp() {
		return exp;
	}
	public void setExp(long exp) {
		this.exp = exp;
	}

	public Vector2 getCharacterSpeed() {
		return characterSpeed;
	}

	public void setCharacterSpeed(Vector2 characterSpeed) {
		this.characterSpeed = characterSpeed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

package com.hf;

public class WorldCon extends IceCream {
 
	public WorldCon() {
		matterState = new Soft();
		formState = new Cone();
	}
 
	void taste() {
		System.out.println("바닐라와 초코 맛이 난다.");	
	}
}

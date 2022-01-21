package com.hf;

public class TwinBar extends IceCream {
 
	public TwinBar() {
		matterState = new Soft();
		formState = new Stick();
	}
 
	void taste() {
		System.out.println("초코맛이 난다.");	
	}

	public void matterState() {
		
	}
}

package com.hf;

public abstract class IceCream {
	MatterState matterState; //물질상태(얼음 or 소프트)
	FormState formState; // 형태(막대 or 콘 or 떠먹는 형태)
 
	public IceCream() {
	}
 
	public void setMatterState (MatterState ms) {
		matterState = ms;
	}
 
	public void setFormState(FormState fs) {
		formState = fs;
	}
 
	abstract void taste();
 
	public void performMatter() {
		matterState.matter();
	}
 
	public void performForm() {
		formState.form();
	}
 
	public void cold() {
		System.out.println("모든 아이스크림은 차갑다!!");
	}
	
	
}

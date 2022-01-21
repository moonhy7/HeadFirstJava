package com.hf;

public abstract class IceCream {
	MatterState matterState; //��������(���� or ����Ʈ)
	FormState formState; // ����(���� or �� or ���Դ� ����)
 
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
		System.out.println("��� ���̽�ũ���� ������!!");
	}
	
	
}

package com.hf;

public class JawsBar extends IceCream { //�ҽ�
	public JawsBar() {
		matterState = new Soft();
		formState = new Stick();
	}

	void taste() {
		System.out.println("������� ����.");	
	}
}

package com.hf;

public class Together extends IceCream {
	public Together() {
		setMatterState(new Soft());
		setFormState(new Cone());
	}
	
	void taste() {
		System.out.println("�ٴҶ� ���� ����.");	
	}
}

package com.kosa;

public class ElectricGuitarMain {
	
	public static void main(String[] args) {
		ElectricGuitar guitar = new ElectricGuitar();
		guitar.setBrand("�߸���");
		System.out.println(guitar.getBrand());
		System.out.println(guitar.toString());  // ��ü�� ���� state Ȯ��!
		System.out.println(guitar);
	}

}
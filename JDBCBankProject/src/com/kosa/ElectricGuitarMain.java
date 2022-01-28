package com.kosa;

public class ElectricGuitarMain {
	
	public static void main(String[] args) {
		ElectricGuitar guitar = new ElectricGuitar();
		guitar.setBrand("야마하");
		System.out.println(guitar.getBrand());
		System.out.println(guitar.toString());  // 객체의 현재 state 확인!
		System.out.println(guitar);
	}

}
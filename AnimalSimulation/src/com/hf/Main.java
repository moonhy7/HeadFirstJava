package com.hf;

public class Main {
	public static void main(String[] args) {
		Animal arrow = new Dog();
		arrow.makeNoise();
		arrow.eat();
		
		Pet pet = (Pet) arrow();
	}

	private static Pet arrow() {
		// TODO Auto-generated method stub
		return null;
	}


}

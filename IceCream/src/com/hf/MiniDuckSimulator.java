package com.hf;

public class MiniDuckSimulator {
 
	public static void main(String[] args) {
 
		Babamba	babamba = new Babamba();
		JawsBar	jawsBar = new JawsBar();
		Together together = new Together();
		WorldCon worldCon = new WorldCon();
		TwinBar twinBar = new TwinBar();

		babamba.performForm();
		jawsBar.performForm();
		together.performForm();
		worldCon.performForm();
		twinBar.performForm();
   
		twinBar.setFormState(new TwoStick());
		twinBar.matterState();
	}
}

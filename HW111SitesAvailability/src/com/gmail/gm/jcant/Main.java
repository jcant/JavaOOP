package com.gmail.gm.jcant;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		System.out.println("START");
		SitesChecker sCheck = new SitesChecker();

		sCheck.addSiteList(new File("sites.txt"));

		System.out.println(sCheck);
	}

}

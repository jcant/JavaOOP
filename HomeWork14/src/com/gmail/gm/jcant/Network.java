package com.gmail.gm.jcant;

import java.util.Arrays;

public class Network {

	private Phone[] phones;

	public Network() {
		super();
		phones = new Phone[0];
	}

	public void addPhone(Phone ph) {
		Phone[] newPhones = new Phone[phones.length + 1];
		for (int i = 0; i < phones.length; i++) {
			newPhones[i] = phones[i];
		}
		newPhones[phones.length] = ph;
		phones = newPhones;
	}

	public void delPhone(Phone ph) {
		Phone[] newPhones = new Phone[phones.length - 1];
		int newCnt = 0;
		for (int i = 0; i < phones.length; i++) {
			if (!phones[i].getNumber().equals(ph.getNumber())) {
				newPhones[newCnt] = phones[i];
				newCnt++;
			}
		}
		phones = newPhones;
	}

	public Phone findPhone(Phone ph) {
		for (int i = 0; i < phones.length; i++) {
			if (ph.getNumber().equals(phones[i].getNumber())) {
				return phones[i];
			}
		}

		return null; // return null phone when phone was not found
	}

	@Override
	public String toString() {
		return "Network [phones=" + Arrays.toString(phones) + "]";
	}

}

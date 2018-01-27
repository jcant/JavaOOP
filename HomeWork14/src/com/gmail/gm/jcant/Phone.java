package com.gmail.gm.jcant;

public class Phone {

	private String number;
	private Network selfNetwork;

	public Phone() {
		super();
		number = "";
	}

	public Phone(String number) {
		super();
		this.number = number;
	}

	public Phone(String number, Network selfNetwork) {
		super();
		this.number = number;
		registerInNetwork(selfNetwork);
	}

	public void registerInNetwork(Network net) {
		if (!number.equals("")) {

			if (selfNetwork != null) {
				selfNetwork.delPhone(this);
			}

			selfNetwork = net;
			selfNetwork.addPhone(this);
		}
	}

	public void call(String number) {
		if (this.number.equals(number)) {
			System.out.println("You can't call to self number!!!");
			return;
		}

		System.out.println("(" + this.number + ") Call to: " + number);
		Phone recipient = selfNetwork.findPhone(new Phone(number));
		if (recipient != null) {
			System.out.println("Recipient found");
			recipient.incomingCall(this);
		} else {
			System.out.println("Recipient not found!!!");
			System.out.println();
		}
	}

	public void call(Phone ph) {
		call(ph.getNumber());
	}

	public void incomingCall(Phone ph) {
		System.out.println("(" + this.number + ") Incoming call from: " + ph.getNumber());
		System.out.println();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Network getSelfNetwork() {
		return selfNetwork;
	}

	@Override
	public String toString() {
		return "number=" + number;
	}

}

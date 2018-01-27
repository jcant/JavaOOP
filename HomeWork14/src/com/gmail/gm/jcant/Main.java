package com.gmail.gm.jcant;

public class Main {

	public static void main(String[] args) {

		Network mts = new Network();
		Network kievstar = new Network();

		Phone myPhone = new Phone();
		myPhone.setNumber("0661111111");
		myPhone.registerInNetwork(mts);

		Phone wife = new Phone("0662222222");
		wife.registerInNetwork(mts);

		Phone son = new Phone("0993333333", mts);

		Phone ph1 = new Phone("0123456789", kievstar);
		Phone ph2 = new Phone("0682222222", kievstar);

		System.out.println("mts:      " + mts);
		System.out.println("kievstar: " + kievstar);
		System.out.println();
		System.out.println("myPhone: " + myPhone);
		System.out.println("wife:    " + wife);
		System.out.println("son:     " + son);
		System.out.println();
		System.out.println("ph1:     " + ph1);
		System.out.println("ph2:     " + ph2);
		System.out.println();

		myPhone.call("0662222222");
		myPhone.call(son);
		myPhone.call("012345678978878"); // call to wrong number
		myPhone.call(ph1); // call to number in other network

		myPhone.registerInNetwork(kievstar);
		myPhone.call(ph1);
		myPhone.call(wife); // now this number is in other network
		ph1.call(son); // this numbers are in different networks
		ph1.registerInNetwork(mts);
		ph1.call(son); // now they are in one network
		myPhone.call(ph1);

		myPhone.call(myPhone);

	}

}

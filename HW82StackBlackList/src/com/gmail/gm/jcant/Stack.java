package com.gmail.gm.jcant;

import java.util.Arrays;

public class Stack {

	private Object[] storage = new Object[10];
	private int index = -1;
	private BlackList blackList = new BlackList();

	public Stack() {
		super();
	}

	public void push(Object obj) {
		if (!blackList.canAdd(obj)) {
			throw new IllegalArgumentException(obj.getClass() + " is BlackListed");
		}

		index += 1;

		if (index >= storage.length) {
			addStorade();
		}

		storage[index] = obj;
	}

	public Object pop() {
		if (!isEmpty()) {
			Object obj = storage[index];
			storage[index--] = null;
			return obj;
		} else {
			throw new ArrayIndexOutOfBoundsException("Stack is empty");
		}
	}

	public Object get() {
		if (!isEmpty()) {
			return storage[index];
		} else {
			throw new ArrayIndexOutOfBoundsException("Stack is empty");
		}
	}

	private void addStorade() {
		Object[] newArray = new Object[storage.length * 2];
		newArray = Arrays.copyOf(storage, newArray.length);

		storage = newArray;
	}

	public boolean isEmpty() {
		return (index == -1);
	}

	public void addToBlackList(Class cls) {
		blackList.addClass(cls);
	}

	public void removeFromBlackList(Class cls) {
		blackList.removeClass(cls);
	}

	@Override
	public String toString() {
		return "Stack [storage=" + Arrays.toString(storage) + ", index=" + index + "]";
	}

}

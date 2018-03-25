package com.gmail.gm.jcant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlackList {
	private List<Class> list = new ArrayList<Class>();

	public BlackList() {
		super();
	}

	public void addClass(Class cls) {
		if (!isPresent(cls)) {
			list.add(cls);
		}
	}

	public void removeClass(Class cls) {
		Iterator<Class> it = list.iterator();

		while (it.hasNext()) {
			if (it.next().equals(cls)) {
				it.remove();
			}
		}
	}

	public boolean canAdd(Object obj) {
		return !isPresent(obj.getClass());
	}

	private boolean isPresent(Class cls) {
		Iterator<Class> it = list.iterator();

		while (it.hasNext()) {
			if (it.next().equals(cls)) {
				return true;
			}
		}

		return false;
	}
}

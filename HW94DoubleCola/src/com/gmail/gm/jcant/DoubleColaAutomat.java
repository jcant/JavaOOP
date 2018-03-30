package com.gmail.gm.jcant;

import java.util.LinkedList;
import java.util.Queue;

public class DoubleColaAutomat {

	private Queue<BBTPers> queue = new LinkedList<>();
	private int givenColas = 0;

	public DoubleColaAutomat() {
		super();
	}

	public void addToQueue(BBTPers pers) {
		queue.add(pers);
	}

	public void processSell(int colaNum) {
		for (int i = 0; i < colaNum; i++) {
			sell();
		}
	}

	private void sell() {
		BBTPers current = queue.poll();
		if (current == null) {
			throw new NullPointerException("can't sell() - current person is null");
		}

		BBTPers clone = current.clone();
		queue.add(current);
		queue.add(clone);
		givenColas++;
	}
	
	public void resetQueue() {
		if (queue.isEmpty()) {
			return;
		}

		Queue<BBTPers> tmp = new LinkedList<>();
		for (BBTPers pers : queue) {
			if (pers.getGeneration() == 0) {
				tmp.add(pers);
			}
		}

		queue = tmp;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Given Colas: "+ givenColas + System.lineSeparator());
		for (BBTPers pers : queue) {
			sb.append(pers + System.lineSeparator());
		}
		return sb.toString();
	}
	
	
}

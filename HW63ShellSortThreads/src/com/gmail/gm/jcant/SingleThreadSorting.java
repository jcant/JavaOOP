package com.gmail.gm.jcant;

public class SingleThreadSorting implements Runnable {
	private int[] array;
	private int begin;
	private int end;
	private Thread thr;
	private int index;
	private boolean stop = false;

	public SingleThreadSorting(int[] array, int begin, int end) {
		super();
		this.array = array;
		this.begin = begin;
		this.end = end;
		thr = new Thread(this);
		thr.start();
		this.index = begin;
	}

	public Thread getThr() {
		return thr;
	}

	public int peekElement() {
		return array[index];
	}

	public int pollElement() {
		int temp = array[index];
		check();
		return temp;
	}

	public boolean isStop() {
		return stop;
	}

	@Override
	public void run() {
		int tmp;
		for (int step = (end - begin) / 2; step > 0; step /= 2) {

			for (int i = step + begin; i < end; i++) {

				tmp = array[i];
				int j;

				for (j = i; j >= step + begin; j -= step) {

					if (tmp < array[j - step]) {
						array[j] = array[j - step];
					} else {
						break;
					}
				}
				array[j] = tmp;
			}
		}
	}

	private void check() {
		this.index++;
		if (this.index >= this.end) {
			this.stop = true;
		}
	}
}

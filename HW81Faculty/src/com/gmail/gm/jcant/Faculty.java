package com.gmail.gm.jcant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Faculty {

	private List<Group> groups = new LinkedList<Group>();
	private File baseDir;

	public Faculty() {
		super();
		baseDir = new File(".");
	}

	public File getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(File baseDir) {
		if (!baseDir.exists()) {
			throw new IllegalArgumentException("BaseDir is not exist");
		}
		if (baseDir.isFile()) {
			throw new IllegalArgumentException("BaseDir is a file");
		}

		this.baseDir = baseDir;
	}

	public void addGroup(Group group) {
		if (group == null) {
			throw new IllegalArgumentException("group is null");
		}

		Iterator<Group> it = groups.iterator();
		while (it.hasNext()) {
			if (it.next().equals(group)) {
				throw new IllegalArgumentException("Equal group is present in faculty");
			}
		}
		groups.add(group);
	}

	public void saveGroupByName(String name) throws IOException, IllegalArgumentException {
		Group gr = getGroupByName(name);
		if (gr != null) {
			saveGroupToFile(gr);
		} else {
			throw new IllegalArgumentException("Group with name=" + name + " is not present");
		}
	}

	private void saveGroupToFile(Group group) throws IOException {
		String fname = "" + group.hashCode();
		File file = new File(baseDir, fname);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(group);
		} catch (IOException e) {
			throw e;
		}
	}

	public Group getGroupByName(String name) {
		Iterator<Group> it = groups.iterator();
		while (it.hasNext()) {
			Group gr = it.next();
			if (gr.getGroupName().equals(name)) {
				return gr;
			}
		}
		return null;
	}

	public Group loadGroupFromFile(String fname) throws IOException, ClassNotFoundException {
		Group result = null;

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname))) {
			result = (Group) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw e;
		}

		addGroup(result);

		return result;
	}

	public void saveAllGroups() throws IOException, IllegalArgumentException {
		if (groups.isEmpty()) {
			throw new IllegalArgumentException("It is no groups to save");
		}

		Iterator<Group> it = groups.iterator();
		while (it.hasNext()) {
			saveGroupToFile(it.next());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("*** Faculty *** " + System.lineSeparator());

		Iterator<Group> it = groups.iterator();
		while (it.hasNext()) {
			sb.append(it.next() + System.lineSeparator());
		}

		return sb.toString();
	}

	public void showGroupsAvarageScore() {
		Iterator<Group> it = groups.iterator();
		while (it.hasNext()) {
			Group gr = it.next();
			System.out.println("Group: " + gr.getGroupName() + " AvScore: " + gr.getAverageScore());
		}
	}

	public Student[] getStudentsByAvScore(double min, double max) {
		if (min > max) {
			throw new IllegalArgumentException("min > max");
		}

		List<Student> result = new ArrayList<Student>();
		Student[] array = new Student[1];

		Iterator<Group> it = groups.iterator();
		while (it.hasNext()) {
			Group gr = it.next();
			for (Student st : gr.getStudentsArray()) {
				if ((st.getAvarageScore() >= min) && (st.getAvarageScore() <= max)) {
					result.add(st);
				}
			}
		}

		return result.toArray(array);
	}

}

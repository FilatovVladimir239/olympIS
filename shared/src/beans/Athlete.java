package beans;

import java.io.Serializable;

public class Athlete implements Serializable {
	public Athlete(String name, String sex, int weight, int height, String competition){
		this.name = name;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.competition = competition;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return this.sex;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return this.weight;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return this.height;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public String getCompetition() {
		return this.competition;
	}

	private String name;
	private String sex;
	private int weight;
	private int height;
	private String competition;
}


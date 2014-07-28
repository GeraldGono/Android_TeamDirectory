package com.example.team_directory_v02;

public class Member {

	private String name;
	private String color;
	private String dept;
	private int id;

	public Member() {

	}

	public Member(String name, String color, String dept) {
		super();
		this.setName(name);
		this.setColor(color);
		this.setDept(dept);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name;

	}

}

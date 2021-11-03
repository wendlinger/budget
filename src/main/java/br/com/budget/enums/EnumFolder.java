package br.com.budget.enums;

public enum EnumFolder {

	HEALTH(0), EDUCATION(1), SPORTS(2), INFRASTRUCTURE(3);

	private int value;

	private EnumFolder(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

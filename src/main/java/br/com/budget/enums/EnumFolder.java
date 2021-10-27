package br.com.budget.enums;

public enum EnumFolder {

	FEDERAL(0), STATE(1), COUNTY(2);
	
	private int value;
	
	private EnumFolder(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

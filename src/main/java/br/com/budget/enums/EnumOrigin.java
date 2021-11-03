package br.com.budget.enums;

public enum EnumOrigin {

	FEDERAL(0), STATE(1), COUNTY(2);
	
	private int value;
	
	private EnumOrigin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

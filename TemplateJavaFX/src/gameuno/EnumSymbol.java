package gameuno;

public enum EnumSymbol {
	ZERO(0), UN(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7), HUIT(8), NEUF(9), PLUS2(12), BLOCK(13), REVERSE(14), PLUS4(21), CHANGE(22);
	
	private int numVal;
	EnumSymbol(int numVal) {
		this.numVal = numVal;
	}
	public int getNumVal() {
		return numVal;
	}
}

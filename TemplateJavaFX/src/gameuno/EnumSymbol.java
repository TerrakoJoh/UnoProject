package gameuno;

public enum EnumSymbol {
	ZO(0), UN(1), DX(2), TR(3), QT(4), CQ(5), SX(6), ST(7), HT(8), NF(9), PLS2(12), PLS4(14), B(20), R(21), C(22);
	
	private int numVal;
	EnumSymbol(int numVal) {
		this.numVal = numVal;
	}
	public int getNumVal() {
		return numVal;
	}
}

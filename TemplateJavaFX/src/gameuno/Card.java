package gameuno;

public class Card {
	private EnumColor color;
	private EnumSymbol symbol;
	
	public Card() {
		
	}
	
	public Card(EnumColor color, EnumSymbol symbol) {
		this.color = color;
		this.symbol = symbol;
	}
	
	public String PrintCard() {
		return color.name() + " " + symbol.name();
		//System.out.println(color.name() + " " + symbol.name());
	}
}
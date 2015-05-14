package cz.muni.fi.sdipr.utils;

public enum CharactersEnum {
	A("A"),
	B("B"),
	C("C"),
	D("D");

	private final String letter;

	private CharactersEnum(String letter) {
		this.letter = letter;
	}

	public String getLetter() {
		return this.letter;
	}
}

package cz.muni.fi.sdipr.utils;

import java.util.Random;

public class Utils {
	public static String generateRandomCharacter(Random rnd) {

		int i = rnd.nextInt(4);

		switch (i) {
			case 0:
				return CharactersEnum.A.getLetter();
			case 1:
				return CharactersEnum.B.getLetter();
			case 2:
				return CharactersEnum.C.getLetter();
			default:
				return CharactersEnum.D.getLetter();
		}
	}
}

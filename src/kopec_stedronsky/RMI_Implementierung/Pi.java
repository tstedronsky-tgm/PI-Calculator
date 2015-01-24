package kopec_stedronsky.RMI_Implementierung;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Diese Klasse ist fuer die berechnung von Pi zustaendig.
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 * @see http://stackoverflow.com/questions/8370290/generating-pi-to-nth-digit-java
 */
public final class Pi {
	//benoetigte Werte fuer die Berechnung von Pi
	private static final BigDecimal TWO = new BigDecimal("2");
	private static final BigDecimal FOUR = new BigDecimal("4");
	private static final BigDecimal FIVE = new BigDecimal("5");
	private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");

	/*
	 * Diese Methode liefert Pi mit den gewuenschten Nachkommastellen
	 * @param numDigits -die gewuenschte Anzahl von Nachkommastellen
	 */
	public static BigDecimal pi(int numDigits) {
		int calcDigits = numDigits + 10;
		
		return FOUR.multiply((FOUR.multiply(arccot(FIVE, calcDigits)))
				.subtract(arccot(TWO_THIRTY_NINE, calcDigits)))
				.setScale(numDigits, RoundingMode.DOWN);
	}
	
	/*
	 * Diese Methode ist fuer die sequenzielle Berechnung von Pi zustaendig
	 * @param x - Zahl zur berechnung von Pi
	 * @param numDigits - Anzahl der gewuenschten Nachkommastellen
	 */
	private static BigDecimal arccot(BigDecimal x, int numDigits) {

		BigDecimal unity = BigDecimal.ONE.setScale(numDigits,RoundingMode.DOWN);
		BigDecimal sum = unity.divide(x, RoundingMode.DOWN);
		BigDecimal xpower = new BigDecimal(sum.toString());
		BigDecimal term = null;

		boolean add = false;

		for (BigDecimal n = new BigDecimal("3"); term == null ||term.compareTo(BigDecimal.ZERO) != 0; n = n.add(TWO)) {
			xpower = xpower.divide(x.pow(2), RoundingMode.DOWN);
			term = xpower.divide(n, RoundingMode.DOWN);
			sum = add ? sum.add(term) : sum.subtract(term);
			add = ! add;
		}
		return sum;
	}
}

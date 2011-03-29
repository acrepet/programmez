package com.programmez.samples.gigreservation.domain;

import static java.lang.String.format;

/**
 *
 * @author agnes007
 */
public class InsufficientTicketsException extends Exception {
	private final Ticketing ticketing;
	private final int nbOrderingTickets;
	
	public InsufficientTicketsException(Ticketing ticketing, int nbOrderingTickets) {
		this.ticketing = Ticketing.copy(ticketing);
		this.nbOrderingTickets = nbOrderingTickets;
	}

	public String getBandGigTicketing() {
		return ticketing.getBand();
	}

	public double getNbTicketsRemaining() {
		return ticketing.getNbTickets();
	}

	public double getNbOrderingTickets() {
		return nbOrderingTickets;
	}
	

	public String toString() {
		StringBuilder sb = new StringBuilder()
			.append(format("Failed to order $%d from Ticketing of the Band %s. " +
					"Reason: insufficient funds\n", getNbOrderingTickets(), getBandGigTicketing()))
			.append(format("\tcurrent remaining tickets for Ticketing is %d\n", getNbTicketsRemaining()));
		return sb.toString();
	}
}

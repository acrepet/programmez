package com.programmez.samples.gigreservation.domain;

/**
 *
 * @author agnes007
 */
public class Ticketing {

    private final Long id;
    private Long nbTickets;
    private String band;

    public Ticketing(Long id, long inititalNbTickets, String band) {
        this.id = id;
        this.nbTickets = inititalNbTickets;
        this.band = band;
    }

    public Long getId() {
        return id;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }


    public Long getNbTickets() {
        return nbTickets;
    }

    public void setNbTickets(Long nbTickets) {
        this.nbTickets = nbTickets;
    }

    public void orderTicket(int nbOrderingTickets) throws InsufficientTicketsException {
        assertNotEmpty(nbOrderingTickets);

        if (nbOrderingTickets > nbTickets) {
            throw new InsufficientTicketsException(this, nbOrderingTickets);
        }
        nbTickets -= nbOrderingTickets;
    }

    private void assertNotEmpty(int nbOrderingTickets) {
        if (!(nbOrderingTickets > 0)) {
            throw new IllegalArgumentException("nbOrderingTickets must be greater than zero");
        }
    }

    public static Ticketing copy(Ticketing ticketing) {
        return new Ticketing(ticketing.getId(),ticketing.getNbTickets(), ticketing.getBand());
    }
}

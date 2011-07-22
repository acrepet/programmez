package com.programmez.samples.gigreservation.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Ticketing
 * @author agnes007
 */
@Entity
public class Ticketing {

    @Id
    private Long id;
    @Basic
    private Long nbTickets;
    @Basic
    private String band;

    public Ticketing() {
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticketing other = (Ticketing) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.nbTickets != other.nbTickets && (this.nbTickets == null || !this.nbTickets.equals(other.nbTickets))) {
            return false;
        }
        if ((this.band == null) ? (other.band != null) : !this.band.equals(other.band)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + (this.nbTickets != null ? this.nbTickets.hashCode() : 0);
        hash = 97 * hash + (this.band != null ? this.band.hashCode() : 0);
        return hash;
    }
    
}

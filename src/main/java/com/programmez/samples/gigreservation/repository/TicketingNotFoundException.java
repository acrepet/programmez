/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.programmez.samples.gigreservation.repository;

/**
 *
 * @author agnes007
 */
public class TicketingNotFoundException extends RuntimeException {

    /**
     * Creates a new instance of <code>TicketingNotFoundException</code> without id of ticketing
     */
    public TicketingNotFoundException(Long ticketingId) {
        super(String.format("ticketing with id [%d] could not be found", ticketingId));
    }

    /**
     * Constructs an instance of <code>TicketingNotFoundException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public TicketingNotFoundException(String msg) {
        super(msg);
    }
}

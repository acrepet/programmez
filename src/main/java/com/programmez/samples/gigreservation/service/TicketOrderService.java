/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.service;

import com.programmez.samples.gigreservation.domain.InsufficientTicketsException;

/**
 *
 * @author agnes007
 */
public interface TicketOrderService {

    	void order(int nbTickets,  Long ticketingId)
		throws InsufficientTicketsException;

}

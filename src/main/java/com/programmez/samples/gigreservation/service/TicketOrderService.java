/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.service;

import com.programmez.samples.gigreservation.domain.InsufficientTicketsException;
import org.springframework.stereotype.Service;

/**
 *
 * @author agnes007
 */
@Service
public interface TicketOrderService {

    	void order(int nbTickets,  Long ticketingId)
		throws InsufficientTicketsException;

}

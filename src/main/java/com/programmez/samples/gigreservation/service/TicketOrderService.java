/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.service;

import com.programmez.samples.gigreservation.domain.InsufficientTicketsException;
import com.programmez.samples.gigreservation.domain.Ticketing;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author agnes007
 */
@Service
public interface TicketOrderService {

    	public void order(int nbTickets,  Long ticketingId)
		throws InsufficientTicketsException;

        public List<Ticketing> searchTicketingsByBand(String bandName);

}

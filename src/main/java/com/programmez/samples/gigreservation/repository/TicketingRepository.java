/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.repository;

import com.programmez.samples.gigreservation.domain.Ticketing;

/**
 *
 * @author agnes007
 */
public interface TicketingRepository {

	Ticketing findById(Long ticketingId);

	void updateNbTickets(Ticketing ticketing);

}

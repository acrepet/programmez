/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.programmez.samples.gigreservation.domain.Ticketing;

/**
 *
 * @author agnes007
 */
public interface TicketingRepository {

	@Cacheable(value = "ticket", key = "#ticketingId")
	Ticketing findById(Long ticketingId);

	@CacheEvict(value = {"ticket", "tickets"}, key = "#ticketing.id")
	void updateNbTickets(Ticketing ticketing);

	@Cacheable(value = "tickets", key = "#ticketingBand", condition = "#ticketingBand.startsWith('A')")
	List<Ticketing> findByBand(String ticketingBand, boolean audit);

}

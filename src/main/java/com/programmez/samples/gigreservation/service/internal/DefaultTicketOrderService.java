package com.programmez.samples.gigreservation.service.internal;

import static java.lang.String.format;

import org.springframework.transaction.annotation.Transactional;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.domain.InsufficientTicketsException;
import com.programmez.samples.gigreservation.repository.TicketingRepository;
import com.programmez.samples.gigreservation.service.TicketOrderService;

/**
 *
 * @author agnes007
 */
public class DefaultTicketOrderService implements TicketOrderService {

	private final TicketingRepository ticketingRepository;

	public DefaultTicketOrderService(TicketingRepository TicketingRepository) {
		this.ticketingRepository = TicketingRepository;
	}



	@Override
	@Transactional
	public void order(int nbTickets, Long ticketingId) throws InsufficientTicketsException{
		if (nbTickets < 1)
			throw new IllegalArgumentException(
					format("order must be at least 1 ticket"));



		Ticketing ticketing = ticketingRepository.findById(ticketingId);

		ticketing.orderTicket(nbTickets);

		ticketingRepository.updateNbTickets(ticketing);


	}


}

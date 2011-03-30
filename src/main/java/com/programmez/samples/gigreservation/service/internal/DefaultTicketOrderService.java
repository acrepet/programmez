package com.programmez.samples.gigreservation.service.internal;

import static java.lang.String.format;

import org.springframework.transaction.annotation.Transactional;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.domain.InsufficientTicketsException;
import com.programmez.samples.gigreservation.repository.TicketingRepository;
import com.programmez.samples.gigreservation.service.GigService;
import com.programmez.samples.gigreservation.service.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author agnes007
 */
@GigService
public class DefaultTicketOrderService implements TicketOrderService {

    @Autowired
    private TicketingRepository ticketingRepository;

    public DefaultTicketOrderService(TicketingRepository TicketingRepository) {
        this.ticketingRepository = TicketingRepository;
    }

    public DefaultTicketOrderService() {
    }

    @Override
    @Transactional
    public void order(int nbTickets, Long ticketingId) throws InsufficientTicketsException {
        if (nbTickets < 1) {
            throw new IllegalArgumentException(
                    format("order must be at least 1 ticket"));
        }



        Ticketing ticketing = ticketingRepository.findById(ticketingId);

        ticketing.orderTicket(nbTickets);

        ticketingRepository.updateNbTickets(ticketing);


    }
}

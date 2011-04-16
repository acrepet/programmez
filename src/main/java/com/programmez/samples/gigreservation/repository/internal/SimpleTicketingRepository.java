/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.programmez.samples.gigreservation.repository.internal;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.repository.TicketingNotFoundException;
import com.programmez.samples.gigreservation.repository.TicketingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author agnes007
 */
public class SimpleTicketingRepository implements TicketingRepository{

    public static class Data {

        public static final Long TICKETING_A_ID = new Long(1);
        public static final Long TICKETING_B_ID = new Long(2);
        public static final long BAND_A_ID = 1;
        public static final long BAND_B_ID = 2;
        public static final String BAND_A_NAME = "Arcade Fire";
        public static final String BAND_B_NAME = "Fugazi";
        public static final long TICKETING_Z_ID = 99; // NON EXISTENT ID
        public static final long TICKETING_A_INITIAL_NBTICKETS = 100;
        public static final long TICKETING_B_INITIAL_NBTICKETS = 0;
    }
    @SuppressWarnings("serial")
    private final Map<Long, Ticketing> ticketingsById = new HashMap<Long, Ticketing>() {
        {
            put(Data.TICKETING_A_ID, new Ticketing(Data.TICKETING_A_ID,Data.TICKETING_A_INITIAL_NBTICKETS, Data.BAND_A_NAME));
            put(Data.TICKETING_B_ID, new Ticketing(Data.TICKETING_B_ID, Data.TICKETING_B_INITIAL_NBTICKETS,Data.BAND_B_NAME));
        }
    };

    @Override
    public Ticketing findById(Long ticketingId) {
        return Ticketing.copy(nullSafeTicketingLookup(ticketingId));
    }

    @Override
    public void updateNbTickets(Ticketing ticketing)  {
        Ticketing actualTicketing = nullSafeTicketingLookup(ticketing.getId());
        actualTicketing.setNbTickets(ticketing.getNbTickets());
    }

    private Ticketing nullSafeTicketingLookup(Long ticketingId) {
        Ticketing Ticketing = ticketingsById.get(ticketingId);
        if (Ticketing == null) {
            throw new TicketingNotFoundException(ticketingId);
        }
        return Ticketing;
    }

	@Override
	public List<Ticketing> findByBand(String ticketingBand, boolean audit) {
		return new ArrayList<Ticketing>();
	}
}

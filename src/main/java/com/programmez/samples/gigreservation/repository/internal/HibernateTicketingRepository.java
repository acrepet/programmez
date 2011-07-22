/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.programmez.samples.gigreservation.repository.internal;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.repository.TicketingRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Sryl <cyril.lacote@gmail.com>
 */
// @Repository("hibernate")
public class HibernateTicketingRepository implements TicketingRepository {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Ticketing findById(Long ticketingId) {
        return (Ticketing) sessionFactory.getCurrentSession().get(Ticketing.class, ticketingId);
    }

    @Override
    public void updateNbTickets(Ticketing ticketing) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Ticketing> findByBand(String ticketingBand, boolean audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

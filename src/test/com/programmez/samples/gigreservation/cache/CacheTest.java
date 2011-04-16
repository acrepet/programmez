package com.programmez.samples.gigreservation.cache;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.repository.TicketingRepository;

/**
 *
 * @author agnes007
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/programmez/samples/gigreservation/config/xml/cache-service-config.xml")
public class CacheTest {

	@Inject
	TicketingRepository repo;
	
	public static final ThreadLocal<AtomicInteger> hitRepo = new ThreadLocal<AtomicInteger>();
	
	private Integer previousValue;
	
	@Before
	public void initHitRepo() {
		previousValue = 0;
		hitRepo.set(new AtomicInteger());
	}
	
    @Test
    @DirtiesContext
    public void testCacheable() {
    	Ticketing ticketFromDb = repo.findById(1L);
    	assertNotNull(ticketFromDb);
    	assertHitDb();
    	
    	Ticketing ticketFromCache = repo.findById(1L);
    	assertNotNull(ticketFromCache);
    	assertHitCache();
    	assertEquals(ticketFromDb, ticketFromCache);
    }
    
    @Test
    @DirtiesContext
    public void testCacheEvict() {
    	Ticketing ticketFromDb = repo.findById(1L);
    	assertNotNull(ticketFromDb);
    	assertHitDb();
    	
    	ticketFromDb.setNbTickets(12L);
    	repo.updateNbTickets(ticketFromDb);
    	
    	Ticketing ticketEvicted = repo.findById(1L);
    	assertNotNull(ticketEvicted);
    	assertHitDb();
    }
    
    @Test
    @DirtiesContext
    public void testCacheConditional() {
    	List<Ticketing> ticketsFromDb = repo.findByBand("An", false);
    	assertNotNull(ticketsFromDb);
    	assertEquals(2, ticketsFromDb.size());
    	assertHitDb();
    	
    	repo.findByBand("An", false);
    	assertHitCache();
    	
    	repo.findByBand("Fugazi", false);
    	assertHitDb();
    	repo.findByBand("Fugazi", false);
    	assertHitDb();
    }
    
    private void assertHitDb() {
    	assertEquals(previousValue + 1, hitRepo.get().get());
    	previousValue++;
    }
    
    private void assertHitCache() {
    	assertEquals(previousValue.intValue(), hitRepo.get().get());
    }

}

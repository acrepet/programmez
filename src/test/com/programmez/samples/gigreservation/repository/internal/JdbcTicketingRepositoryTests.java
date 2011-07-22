/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.repository.internal;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.repository.TicketingRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
 import static org.junit.Assert.*;

/**
 *
 * @author agnes007
 */
public class JdbcTicketingRepositoryTests {

    public JdbcTicketingRepositoryTests() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    	@Test
	public void listTicketings(){
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("dev");
		ctx.load("classpath:com/programmez/samples/gigreservation/config/xml/ticketorder-service-config.xml");
		ctx.refresh();
                List<Ticketing> expected = new ArrayList<Ticketing>();
                expected.add(new Ticketing(new Long(2), 0, "Fugazi"));

		TicketingRepository ticketingRepository = ctx.getBean(TicketingRepository.class);
                List<Ticketing> ticketings = ticketingRepository.findByBand("Fugazi",false);

		assertEquals(expected,ticketings);

	}

}
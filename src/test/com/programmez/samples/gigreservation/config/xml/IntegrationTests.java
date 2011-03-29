package com.programmez.samples.gigreservation.config.xml;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.programmez.samples.gigreservation.domain.InsufficientTicketsException;
import com.programmez.samples.gigreservation.repository.TicketingRepository;
import com.programmez.samples.gigreservation.service.TicketOrderService;

/**
 * Tests with plain XML configuration
 * @author agnes007
 */
public class IntegrationTests {

	@Test
	public void orderTenTickets() throws InsufficientTicketsException {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("dev");
		ctx.load("classpath:com/programmez/samples/gigreservation/config/xml/ticketorder-service-config.xml");
		ctx.refresh();

		TicketOrderService ticketOrderService = ctx.getBean(TicketOrderService.class);
		TicketingRepository ticketingRepository = ctx.getBean(TicketingRepository.class);


		assertThat(ticketingRepository.findById(new Long(1)).getNbTickets(), equalTo(100L));

		ticketOrderService.order(10,new Long(1));

		assertThat(ticketingRepository.findById(new Long(1)).getNbTickets(), equalTo(90L));
	}

}

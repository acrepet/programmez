package com.programmez.samples.gigreservation.config.code;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.programmez.samples.gigreservation.domain.InsufficientTicketsException;
import com.programmez.samples.gigreservation.repository.TicketingRepository;
import com.programmez.samples.gigreservation.service.TicketOrderService;

/**
 * Tests with Java configuration
 * @author agnes007
 */
public class IntegrationConfJavaTests {

	@Test
	public void orderTenTickets() throws InsufficientTicketsException {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().setDefaultProfiles("dev");
		ctx.register(OrderServiceConfig.class, StandaloneDataConfig.class, JndiDataConfig.class);
		ctx.refresh();

		TicketOrderService ticketOrderService = ctx.getBean(TicketOrderService.class);
		TicketingRepository ticketingRepository = ctx.getBean(TicketingRepository.class);

		assertThat(ticketingRepository.findById(new Long(1)).getNbTickets(), equalTo(100L));

		ticketOrderService.order(10,new Long(1));

		assertThat(ticketingRepository.findById(new Long(1)).getNbTickets(), equalTo(90L));
	}

}

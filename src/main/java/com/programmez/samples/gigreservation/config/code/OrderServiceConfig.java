package com.programmez.samples.gigreservation.config.code;

import com.programmez.samples.gigreservation.repository.TicketingRepository;
import com.programmez.samples.gigreservation.repository.internal.JdbcTicketingRepository;
import com.programmez.samples.gigreservation.service.TicketOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.programmez.samples.gigreservation.service.internal.DefaultTicketOrderService;

/**
 *
 * @author agnes007
 */
@Configuration
public class OrderServiceConfig {

    @Autowired
    DataConfig dataConfig;

    @Bean
    public TicketOrderService orderService() {
        return new DefaultTicketOrderService(ticketingRepository());
    }

    @Bean
    public TicketingRepository ticketingRepository() {
        return new JdbcTicketingRepository(dataConfig.dataSource());
    }
}

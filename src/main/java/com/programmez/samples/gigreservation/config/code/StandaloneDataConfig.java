package com.programmez.samples.gigreservation.config.code;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 *
 * @author agnes007
 */
@Configuration
@Profile("dev")
public class StandaloneDataConfig implements DataConfig {

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:com/programmez/samples/gigreservation/config/sql/schema.sql")
			.addScript("classpath:com/programmez/samples/gigreservation/config/sql/test-data.sql")
			.build();
	}
}

package com.programmez.samples.gigreservation.repository.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.repository.TicketingRepository;

/**
 *
 * @author agnes007
 */
public class JdbcTicketingRepository implements TicketingRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTicketingRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void updateNbTickets(Ticketing ticketing) {
        jdbcTemplate.update("update ticketing set nbTickets = ? where id = ?", ticketing.getNbTickets(), ticketing.getId());
    }

    @Override
    public Ticketing findById(Long ticketingId) {
        return jdbcTemplate.queryForObject("select id, nbTickets, band from ticketing where id = ?", new TicketingRowMapper(), ticketingId);
    }

    private static class TicketingRowMapper implements RowMapper<Ticketing> {

        @Override
        public Ticketing mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Ticketing(rs.getLong("ID"), rs.getLong("NBTICKETS"), rs.getString("BAND"));
        }
    }
}

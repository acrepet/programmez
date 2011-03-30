package com.programmez.samples.gigreservation.repository.internal;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.programmez.samples.gigreservation.domain.Ticketing;
import com.programmez.samples.gigreservation.repository.TicketingRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agnes007
 */
@Repository
public class JdbcTicketingRepository implements TicketingRepository {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    public JdbcTicketingRepository() {
    }

    public JdbcTicketingRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void inittemplate() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void updateNbTickets(Ticketing ticketing) {
        jdbcTemplate.update("update ticketing set nbTickets = ? where id = ?", ticketing.getNbTickets(), ticketing.getId());
    }

    public void setJdbcTemplate(JdbcTemplate template) {
        jdbcTemplate = template;
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

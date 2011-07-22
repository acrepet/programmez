/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.repository.internal;

import com.programmez.samples.gigreservation.domain.Ticketing;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author agnes007
 */
public class TicketingResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException {
		Ticketing ticketing = new Ticketing();
		ticketing.setNbTickets(rs.getLong(1));
		ticketing.setBand(rs.getString(2));
		return ticketing;
	}

}


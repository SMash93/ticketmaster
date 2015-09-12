package edu.msg.ticketmaster.webPlayground;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.msg.ticketmaster.backend.service.local.RoomService;
import edu.msg.ticketmaster.backend.service.local.SeatService;

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SeatService seatService;

	@EJB
	RoomService roomService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		roomService.findAllRooms();
		seatService.findAllSeats();
	}

}

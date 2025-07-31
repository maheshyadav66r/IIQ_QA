package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.eshiam.domains.AppointmentBooking;
import com.eshiam.domains.DoctorRegistry;
import com.eshiam.interfaces.AppointmentBookingService;
import com.eshiam.jsonutils.JSonUtils;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.servicelayers.AppointmentBookingServiceImpl;

/**
 * Servlet implementation class AppointmentBookingControllerServlet
 */
@WebServlet("/AppointmentBookingControllerServlet")
public class AppointmentBookingControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AppointmentBookingService service = new AppointmentBookingServiceImpl();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppointmentBookingControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String data = IOUtils.toString(request.getInputStream(), "UTF-8");
		AppointmentBooking appointmentBooking = null;
		try {
			appointmentBooking = JSonUtils.getObjectFromJasonString(data, AppointmentBooking.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String action = appointmentBooking.getAction();
		ServiceDTO<AppointmentBooking> dto = null;
		if ("Retrieve".equals(action)) {
			dto = service.retrieveAppointmentBooking(appointmentBooking);
		} else if ("Delete".equals(action)) {
			dto = service.deleteAppointmentBooking(appointmentBooking);
		} else {
			dto = service.saveAppointmentBooking(appointmentBooking);
		}
		response.getWriter().append(JSonUtils.getJsonStringFromObject(dto));

	}
	}



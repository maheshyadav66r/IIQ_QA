package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.eshiam.domains.DoctorsTimeOff;
import com.eshiam.interfaces.DoctorsTimeOffService;
import com.eshiam.jsonutils.JSonUtils;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.servicelayers.DoctorsTimeOffServiceImpl;

/**
 * Servlet implementation class DoctorsTimeOffControllerServlet
 */
@WebServlet("/DoctorsTimeOffControllerServlet")
public class DoctorsTimeOffControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DoctorsTimeOffService service = new DoctorsTimeOffServiceImpl();
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoctorsTimeOffControllerServlet() {
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
		DoctorsTimeOff doctorsTimeOff = null;
		try {
			doctorsTimeOff = JSonUtils.getObjectFromJasonString(data, DoctorsTimeOff.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String action = doctorsTimeOff.getAction();
		ServiceDTO<DoctorsTimeOff> dto = null;
		if ("Retrieve".equals(action)) {
			dto = service.retrieveDoctorsTimeOff(doctorsTimeOff);
		} else if ("Delete".equals(action)) {
			dto = service.deleteDoctorsTimeOff(doctorsTimeOff);
		} else {
			dto = service.saveDoctorsTimeOff(doctorsTimeOff);
		}
		response.getWriter().append(JSonUtils.getJsonStringFromObject(dto));

	
	}

}

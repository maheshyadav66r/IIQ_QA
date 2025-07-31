package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.eshiam.domains.DoctorRegistry;
import com.eshiam.interfaces.DoctorRegistryService;
import com.eshiam.jsonutils.JSonUtils;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.servicelayers.DoctorRegistryServiceImpl;

/**
 * Servlet implementation class DoctorRegistryControllerServlet
 */
@WebServlet("/DoctorRegistryControllerServlet")
public class DoctorRegistryControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DoctorRegistryService service = new DoctorRegistryServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoctorRegistryControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		String data = IOUtils.toString(request.getInputStream(), "UTF-8");
		DoctorRegistry doctorRegistry = null;
		try {
			doctorRegistry = JSonUtils.getObjectFromJasonString(data, DoctorRegistry.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String action = doctorRegistry.getAction();
		ServiceDTO<DoctorRegistry> dto = null;
		if ("Retrieve".equals(action)) {
			dto = service.retrieveDoctorRegistry(doctorRegistry);
		} else if ("Delete".equals(action)) {
			dto = service.deleteDoctorRegistry(doctorRegistry);
		} else {
			dto = service.saveDoctorRegistry(doctorRegistry);
		}
		response.getWriter().append(JSonUtils.getJsonStringFromObject(dto));

	}

}

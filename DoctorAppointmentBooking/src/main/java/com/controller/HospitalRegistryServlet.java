package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.eshiam.domains.HospitalRegistry;
import com.eshiam.interfaces.HospitalRegistryService;
import com.eshiam.jsonutils.JSonUtils;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.eshiam.servicelayers.HospitalRegistryServiceImpl;

/**
 * Servlet implementation class HospitalRegistryServlet
 */
@WebServlet("/HospitalRegistryServlet")
public class HospitalRegistryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HospitalRegistryService service = new HospitalRegistryServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HospitalRegistryServlet() {
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
		HospitalRegistry hospitalRegistry = null;
		try {
			hospitalRegistry = JSonUtils.getObjectFromJasonString(data, HospitalRegistry.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String action = hospitalRegistry.getAction();
		ServiceDTO<HospitalRegistry> dto = null;
		if ("Retrieve".equals(action)) {
			dto = service.retrieveHospitalRegistry(hospitalRegistry);
		} else if ("Delete".equals(action)) {
			dto = service.deleteHospitalRegistry(hospitalRegistry);
		} else {
			dto = service.saveHospitalRegistry(hospitalRegistry);
		}
		response.getWriter().append(JSonUtils.getJsonStringFromObject(dto));

	}

}

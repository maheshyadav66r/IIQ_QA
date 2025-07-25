package com.eshiam.jsonutils;

import org.joda.time.DateTime;

import com.eshiam.domains.HospitalRegistry;
import com.eshiam.domains.HospitalToDoctor;
import com.eshiam.serviceDTOs.ServiceDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JSonUtils {
//	public static void main(String[] args) throws Exception {
//		HospitalRegistry hospitalRegistry = new HospitalRegistry();
//		HospitalToDoctor hospitalToDoctor = new HospitalToDoctor();
//
//		hospitalRegistry.setId(1521);
//		hospitalRegistry.setHospitalCode("ss");
//		hospitalRegistry.setHospitalName("Sunshine");
//		hospitalRegistry.setHospitalStartDate(new DateTime());
//
//		hospitalToDoctor.setId(45235);
//		hospitalToDoctor.setHospitalId(hospitalRegistry.getId());
//		hospitalToDoctor.setDoctorId(112);
//		hospitalToDoctor.setAppointmentFee(250000000);
//
//		hospitalRegistry.getHospitalTodoctorList().add(hospitalToDoctor);
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JodaModule());
//		String jSonString = objectMapper.writeValueAsString(hospitalRegistry);
//   		System.out.println(jSonString);
//
//	}

	public static <T> T getObjectFromJasonString(String jsonString, Class<T> classType) throws Exception, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		T object = mapper.readValue(jsonString, classType);
		return object;
	}

	public static <T> String getJsonStringFromObject(ServiceDTO<T> dto) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		String json = mapper.writeValueAsString(dto);
		return json;
	}

}

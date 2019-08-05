package com.AutomatizacionControlada;

import com.AutomatizacionControlada.models.Employee;
import com.AutomatizacionControlada.repository.EmployeeRepository;
import com.AutomatizacionControlada.util.Position;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@TestPropertySource("/application.properties")
public class AutomatizacionControladaTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private EmployeeRepository employeeRepository;


	@Test
	public void createEmployee() throws Exception {

		Map<String, String> map = new HashMap<>();
		map.put("name", "Juan");
		map.put("dni", "38676779");

		final ObjectMapper mapper = new ObjectMapper();
		final String requestBody = mapper.writeValueAsString(map);

		mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
				.andExpect(status().isCreated());
	}

	@Test
	public void createEmployeePrimal() throws Exception {

		Employee employee = new Employee("juan","doyle", Position.BOSS);
		employeeRepository.save(employee);
		employeeRepository.deleteAll();
	}

}



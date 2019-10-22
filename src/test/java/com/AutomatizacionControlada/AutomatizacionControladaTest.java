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

import java.util.*;

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

		Employee employee = new Employee("juan","doyle", Position.BOSS, false);
		employeeRepository.save(employee);
		employeeRepository.deleteAll();
	}

	@Test
	public void sortNumbers(){
//		int[] numbers = {2,3,1,4};
		List<String> numbers = new ArrayList<>();
		numbers.add("B");
		numbers.add("C");
		numbers.add("A");
		numbers.add("D");

		Scanner sc = new Scanner(System.in);

		for (String p: numbers) {
			System.out.println(p);
		}
		System.out.println("Choose a person to be your friend by writting down the name");
		String letter_choosen = sc.nextLine();


		boolean conteins = numbers.contains(letter_choosen);

		System.out.println(conteins);

//		makefriends()
//		removefriends()
//		getDirectFriends()
//		getIndirectFriends()
	}
}

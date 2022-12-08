package canard.intern.post.following.backend.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import canard.intern.post.following.backend.controller.fixture.TraineeJsonProvider;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.validators.DateLessThan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = TraineeController.class)
class TraineeControllerTest {

	final static String BASE_URL="/api/trainees";
	final static String URL_TEMPLATE_ID= BASE_URL+ "/{id}";
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void getAll() throws Exception{
		mockMvc.perform(get(BASE_URL))
				.andDo(print());
	}
	@Test
	void getById() throws Exception {
		int id=2;
			mockMvc.perform(get(URL_TEMPLATE_ID,id).accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").value(id));
		
	}
	
	@Test
	void getById_KO_idNotFound() throws Exception {
		// TODO
		fail("Test not defined yet");
			
		
	}
	@Test
	void create_ok_allfieldsvalid() throws Exception{
		var trainee = TraineeJsonProvider.traineeJsonAllFieldsValid();
		System.out.println(trainee);
		
		mockMvc.perform(post(BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(trainee)
		)
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists());
	
		// NB: check al fields in response are equals to request fields
	}
	
	@ParameterizedTest
	@MethodSource("canard.intern.post.following.backend.controller.fixture.TraineeJsonProvider#traineeJsonMissingNonRequiredFields")
	void create_ok_missing_non_required_fields(String traineeJson) throws Exception{
		
		//lastname, firstname, email, birthdate
		var trainee = TraineeJsonProvider.traineeJsonAllFieldsValid();
		mockMvc.perform(post(BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(trainee)
		)
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists());
	}
	
	
	@Test
	void create_KO_missing_required_fields() throws Exception{
		var traineeJson = TraineeJsonProvider.traineeJsonMissingRequiredFields();
		mockMvc.perform(post(BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(traineeJson)
		)
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	
	@Test
	void create_KO_invalid_fields() throws Exception{
		//email ,birthdate 18 years, phoneNumber pattern
		var traineeJson = TraineeJsonProvider.traineeJsonInvalid();
		mockMvc.perform(post(BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(traineeJson)
		)
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	
	
	@Test
	void create_KO_invalidBirthDate() throws Exception{
		//email ,birthdate 18 years, phoneNumber pattern
		var traineeJson = TraineeJsonProvider.traineeJsonInvalidBirdthdate();
		mockMvc.perform(post(BASE_URL)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(traineeJson)
		)
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	
	 @Test
	    void update_OK() throws Exception{
	        // id found +
	        // - all fields valid + no id in body
	        // - all fields valid + id in body equals to id in path
		 int id=2;
		 var traineeJson = TraineeJsonProvider.traineeJsonInvalidBirdthdate();
	    	mockMvc.perform(put(URL_TEMPLATE_ID,id)
	    			.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(traineeJson)
					)
	    	.andDo(print())
	    	.andExpect(status().isNoContent())
			.andExpect(jsonPath("$.id").exists());
	    }

	    @Test
	    void update_KO() {
	        // - id not found
	        // - id found +
	        //      . invalid field(s)
	        //      . all fields valid + id in body different from id in path
	        fail("Test not defined yet");
	    }

	    @Test
	    void delete_OK() throws Exception{
	        // id found
	    	int id=2;
	    	mockMvc.perform(delete(URL_TEMPLATE_ID,id))
	    	.andDo(print())
	    	.andExpect(status().isNoContent());
			//.andExpect(jsonPath("$.id").doesNotExist());
	    }
	    @Test
	    void delete_KO() {
	        // id not found
	        fail("Test not defined yet");
	    }
	

}

package canard.intern.post.following.backend.controller.fixture;

import java.time.LocalDate;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TraineeJsonProvider {
	
	private static ObjectNode traineeJsonRequiredFields() {
		
			ObjectMapper objectMapper = new ObjectMapper();
			var  trainee = objectMapper.createObjectNode();
			return trainee.put("lastname","bill")
			.put("firstname","bull")
			.put("birthdate","1950-02-05")
			.put("email", "bill@gmail.com");	
	}
		
	public static String traineeJsonAllFieldsValid() {
		return traineeJsonRequiredFields().put("gender","M").put("phoneNumber","+3341526395").toPrettyString();
		
		
	}

	public static Stream<String> traineeJsonMissingNonRequiredFields() {
		return Stream.of(
				traineeJsonRequiredFields().toPrettyString(),
				traineeJsonRequiredFields().put("gender", "M")
				.toPrettyString(),
				traineeJsonRequiredFields().put("phoneNumber", "+3314526398").toPrettyString());
	}
	
	public static String traineeJsonInvalidBirdthdate() {
		var wrongBirthdate = LocalDate.now().minusYears(18).plusDays(1L);
		ObjectMapper objectMapper = new ObjectMapper();
		var  trainee = objectMapper.createObjectNode();
		return trainee.put("lastname","bill")
		.put("firstname","bull")
		.put("birthdate",wrongBirthdate.toString())
		.put("email", "bill@gmail.com").toPrettyString();	
	}
	
	
	public static String traineeJsonMissingRequiredFields() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		var  trainee = objectMapper.createObjectNode();
		return trainee.put("lastname","bill")
				.put("birthdate","1950-05-02")
				.put("email", "bill@gmail.com")
				.put("phoneNUmber","+3314526398").toPrettyString();	
	}
	
	
	
	public static String traineeJsonInvalid() {
		var wrongBirthdate = LocalDate.now().minusYears(18).plusDays(1L);
		ObjectMapper objectMapper = new ObjectMapper();
		var  trainee = objectMapper.createObjectNode();
		return trainee.put("lastname","bill")
		.put("firstname","bull")
		.put("birthdate",wrongBirthdate.toString())
		.put("email", "bill@gmail.com")
		.put("phoneNUmber","0714526398").toPrettyString();
			
	}
}

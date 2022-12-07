package canard.intern.post.following.backend.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import canard.intern.post.following.backend.enums.Gender;
import canard.intern.post.following.backend.validators.DateLessThan;
import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class TraineeDto {
	
	private Integer id;
	
	@NotBlank
	private String lastname;
	
	@NotBlank
	private String firstname;
	
	private Gender gender;
	
	@NotNull
	//@Past //TODO: replace with custom current date -18 year
	@DateLessThan
	private LocalDate birthdate;
	@Pattern(regexp = "^\\+(?:[0-9]?){6,14}[0-9]$")
	private String phoneNumber;
	
	@NotNull
	@Email
	private String email;
	
}

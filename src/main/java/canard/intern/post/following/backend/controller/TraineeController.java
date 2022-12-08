package canard.intern.post.following.backend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.enums.Gender;

@RestController
@RequestMapping("/api/trainees")

public class TraineeController {
	
	/**
	 * *Get /api/trainees
	 * @return
	 */
	
	@GetMapping()
	public List<TraineeDto> getAll() {
		return List.of(
				TraineeDto.builder().id(1).lastname("bond").firstname("James").gender(Gender.M).birthdate(LocalDate.of(1945, 5, 16)).build(),
				TraineeDto.builder().id(2).lastname("Neymar").firstname("Jean").gender(Gender.M).birthdate(LocalDate.of(1994, 3, 16)).build(),
				TraineeDto.builder().id(3).lastname("Money").firstname("Poney").gender(Gender.M).birthdate(LocalDate.of(1988, 5, 6)).build()
				);
	}
	
	@GetMapping("/{id}")
	public TraineeDto getById(@PathVariable("id") int id) {
			
		return TraineeDto.builder().id(id).lastname("bond").firstname("James").gender(Gender.M).birthdate(LocalDate.of(1945, 5, 16)).build();
	}

	/**
	 * GET /api/trainees/{id}
	 * @param id
	 * @return trainee with this id  if found
	 */
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public TraineeDto create(@Valid @RequestBody TraineeDto traineeDto) {
		traineeDto.setId(4);
		return traineeDto;
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") int id) {
		// TODO: remove Trainee with this id
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TraineeDto update(
			@PathVariable("id") int id,
			@Valid @RequestBody TraineeDto traineeDto) {
	if(Objects.nonNull(traineeDto.getId()) && (traineeDto.getId() != id)) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Id <?> from path does not match id <?> from body", id , traineeDto.getId()));
	}
	return traineeDto;
	
	}

	
}




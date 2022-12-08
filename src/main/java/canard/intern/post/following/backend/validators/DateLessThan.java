package canard.intern.post.following.backend.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= DateLessThanValidator.class)
@Documented
public @interface DateLessThan{
	 String message() default "date is not before 18 years ago";

	    Class<?>[] groups() default {};

	    Class<? extends Payload>[] payload() default {};
	
	
}

class DateLessThanValidator implements ConstraintValidator<DateLessThan, LocalDate>{

	@Override
	public boolean isValid(LocalDate localDate, ConstraintValidatorContext constrainValidationContext) {
		// constraint transparent if localDate is null
		if(Objects.isNull(localDate)) {
			return true;
		}
		// localDate is not null: check custom constraint 
		LocalDate today = LocalDate.now();
		LocalDate date18yearsAgo = today.minusYears(18L);
		
		return localDate.compareTo(date18yearsAgo) <= 0 ;
	}

}

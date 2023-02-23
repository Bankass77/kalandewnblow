package DensagouService.userService.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import DensagouService.userService.constraint.NotExistingUser;
import DensagouService.userService.domain.Email;
import DensagouService.userService.response.AbstractUserFormData;
import DensagouService.userService.service.StudentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotExistingUserValidator implements ConstraintValidator<NotExistingUser, AbstractUserFormData> {

	private final StudentService userService;

	@Autowired
	public NotExistingUserValidator(StudentService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public void initialize(NotExistingUser constraint) {

	}

	@Override
	public boolean isValid(AbstractUserFormData value, ConstraintValidatorContext context) {

		if (!StringUtils.isEmpty(value.getEmail()) && userService.userWithEmailExists(new Email(value.getEmail()))) {

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{UserAlreadyExisting}").addPropertyNode("email")
					.addConstraintViolation();
			return false;
		}
		return true;
	}

}

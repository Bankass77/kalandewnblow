package DensagouService.userService.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({ Default.class, ValidationGroupOne.class, ValidationGroupTwo.class })
public interface CreateUserValidationGroupSequence {

}

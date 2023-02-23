package DensagouService.userService.domain;

import java.io.Serializable;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PhoneNumber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String phoneNumber;

	private static final Pattern VALIDATION_PATTERN = Pattern.compile("^(?:(?:\\+|00)223)\\s*[6-7]{2}\\d{6,8}$");

	public PhoneNumber(String phString) {
		Assert.hasText(phString, "PhoneNumber cannot be blank.");
		Assert.isTrue(VALIDATION_PATTERN.asPredicate().test(phString), "PhoneNumber does not have proper format.");
		this.phoneNumber = phString;
	}

	public String asString() {

		return phoneNumber;
	}
}

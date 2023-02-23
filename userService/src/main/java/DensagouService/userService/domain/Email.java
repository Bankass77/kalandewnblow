package DensagouService.userService.domain;

import java.io.Serializable;

import DensagouService.userService.constraint.EmailConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmailConstraint
	private String email;

	public String asString() {
		return email;
	}

}

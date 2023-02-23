package DensagouService.userService.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(insertable = true, updatable = true)
	private String street;

	@Column(insertable = true, updatable = true)
	@Digits(integer = 3, fraction = 0)
	private int streetNumber;

	@Column(insertable = true, updatable = true)
	private String city;

	@Column(insertable = true, updatable = true)
	@Pattern(regexp = "^(?:0[1-9]|[1-8]\\d|9[0-8])\\d{3}$")
	private Integer codePostale;

	@Column(insertable = true, updatable = true)
	private String country;
}

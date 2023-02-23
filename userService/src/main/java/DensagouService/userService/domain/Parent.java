package DensagouService.userService.domain;

import java.time.LocalDateTime;
import java.util.Set;

import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorColumn(name = "PARENT")
@Table(name = "Parents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public final class Parent extends Utilisateur {

	public Parent(UserName userName, Gender gender, MaritalStatus maritalStatus, LocalDateTime createdDate,
			LocalDateTime lastModifiedDate, PhoneNumber phoneNumber, Email email, Address address, Set<UserRole> roles,
			String password) {
		super(userName, gender, maritalStatus, createdDate, lastModifiedDate, phoneNumber, email, address, roles,
				password);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String socioOccupationalCategory;

}

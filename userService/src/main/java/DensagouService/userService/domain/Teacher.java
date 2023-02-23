package DensagouService.userService.domain;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorColumn(name = "TEACHER")
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "teacher")
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false, exclude = { "subject" })
public class Teacher extends Utilisateur {

	public Teacher(UserName userName, Gender gender, MaritalStatus maritalStatus, LocalDateTime createdDate,
			LocalDateTime lastModifiedDate, PhoneNumber phoneNumber, Email email, Address address, Set<UserRole> roles,
			String password) {
		super(userName, gender, maritalStatus, createdDate, lastModifiedDate, phoneNumber, email, address, roles,
				password);

	}

	public Teacher(Long teacherId, UserName userName) {
		super(teacherId, userName);
	}

	public Teacher(UserName userName, Gender gender, MaritalStatus maritalStatus, LocalDateTime createdDate,
			LocalDateTime lastModifiedDate, PhoneNumber phoneNumber, Email email, Address address, Set<UserRole> roles,
			String password, String matricule) {
		super(userName, gender, maritalStatus, createdDate, lastModifiedDate, phoneNumber, email, address, roles,
				password);
		this.matricule = matricule;

	}

	private static final long serialVersionUID = 1L;
	@Column
	private String matricule;

	@Column
	private int maxPerDay;

	@Column
	private int maxPerWeek;

}

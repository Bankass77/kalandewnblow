package DensagouService.userService.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@jakarta.persistence.Entity
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorColumn(name = "STUDENT")
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, exclude = { "group", "courses" })
@ToString(exclude = { "group", "courses" })
public final class Student extends Utilisateur {

	private static final long serialVersionUID = 1L;
	@Column(name = "ine_number")
	private String ineNumber;

	@NotNull(message = "Age is required")
	@Column(name = "birthDate")
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonIgnore
	private LocalDate birthDate;

	@NotNull(message = "Age is required")
	@Column(name = "age")
	private int age;

	@Column
	@NotBlank
	@NotNull(message = "Mother First Name is required")
	private String motherFirstName;

	@Column
	@NotBlank
	@NotNull(message = "Mother Last Name is required")
	private String motherLastName;

	@Column
	@NotBlank
	@NotNull(message = "Father Last Name is required")
	private String fatherLastName;

	@Column
	@NotBlank
	@NotNull(message = "Father First Name is required")
	private String fatherFirstName;

	@Column
	@NotNull(message = "Father Mobile is required")
	private PhoneNumber fatherMobile;

	@Column
	@NotNull(message = "Mother mobile  is required")
	private PhoneNumber motherMobile;

	/**
	 * @param userName
	 * @param gender
	 * @param maritalStatus
	 * @param createdDate
	 * @param lastModifiedDate
	 * @param phoneNumber
	 * @param email
	 * @param address
	 * @param roles
	 * @param password
	 * @param ineNumber
	 * @param birthDate
	 * @param age
	 * @param motherFirstName
	 * @param motherLastName
	 * @param fatherLastName
	 * @param fatherFirstName
	 * @param fatherMobile
	 * @param motherMobile
	 */
	public Student(UserName userName, Gender gender, MaritalStatus maritalStatus, LocalDateTime createdDate,
			LocalDateTime lastModifiedDate, PhoneNumber phoneNumber, Email email, Address address, Set<UserRole> roles,
			String password, String ineNumber, LocalDate birthDate, int age, String motherFirstName,
			String motherLastName, String fatherLastName, String fatherFirstName, PhoneNumber fatherMobile,
			PhoneNumber motherMobile) {
		super(userName, gender, maritalStatus, createdDate, lastModifiedDate, phoneNumber, email, address, roles,
				password);
		this.ineNumber = ineNumber;
		this.birthDate = birthDate;
		this.age = age;
		this.motherFirstName = motherFirstName;
		this.motherLastName = motherLastName;
		this.fatherLastName = fatherLastName;
		this.fatherFirstName = fatherFirstName;
		this.fatherMobile = fatherMobile;
		this.motherMobile = motherMobile;

	}

	/**
	 * @param userName
	 * @param gender
	 * @param maritalStatus
	 * @param createdDate
	 * @param lastModifiedDate
	 * @param phoneNumber
	 * @param email
	 * @param address
	 * @param roles
	 * @param password
	 * @param ineNumber
	 * @param birthDate
	 * @param age
	 * @param motherFirstName
	 * @param motherLastName
	 * @param fatherLastName
	 * @param fatherFirstName
	 * @param fatherMobile
	 * @param motherMobile
	 * @return new Student
	 */
	public static Student createStudent(UserName userName, Gender gender, MaritalStatus maritalStatus,
			LocalDateTime createdDate, LocalDateTime lastModifiedDate, PhoneNumber phoneNumber, Email email,
			Address address, Set<UserRole> roles, String password, String ineNumber, LocalDate birthDate, int age,
			String motherFirstName, String motherLastName, String fatherLastName, String fatherFirstName,
			PhoneNumber fatherMobile, PhoneNumber motherMobile) {

		return new Student(userName, gender, maritalStatus, createdDate, lastModifiedDate, phoneNumber, email, address,
				Set.of(UserRole.STUDENT), password, ineNumber, birthDate, age, motherFirstName, motherLastName,
				fatherLastName, fatherFirstName, fatherMobile, motherMobile);
	}

}

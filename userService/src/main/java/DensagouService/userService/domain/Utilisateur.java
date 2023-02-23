package DensagouService.userService.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nullable;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
// @Builder
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "id"), indexes = @Index(name = "idx_user_email", columnList = "email", unique = true))
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "USER_TYPE")
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public abstract class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version()
	private Long version;

	@NotNull(message = "Username is required")
	@Embedded
	private UserName userName;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	@NotNull(message = "Gender is required")
	private Gender gender;

	@NotNull(message = "Marital Status is required")
	@Enumerated(EnumType.STRING)
	@Column(name = "marital_Status")
	private MaritalStatus maritalStatus;

	@CreatedDate
	@Column(name = "created_date", nullable = false)
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime createdDate = LocalDateTime.now();

	@LastModifiedDate
	@Column(name = "last_modified_date")
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime lastModifiedDate = LocalDateTime.now();

	@Column(insertable = true, updatable = true, nullable = false, name = "phoneNumber")
	@Nullable
	private PhoneNumber phoneNumber;

	@Column
	@Nullable
	private byte[] avatar;

	@NotNull(message = "Please enter a valid address email.")
	@Column(unique = true, nullable = false, updatable = true, name = "email")
	private Email email;

	@NotNull(message = "Address is required")
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "street")),

			@AttributeOverride(name = "streetNumber", column = @Column(name = "streetNumber")),

			@AttributeOverride(name = "codePostale", column = @Column(name = "codePostale")),

			@AttributeOverride(name = "city", column = @Column(name = "city")),
			@AttributeOverride(name = "country", column = @Column(name = "country")) })
	private Address address;

	@ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "student_roles")
	@Column(name = "role")
	private Set<UserRole> roles;

	@NotNull
	@Column(name = "password")
	@NotNull(message = "Password is required")
	private String password;

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
	 */
	public Utilisateur(UserName userName, Gender gender, MaritalStatus maritalStatus, LocalDateTime createdDate,
			LocalDateTime lastModifiedDate, PhoneNumber phoneNumber, Email email, Address address, Set<UserRole> roles,
			String password) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.roles = roles;
		this.password = password;
	}

	@PrePersist
	void onCreate() {
		this.setCreatedDate(getCreatedDate());
		this.setLastModifiedDate(getLastModifiedDate());
	}

	@PreUpdate
	void onUpdate() {
		this.setLastModifiedDate(getLastModifiedDate());
	}

	private String getfullName() {
		return userName.getFirstName() != null ? userName.getFirstName().concat(" ").concat(userName.getLastName())
				: " ";

	}

	public Utilisateur(Long teacherId, UserName userName) {
		this.id = teacherId;
		this.userName = userName;
	}

}

package DensagouService.userService.response;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nullable;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;


import DensagouService.userService.constraint.NotExistingUser;
import DensagouService.userService.domain.Address;
import DensagouService.userService.domain.PhoneNumber;
import DensagouService.userService.enums.Gender;
import DensagouService.userService.enums.MaritalStatus;
import DensagouService.userService.enums.UserRole;
import DensagouService.userService.validation.ValidationGroupOne;
import DensagouService.userService.validation.ValidationGroupTwo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@NotExistingUser(groups = ValidationGroupTwo.class)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public sealed class AbstractUserFormData permits CreateUserFormData, EditUserFormData {

	@NotBlank
	@Size(min = 1, max = 200, groups = ValidationGroupOne.class)
	private String firstName;

	@NotBlank
	@Size(min = 1, max = 200, groups = ValidationGroupOne.class)
	private String lastName;

	@NotNull
	private PhoneNumber phoneNumber;

	@NotNull
	private UserRole userRole;

	@NotNull(groups = ValidationGroupOne.class)
	private Gender gender;

	@NotNull(groups = ValidationGroupOne.class)
	private MaritalStatus maritalStatus;

	@NotBlank
	@Email(groups = ValidationGroupOne.class)
	private String email;

	@NotNull(groups = ValidationGroupOne.class)
	private Address address;

	@NotNull(message = "Create Date  is required!", groups = ValidationGroupOne.class)
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonDeserialize(using = InstantDeserializer.class)
	private LocalDateTime createdDate = LocalDateTime.now();

	@LastModifiedDate
	@NotNull(message = " Modify Date is required!", groups = ValidationGroupOne.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@JsonDeserialize(using = InstantDeserializer.class)
	private LocalDateTime modifyDate = LocalDateTime.now();

	@Nullable
	private MultipartFile avatarFile;

	private Set<UserRole> roles;

}

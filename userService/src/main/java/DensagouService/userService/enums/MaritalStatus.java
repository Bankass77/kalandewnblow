package DensagouService.userService.enums;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MaritalStatus implements Serializable {
	MARRIED("Married"), SINGLE("Single"), DIVORCED("Divorce");

	private String value;

}

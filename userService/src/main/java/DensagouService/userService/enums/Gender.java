package DensagouService.userService.enums;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Gender implements Serializable {

	MALE('M'), FEMALE('F');

	private char c;
}

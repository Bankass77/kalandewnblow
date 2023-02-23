package DensagouService.userService.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum UserRole implements Serializable {

	STUDENT, TEACHER, MANAGER, ADMIN, USER;

	public static Set<UserRole> valueAsSet() {
		return new HashSet<>(Arrays.asList(UserRole.values()));

	}
}

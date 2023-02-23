package DensagouService.userService.util;

import java.time.LocalDate;
import java.time.Period;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculateUserAge {

	public static int calculateAge(LocalDate birthDate) {

		LocalDate curentDate = LocalDate.now();

		if ((birthDate != null && curentDate != null) && birthDate.isBefore(curentDate)) {
			int age = Period.between(birthDate, curentDate).getYears();
			log.debug("Generate calculate user Age: {}", age);
			return age;
		}
		return 0;
	}

}

package DensagouService.userService.request;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateTimeSlotParameters {

	private String timeslotName;
	private LocalTime startHour = LocalTime.now().withSecond(0).withNano(0);
	private LocalTime endHour = LocalTime.now().withSecond(0).withNano(0);
	private DayOfWeek dayOfWeek;
	private Integer duration;

}

package DensagouService.userService.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSubjectParameters {

	private String name;

	private boolean needsLab;

}

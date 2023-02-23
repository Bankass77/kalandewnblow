package DensagouService.userService.util;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;

import DensagouService.userService.domain.PhoneNumber;

public class PhoneNumberPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNotBlank(text)) {
			this.setValue(new PhoneNumber(text));
		} else {
			this.setValue(null);
		}
	}

	@Override
	public String getAsText() {

		PhoneNumber value = (PhoneNumber) getValue();
		return value != null ? value.asString() : "";

	}

}

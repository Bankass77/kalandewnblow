package DensagouService.userService.util;

import java.text.ParseException;
import java.util.Locale;

import javax.annotation.Nonnull;
import org.springframework.format.Formatter;

import DensagouService.userService.domain.PhoneNumber;

public class PhoneNumberFormatter implements Formatter<PhoneNumber> {

	@Override
	@Nonnull
	public String print(@Nonnull PhoneNumber object, @Nonnull Locale locale) {

		return object.asString();
	}

	@Nonnull
	@Override
	public PhoneNumber parse(@Nonnull String text, @Nonnull Locale locale) throws ParseException {

		return new PhoneNumber(text);
	}

}

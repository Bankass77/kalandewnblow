package DensagouService.userService.exception;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import DensagouService.userService.config.KaladewnPropertiesConfig;

@Component
@ResponseStatus(HttpStatus.NOT_FOUND)
public class KaladewnManagementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private static KaladewnPropertiesConfig propertiesConfig;

	/**
	 * Returns new RuntimeException based on template and args
	 *
	 * @param messageTemplate
	 * @param args
	 * @return
	 */
	public static RuntimeException throwException(String messageTemplate, String... args) {
		return new RuntimeException(format(messageTemplate, args));
	}

	/**
	 * Returns new RuntimeException based on EntityType, ExceptionType and args
	 *
	 * @param entityType
	 * @param exceptionType
	 * @param args
	 * @return
	 */
	public RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
		String messageTemplate = getMessageTemplate(entityType, exceptionType);
		return throwException(exceptionType, messageTemplate, args);
	}

	/**
	 * Returns new RuntimeException based on EntityType, ExceptionType and args
	 *
	 * @param entityType
	 * @param exceptionType
	 * @param args
	 * @return
	 */
	public static RuntimeException throwExceptionWithId(EntityType entityType, ExceptionType exceptionType, String id,
			String... args) {
		String messageTemplate = getMessageTemplate(entityType, exceptionType).concat(".").concat(id);
		return throwException(exceptionType, messageTemplate, args);
	}

	/**
	 * Returns new RuntimeException based on EntityType, ExceptionType,
	 * messageTemplate and args
	 *
	 * @param entityType
	 * @param exceptionType
	 * @param messageTemplate
	 * @param args
	 * @return
	 */
	public static RuntimeException throwExceptionWithTemplate(EntityType entityType, ExceptionType exceptionType,
			String messageTemplate, String... args) {
		return throwException(exceptionType, messageTemplate, args);
	}

	/**
	 * Returns new RuntimeException based on template and args
	 *
	 * @param messageTemplate
	 * @param args
	 * @return
	 */
	private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate,
			String... args) {
		if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
			return new EntityNotFoundException(format(messageTemplate, args));
		} else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
			return new DuplicateEntityException(format(messageTemplate, args));
		}
		return new RuntimeException(format(messageTemplate, args));
	}

	private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
		return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
	}

	private static String format(String template, String... args) {
		Optional<Object> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
		if (templateContent.isPresent()) {
			return MessageFormat.format((String) templateContent.get(), (Object[]) args);
		}
		return String.format(template, (Object[]) args);
	}

	// -------------------------------------------------------------------------
	// No String/variable interpolation in Java. Use format instead.
	// -------------------------------------------------------------------------
	public static class EntityNotFoundException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EntityNotFoundException(String message) {
			super(message);
		}

		public EntityNotFoundException(String template, Object arg1, Object arg2, Throwable cause) {
			super(MessageFormat.format(template, arg1, arg2), cause);
		}
	}

	public static class DuplicateEntityException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DuplicateEntityException(String message) {
			super(message);
		}
	}

}

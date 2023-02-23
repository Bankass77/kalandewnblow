package DensagouService.userService.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class KaladewnConfig {

	@Bean
	@Qualifier("svgTemplateEngine")
	public ITemplateResolver svgTemplateResolver() {
		var resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("classpath:/templates/svg/");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setSuffix(".svg");
		resolver.setTemplateMode("XML");
		resolver.setCacheable(false);
		resolver.setCheckExistence(true);

		return resolver;
	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource) {

		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);

		return bean;
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(64000);
		loggingFilter.setIncludeHeaders(false);
		loggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
		return loggingFilter;
	}

}

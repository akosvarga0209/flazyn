package com.flazyn;

import com.flazyn.entities.Role;
import com.flazyn.entities.User;
import com.flazyn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.request.RequestContextListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FlazynApplication extends SpringBootServletInitializer implements ApplicationRunner {

	//Only for test
	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(FlazynApplication.class, args);
	}

	//Only for test
	@Override
	public void run(ApplicationArguments args) throws Exception {
	}

	@Bean
	public static MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/messages");
		return messageSource;
	}

	@Bean
	public ITemplateResolver templateResolver()
	{
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		return templateResolver;
	}

	@Bean
	public TemplateEngine templateEngine()
	{
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());

		return templateEngine;
	}

	@Bean
	public RequestContextListener requestContextListener(){
		RequestContextListener requestContextListener = new RequestContextListener();
		return requestContextListener;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FlazynApplication.class);
	}


}

package br.com.amorim.supermarket.configuration.internacionalizationmessages;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizationConfig {

    private static final String CLASS_PATH = "classpath:messages";
    private static final String ISO_8859_1 = "ISO-8859-1";

    @Bean
    public MessageSource messageSource () {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(CLASS_PATH);
        messageSource.setDefaultEncoding(ISO_8859_1);
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean () {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}

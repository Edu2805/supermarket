package br.com.amorim.supermarket.configuration.internacionalizationmessages;

import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
public class ResourcesBundleMessages {

    private static final ResourceBundle bundle = ResourceBundle
            .getBundle("messages", Locale.getDefault());
    private static final String DOMAIN_MESSAGE = "br.com.supermarket.";

    public static String getString(String chave) {
        String value = null;
        try {
            value = bundle.getString(DOMAIN_MESSAGE + chave);
        }
        catch (Exception e) {
            e.getMessage();
        }
        return value;
    }

    public static String getString(String chave, String... parametros) {
        String value = null;
        try {
            value = java.text.MessageFormat.format(bundle.getString(DOMAIN_MESSAGE + chave), (Object[])parametros);
        }
        catch (Exception e) {
            e.getMessage();
        }
        return value;
    }
}

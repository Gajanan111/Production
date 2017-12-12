package com.billdiary.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import javafx.util.Callback;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;


@Component
public class SpringFxmlLoader {

	private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private static Locale loc;
	private static MessageSourceResourceBundle bundle;
	private static SpringFxmlLoader springFxmlLoader=null;
	
	
	
	private SpringFxmlLoader()
	{
		
	}
	// static method to create instance of SpringFxmlLoader class
    public static SpringFxmlLoader getInstance()
    {
        if (springFxmlLoader == null)
        	springFxmlLoader = new SpringFxmlLoader();
 
        return springFxmlLoader;
    }
	public static MessageSourceResourceBundle getBundle() {
		if(bundle==null)
		{
			bundle =new MessageSourceResourceBundle(applicationContext.getBean(MessageSource.class),loc);
		}
		return bundle;
	}

	public static void setBundle(MessageSourceResourceBundle bundle) {
		SpringFxmlLoader.bundle =new MessageSourceResourceBundle(applicationContext.getBean(MessageSource.class),loc);
	}

	public static Locale getLoc() {
		return loc;
	}

	public static void setLoc(Locale loc) {
		SpringFxmlLoader.loc = loc;
	}
	public Object load(String url) {

		try (InputStream fxmlStream = SpringFxmlLoader.class.getResourceAsStream(url)) {
			System.err.println(SpringFxmlLoader.class.getResourceAsStream(url));
			FXMLLoader loader = new FXMLLoader();
			URL location = getClass().getResource(url);
			loader.setLocation(location);
			//loader.setResources(bundle);
			//loader.setResources(resources);
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				@Override
				public Object call(Class<?> clazz) {
					return applicationContext.getBean(clazz);
				}
			});
			/**
			 * Here we set the Locale (For language change)
			 */
			
			if(loc==null)
			{
				loc = new Locale("en");
			}
			System.out.println(loc.getDisplayLanguage());
			setLoc(loc);
			MessageSourceResourceBundle bundle=getBundle();
			setBundle(bundle);
			loader.setResources(getBundle());
			return loader.load(fxmlStream);
		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		}
	}
	
	public static String getMessage(String key) {
		 
        try {
            MessageSource bean = applicationContext.getBean(MessageSource.class);
            Locale locale2 = new Locale("en", "US");
            return bean.getMessage(key, null, locale2);
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
 
    }
	public static ApplicationContext getApplicationcontext() {
		return applicationContext;
	}

}

package br.com.umake.model;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class LogManager {

	public LogManager(){
		
	}
	
	
	public void error(Object message, Class<?> clazz ){
	
		Logger logger = Logger.getLogger(clazz);
		BasicConfigurator.configure();
		logger.error(message);
		
	}
	
}

package br.com.umake.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ConfigManager {

	private List<Config> configs;
	
	
	public ConfigManager(){
		
	}	
	
	public List<Config> getConfigs() {
		return configs;
	}


	public void setConfigs(List<Config> configs) {
		this.configs = configs;
	}

	public Config searchConfigBy(String slug){
		
		List<Config> config = this.searchConfigsBy(slug);
		
		return config.size() > 0 ? config.get(0) : null;
				
	}
	
	public List<Config> searchConfigsBy(String...slugs){
		
		List<Config> configList = new ArrayList<Config>();
		List<Config> configs = this.getConfigs();
		
		for (String slug : slugs) {

			for (Config config : configs) {

				if( config.getSlug().equals(slug) )
					
					configList.add(config);
				
				
			}
			
		}

		return configList;
				
	}
	
	public Config searchConfigBy(int id){
		
		for (Config config : this.getConfigs()) {
			
			if( config.getId().equals(id) )
				
				return config;
			
			
		}
		
		return null;
		
	}
	
	
}

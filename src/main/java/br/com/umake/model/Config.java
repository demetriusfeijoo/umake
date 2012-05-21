package br.com.umake.model;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Config {
	
	private Long id;
	private String name;
	private String slug;
	private String value;
	
	public Config(){
		
	}
	
	@Override
	public String toString(){
		
		return this.getValue();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}

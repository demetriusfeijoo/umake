package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class Config {
	
	private Long id;
	private String title;
	
	public Config(){
		
	}
	
/*	@Override
	public String toString(){ 

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format(this.getDateOfRegistration());

		return String.format("Page %s, cadastrada no dia %s", "", dateRegForm);
		
	}*/
	
	@Override
	public boolean equals( Object o ){
		
		Config page2 = (Config) o;
		return (this.getId() == page2.getId() ? true : false);
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}

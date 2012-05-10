package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.Column;
import br.com.umake.helper.Id;

@Component
public class Page {
	
	@Column(position=1)
	@Id
	private Long id;
	private Date dateOfRegistration;
	
	public Page(){
		
	}
	
	@Override
	public String toString(){ 

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format(this.getDateOfRegistration());

		return String.format("Page %s, cadastrada no dia %s", "", dateRegForm);
		
	}
	
	@Override
	public boolean equals( Object o ){
		
		Page page2 = (Page) o;
		return (this.getId() == page2.getId() ? true : false);
		
	}

	public Long getId(){
		
		return this.id;
		
	}
	
	public Date getDateOfRegistration(){
		
		return this.dateOfRegistration;
		
	}
	
	public void setId(Long id){
		
		this.id = id;
		
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		
		this.dateOfRegistration = dateOfRegistration;
		
	}
}

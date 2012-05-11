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
	@Column(position=3)
	private Date dateOfRegistration;
	private Boolean status;
	private String title;
	@Column(position=2)
	private String author;
	private String content;
	
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
	
	public Boolean getStatus(){
		
		return this.status;
		
	}
	
	public String getTitle(){
		
		return this.title;
		
	}
	
	public String getAuthor(){
		
		return this.author;
		
	}
	
	public String getContent(){
		
		return this.content;
		
	}
	
	public void setId(Long id){
		
		this.id = id;
		
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		
		this.dateOfRegistration = dateOfRegistration;
		
	}
	
	public void setStatus( Boolean status ){
		
		this.status = status;
		
	}
	
	public void setTitle( String title ){
		
		this.title = title;
		
	}
	
	public void setAuthor( String author ){
		
		this.author = author;
		
	}
	
	public void setContent( String content ){
		
		this.content = content;
		
	}
}

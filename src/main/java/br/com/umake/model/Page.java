package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class Page {
	
	@Column(position=1)
	@Id
	private Long id;
	@Column(position=4)
	private Date dateOfRegistration;
	private Boolean status;
	@Column(position=2)
	private String title;
	private String slug;
	@Column(position=3)
	private String author;
	private String content;
	
	public Page(){
		
	}
	
	@Override
	public String toString(){ 

		return this.content;
		
	}
	
	@Override
	public boolean equals( Object o ){
		
		return (this.getSlug().equals( o.toString()) );
		
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
	
	public String getSlug(){
		
	
		return this.slug;
		
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
	
	public void setSlug(String slug){
		
		this.slug = slug;
		
	}
	
	public void setAuthor( String author ){
		
		this.author = author;
		
	}
	
	public void setContent( String content ){
		
		this.content = content;
		
	}
}

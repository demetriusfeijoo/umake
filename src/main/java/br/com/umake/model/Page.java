package br.com.umake.model;

import java.util.Date;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class Page implements Menuable {
	
	@Column(position=1)
	@Id
	private Long id;
	@Column(position=4)
	private Date dateOfRegistration;
	private Boolean status;
	@Column(position=2)
	private String title;
	private String slug;
	private AdmUser admUser;
	private String content;
	private int ordered;
	private boolean isIndex;
	
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
	
	
	public AdmUser getAdmUser(){
		
		return this.admUser;
		
	}
	
	public String getContent(){
		
		return this.content;
		
	}
	
	public int getOrdered(){
		
		return this.ordered;
		
	}
	
	public boolean getIsIndex() {
		return isIndex;
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
	
	public void setAdmUser( AdmUser admUser ){
		
		this.admUser = admUser;
		
	}
	
	public void setContent( String content ){
		
		this.content = content;
		
	}
	
	public void setOrdered( int ordered ){
		
		this.ordered = ordered;
		
	}

	public void setIsIndex(boolean isIndex) {
		this.isIndex = isIndex;
	}

	public int getItemPosition(){
		
		return this.getOrdered();
		
	}
	
	public String getItemUrl(){
		
		String slug = this.getSlug(); 
		
		if( !slug.startsWith("/", 0)){
			
			slug = "/"+slug;
			
		}
		
		return slug;
		
	}
	
	public String getItemValue(){
		
		return this.getTitle();
		
	}
}

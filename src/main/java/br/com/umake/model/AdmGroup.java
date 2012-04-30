package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class AdmGroup{

	private Long id;
	private String name; 
	private Date dateOfRegistration;
	private AdmGroup parentAdmGroup;
	private Set<AdmUser> admUsers;
	private Set<AdmPermission> admPermissions;				
	
	public AdmGroup(){ 
		
	}
	
	@Override
	public String toString(){ 

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format(this.getDateOfRegistration());

		return String.format("AdmGroup %s, cadastrado no dia %s", this.getName(), dateRegForm);
		
	}
	
	@Override
	public boolean equals( Object o ){
		
		AdmGroup admGroup2 = (AdmGroup) o;
		return (this.getId() == admGroup2.getId() ? true : false);
		
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public AdmGroup getParentAdmGroup() {
		return parentAdmGroup;
	}
	
	public Set<AdmUser> getAdmUsers() {
		return admUsers;
	}

	public Set<AdmPermission> getAdmPermissions() {
		return admPermissions;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public void setParentAdmGroup(AdmGroup parentAdmGroup) {
		this.parentAdmGroup = parentAdmGroup;
	}
	
	public void setAdmUsers(Set<AdmUser> admUsers) {
		this.admUsers = admUsers;
	}

	public void setAdmPermissions(Set<AdmPermission> admPermissions) {
		this.admPermissions = admPermissions;
	}

	public Boolean isParent(){
		return this.equals(getParentAdmGroup());
	}

}

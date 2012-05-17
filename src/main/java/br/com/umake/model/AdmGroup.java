package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class AdmGroup{

	@Column(position=1)
	@Id
	private Long id;
	@Column(position=2)
	private String name; 
	private Date dateOfRegistration;
	private AdmGroup parentAdmGroup;
	private Set<AdmPermission> admPermissions = new HashSet<AdmPermission>();				
	
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

	public void setAdmPermissions(Set<AdmPermission> admPermissions) {
		this.admPermissions = admPermissions;
	}

	public Boolean isParent(){
		return this.equals(getParentAdmGroup());
	}

}

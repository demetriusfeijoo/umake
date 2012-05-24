package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class AdmUser{

	@Column(position=1)
	@Id 
	private Long id;
	@Column(position=2)
	private String name;
	private String login;
	private String password;
	@Column(position=3)
	private String email;
	@Column(position=4)
	private Date dateOfRegistration;
	private Date dateLastVisit;
	private Boolean receiveEmail;
	private Boolean userBlock;
	private Set<AdmGroup> admGroups = new HashSet<AdmGroup>();
	private Set<AdmPermission> admPermissions = new HashSet<AdmPermission>();
	
	public AdmUser() {
		
	}

	@Override
	public String toString() {

		
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); String
		 dateRegForm = df.format( ( this.getDateOfRegistration() != null ?
		 this.getDateOfRegistration() : new Date() ) );
 
		 return  String.format("AdmUser %s, cadastrado no dia %s", this.getName(),
		 dateRegForm);


	}

	@Override
	public boolean equals(Object o) {

		AdmUser admUser2 = (AdmUser) o;

		return (!admUser2.getId().equals(null) && this.getId() == admUser2.getId() ? true
				: false);

	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public Date getDateLastVisit() {
		return dateLastVisit;
	}

	public Boolean getReceiveEmail() {
		return receiveEmail;
	}

	public Boolean getUserBlock() {
		return userBlock;
	}

	public Set<AdmGroup> getAdmGroups() {
		return admGroups;
	}

	public Set<AdmPermission> getAdmPermissions() {

		return this.admPermissions;

	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public void setDateLastVisit(Date dateLastVisit) {
		this.dateLastVisit = dateLastVisit;
	}

	public void setReceiveEmail(Boolean receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public void setUserBlock(Boolean userBlock) {
		this.userBlock = userBlock;
	}

	public void setAdmGroups(Set<AdmGroup> admGroups) {
		this.admGroups = admGroups;
	}

	public void setAdmPermissions(Set<AdmPermission> admPermissions) {
		this.admPermissions = admPermissions;
	}

	public Boolean hasAllNecessariesPermissions(List<AdmPermission> recoveryNecessaryAdmPermissions) { 
		
		for (AdmPermission necessaryPermission : recoveryNecessaryAdmPermissions) {

			Boolean exists = false;

			for (AdmPermission perm : this.getAllAdmPermissions()) {
								
				if (necessaryPermission.equals(perm)){
					
					exists = true;
					
				}
				
			}

			if (!exists) {
				
				return false;
			}
		}

		return true;

	}

	private Set<AdmPermission> getAllAdmPermissions() {
		
		Set<AdmPermission> allAdmPermissions = new HashSet<AdmPermission>( this.getAdmPermissions().size() );
		allAdmPermissions.addAll(this.getAdmPermissions());
		
		for (AdmGroup admGroup : this.getAdmGroups()) {

			allAdmPermissions.addAll(admGroup.getAdmPermissions());
			
			if(admGroup.getParentAdmGroup() != null ){ 
			
				allAdmPermissions.addAll(admGroup.getParentAdmGroup().getAdmPermissions());
				
			}
			
		}

		return allAdmPermissions;
	}	

}

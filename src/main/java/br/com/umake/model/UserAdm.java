package br.com.umake.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UserAdm implements Serializable, Gridable<UserAdm> {

	private Long id;
	private String name;
	private String login;
	private String password;
	
	//@Email
	private String email;
	private Date dateOfRegistration;
	private Date dateLastVisit;
	private Boolean receiveEmail;
	private Boolean userBlock;
	private Set<Group> groups = new HashSet<Group>();
	private Set<Permission> permissions = new HashSet<Permission>();
	private static final long serialVersionUID = 1L;

	public UserAdm() {
		
	}

	@Override
	public String toString() {

		
		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); String
		 dateRegForm = df.format( ( this.getDateOfRegistration() != null ?
		 this.getDateOfRegistration() : new Date() ) );
 
		 return  String.format("Usuario %s, cadastrado no dia %s", this.getName(),
		 dateRegForm);


	}

	@Override
	public boolean equals(Object o) {

		UserAdm user2 = (UserAdm) o;

		return (!user2.getId().equals(null) && this.getId() == user2.getId() ? true
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

	public Set<Group> getGroups() {
		return groups;
	}

	public Set<Permission> getPermissions() {

		return this.permissions;

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

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Boolean hasAllNecessariesPermissions(List<Permission> recoveryNecessaryPermissions) { 

		for (Permission permissaoNecessaria : recoveryNecessaryPermissions) {

			Boolean exists = false;

			for (Permission perm : this.getAllPermissions()) {
								
				if (permissaoNecessaria.equals(perm)){
					
					exists = true;
					
				}
					

			}

			if (!exists) {
				return false;
			}
		}

		return true;

	}

	private Set<Permission> getAllPermissions() {
		
		Set<Permission> allPermissions = new HashSet<Permission>( this.getPermissions().size() );
		allPermissions.addAll(this.getPermissions());
		
		for (Group group : this.getGroups()) {

			Set<Permission> groupPermissions = group.getPermissions();

			for (Permission permissions : groupPermissions) {

				allPermissions.add(permissions);

			}

		}

		return allPermissions;
	}

	public String getIdentifier() {
		
		return String.valueOf(this.getId());
		
	}

	public UserAdm getInstance() {
		
		return this;
		
	}
	

}

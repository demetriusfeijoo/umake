package br.com.umake.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class User implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String login;
	private String password;
	private String email;
	private Date dateOfRegistration; 
	private Date dateLastVisit; 
	private Boolean receiveEmail; 
	private Boolean userBlock;
	private Set<Group> groups;
	private Set<Permission> permissions;
	private static final long serialVersionUID = 1L;
	
	public User() { 

	}

	@Override
	public String toString() {

/*		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format( ( this.getDateOfRegistration() != null ? this.getDateOfRegistration() : new Date() )  );

		String.format("Usuario %s, cadastrado no dia %s", this.getName(), dateRegForm);*/
		return ""; 

	}

	@Override
	public boolean equals(Object o) {
		
		User user2 = (User) o;
		
		return (!user2.getId().equals(null) && this.getId() == user2.getId() ? true : false);

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
				
		for (Group group : this.getGroups() ) {
			
			Set<Permission> groupPermissions = group.getPermissions();
			
			for (Permission permissions : groupPermissions) {
			
				this.permissions.add(permissions);
				
			}
			
		}
		
		return  permissions;
		
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

	public Boolean hasPermissions(List<Permission> recoveryNecessaryPermissions) {
		return true;		
	}

}

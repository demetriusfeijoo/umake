package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;

import br.com.caelum.vraptor.ioc.Component;



@Entity
@Component
public class Permission {
	
	//public enum PermissionName{ CREATE, VIEW, EDIT, DELETE }

	private Long id;
	private String context;
	private String name; 
	private Date dateOfRegistration;
	private Set<User> users ;
	private Set<Group> groups ;

	public Permission() {
		
	}

	@Override
	public String toString() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format(this.getDateOfRegistration());

		return String.format("Permissão %s, cadastrada no dia %s",
				this.getName(), dateRegForm);

	}

	@Override
	public boolean equals(Object o) {

		Permission permission2 = (Permission) o;

		return (!permission2.getId().equals(null)
				&& this.getId() == permission2.getId() ? true : false);

	}

	public Long getId() {
		return id;
	}

	public String getContext() {
		return context;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public String getName(){
		return this.name;
	}

}

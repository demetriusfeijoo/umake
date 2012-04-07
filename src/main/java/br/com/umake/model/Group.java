package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class Group{

	private Long id;
	private String name; 
	private Date dateOfRegistration;
	private Group parentGroup;
	private Set<UserAdm> users;
	private Set<Permission> permissions;				
	
	public Group(){ 
		
	}
	
	@Override
	public String toString(){ 

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format(this.getDateOfRegistration());

		return String.format("Grupo %s, cadastrado no dia %s", this.getName(), dateRegForm);
		
	}
	
	@Override
	public boolean equals( Object o ){
		
		Group group2 = (Group) o;
		return (this.getId() == group2.getId() ? true : false);
		
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

	public Group getParentGroup() {
		return parentGroup;
	}
	
	public Set<UserAdm> getUsers() {
		return users;
	}

	public Set<Permission> getPermissions() {
		return permissions;
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

	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}
	
	public void setUsers(Set<UserAdm> users) {
		this.users = users;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public Boolean isParent(){
		return this.equals(this.getParentGroup());
	}
}

package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Group {

	private Long id;
	private String name;
	private Date dateOfRegistration;
	private Group parentGroup;
	private Set<Permission> permissions;
	
	public Group(){
		
	}
	
	public String toString(){

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format(this.getDateOfRegistration());

		return String.format("Grupo %s, cadastrado no dia %s",
				this.getName(), dateRegForm);
		
	}
	
	public boolean equals( Object o ){
		
		Group group2 = (Group) o;
		
		return (!group2.getId().equals(null) && this.getId() == group2.getId() ? true : false);
		
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

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}


}
package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Permission {

	private Long id;
	private String name;
	private Date dateOfRegistration;

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
		
		return (!permission2.getId().equals(null) && this.getId() == permission2.getId() ? true
				: false);

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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

}

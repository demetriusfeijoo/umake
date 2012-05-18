package br.com.umake.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class AdmPermission {
	
	@Column(position=1)
	@Id
	private Long id;
	@Column(position=2)
	private String context;
	@Column(position=3)
	private String type; 
	private Date dateOfRegistration;

	public AdmPermission() {
		
	}

	@Override
	public String toString() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateRegForm = df.format(this.getDateOfRegistration());

		return String.format("AdmPermission %s, cadastrada no dia %s",
				this.getType(), dateRegForm);

	}

	@Override
	public boolean equals(Object o) {

		AdmPermission admPermission2 = (AdmPermission) o;
		
		return (this.getContext().equals(admPermission2.getContext() ) && this.getType().equals(admPermission2.getType()) ? true : false);

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

	public void setType(String type) {
		this.type = type.toUpperCase();
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setContext(String context) {
		this.context = context.toUpperCase();
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	
	public String getType(){
		return this.type;
	}

}

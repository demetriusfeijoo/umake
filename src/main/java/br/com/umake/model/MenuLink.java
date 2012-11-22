package br.com.umake.model;

import br.com.caelum.vraptor.ioc.Component;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class MenuLink implements Menuable {
	
	@Column(position=1)
	@Id
	private long id;
	private int position;
	@Column(position=3)
	private String url;
	@Column(position=2)
	private String value;
	
	public MenuLink(){
		
	}
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getPosition() {
		return position;
	}



	public void setPosition(int position) {
		this.position = position;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public int getItemPosition() {
		
		return this.getPosition();
		
	}

	public String getItemUrl() {

		return this.getUrl();
		
	}

	public String getItemValue() {
		
		return this.getValue();
		
	}

}

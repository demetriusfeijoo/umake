package br.com.umake.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.annotations.Entity;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;
import br.com.umake.helper.flexigrid.Column;
import br.com.umake.helper.flexigrid.Id;

@Component
public class Menu {
	
	@Column(position=1)
	@Id
	private Long id;
	@Column(position=2)
	private String name;
	@Column(position=3)
	private int position;
	//List<Menuable> menuElements;
	
	public Menu(){
		
	}
	
	/*@Override
	public String toString(){
		
		return this.getDefaultMenu();
		
	}*/

	/*public List<Menuable> getMenuElements() {
		return menuElements;
	}*/
	
	public void setId(Long id){
		
		this.id = id;
		
	}
	
	public Long getId(){
	
		return this.id;
		
	}
	
	public void setName( String name ){
		
		this.name = name;
		
	}
	
	public String getName(){
		
		return this.name;
		
	}
	
	public void setPosition(int position){
		
		this.position = position;
		
	}
	
	public int getPosition(){
		
		return this.position;
		
	}

	/*public void setMenuElements(List<Menuable> menuElements) {
		this.menuElements = menuElements;
	}
	
	public String getDefaultMenu(){
		
		Collections.sort(this.getMenuElements(), new Comparator<Menuable>() {
			
			public int compare(Menuable o1, Menuable o2) {
				
				return o1.getItemPosition() - o2.getItemPosition();
				
			}
			
			
		});
		
		StringBuilder menuPrint = new StringBuilder("<ul>");
		
		for (Menuable menuElement : this.getMenuElements()) {
			
			menuPrint.append(String.format("<li> <a href=\"%s\"> %s </a> </li>", menuElement.getItemUrl(), menuElement.getItemValue()));
			
		}
		
		menuPrint.append("</ul>");

		return menuPrint.toString();
		
	}*/


}

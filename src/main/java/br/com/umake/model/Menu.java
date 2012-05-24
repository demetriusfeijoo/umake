package br.com.umake.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;

@Component
@PrototypeScoped
public class Menu {

	List<Menuable> menuElements;
	
	public Menu(){
		
	}
	
	@Override
	public String toString(){
		
		return this.getDefaultMenu();
		
	}

	public List<Menuable> getMenuElements() {
		return menuElements;
	}

	public void setMenuElements(List<Menuable> menuElements) {
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
		
	}


}

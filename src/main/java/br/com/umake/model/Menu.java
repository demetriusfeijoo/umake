package br.com.umake.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.caelum.vraptor.ioc.Component;
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
	private boolean status;
	private Set<Menuable> menuLinks = new HashSet<Menuable>();
	private Set<Menuable> menuPages = new HashSet<Menuable>();	

	
	public Menu(){
		
	}
	
	@Override
	public String toString(){
		
		return this.getDefaultMenu();
		
	}

	
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setMenuLinks(Set<Menuable> menuLinks) {
		this.menuLinks = menuLinks;
	}

	public Set<Menuable> getMenuLinks() {
		return menuLinks;
	}
	
	public void setMenuPages(Set<Menuable> menuPages) {
		this.menuPages = menuPages;
	}

	public Set<Menuable> getMenuPages() {
		return menuPages;
	}

	public String getDefaultMenu(){
		
		Set<Menuable> allElementsOfMenu = this.getMenuLinks();
		allElementsOfMenu.addAll(this.getMenuPages());
		
		List<Menuable> orderedList = new ArrayList<Menuable>( allElementsOfMenu.size() );
		
		for (Menuable menuable : allElementsOfMenu) {
		
			orderedList.add(menuable);
			
		}
		
		Collections.sort(orderedList, new Comparator<Menuable>() {
			
			public int compare(Menuable o1, Menuable o2) {
				
				return o1.getItemPosition() - o2.getItemPosition();
				
			}
			
			
		});
		
				
		StringBuilder menuPrint = new StringBuilder("<ul>");
		
		for (Menuable menuElement : orderedList) {
			
			boolean canInsert = true;
			
			if( menuElement instanceof Page ){
				
				Page pageTemp = (Page) menuElement;
				
				if(!pageTemp.getStatus()){
					
					canInsert = false;
					
				}
				
			}
			
			if(canInsert){
				
				menuPrint.append(String.format("<li> <a href=\"%s\"> %s </a> </li>", menuElement.getItemUrl(), menuElement.getItemValue()));
				
			}
						
		}
		
		menuPrint.append("</ul>");

		return menuPrint.toString();
		
	}


}

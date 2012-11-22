package br.com.umake.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.MenuDao;
import br.com.umake.dao.MenuLinkDao;
import br.com.umake.dao.PageDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.interceptor.AdmUserControl;
import br.com.umake.model.Menu;
import br.com.umake.model.MenuLink;
import br.com.umake.model.Menuable;
import br.com.umake.model.Page;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class MenuController {

	private final Result result;
	private final MenuDao menuDao;
	private final AdmUserControl admUserControl;
	private MenuLinkDao menuLinkDao;
	private PageDao pageDao;
	
	public MenuController(Result result, MenuDao menuDao, PageDao pageDao, MenuLinkDao menuLinkDao,AdmUserControl admUserControl){
		
		this.result = result;
		this.menuDao = menuDao;
		this.admUserControl = admUserControl;
		this.menuLinkDao = menuLinkDao;
		this.pageDao = pageDao;
		
	}
	
	@Get("adm/menu/{menu.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW})}) 
	public void getMenu( Menu menu ){
		
		Menu menuRecovered = this.menuDao.get(menu);		
		List<MenuLink> links = this.menuLinkDao.getAll();
		List<Page> pages = this.pageDao.getAll();

		if(menuRecovered != null ){
			
			this.result.include("menu", menuRecovered);
			this.result.include("menuLinks", links);
			this.result.include("menuPages", pages);

			this.result.forwardTo(this).formMenu();
			
		}else{
			
			this.result.redirectTo(this).list();
			
		}
				
	}
	
    @Get("adm/menu/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW})}) 
	public void formMenu(){
    	
		List<MenuLink> links = this.menuLinkDao.getAll();
		List<Page> pages = this.pageDao.getAll();
		
		this.result.include("menuPages", pages);
		this.result.include("menuLinks", links);
		
    }
    
	@Get("adm/menu")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}
    
	@Post("adm/menu")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final Menu menu, List<Page> menuPages, List<MenuLink> menuLinks) {
		
		Set<Menuable> newPages =  new HashSet<Menuable>();
		Set<Menuable> newLinks =  new HashSet<Menuable>();

		for (Page pages : menuPages) {
			newPages.add(pages);
		}
		
		
		for (MenuLink links : menuLinks) {
			newLinks.add(links);
		}
		
		menu.setMenuPages(newPages);
		menu.setMenuLinks(newLinks);
		
		this.result.include("retorno", this.menuDao.insert(menu) );
		this.result.include("tipoSubmit", "cadastrado" );		
		this.result.include("menu", menu);

		this.result.redirectTo(this).formMenu();

	}
	
	@Put("adm/menu")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(final Menu menu, List<Page> menuPages, List<MenuLink> menuLinks){

		Set<Menuable> newPages =  new HashSet<Menuable>();
		Set<Menuable> newLinks =  new HashSet<Menuable>();

		for (Page pages : menuPages) {
			newPages.add(pages);
		}
		
		
		for (MenuLink links : menuLinks) {
			newLinks.add(links);
		}
		
		menu.setMenuPages(newPages);
		menu.setMenuLinks(newLinks);
		
		this.result.include("retorno", this.menuDao.edit(menu) );
		this.result.include("tipoSubmit", "editado" );
		
		this.result.redirectTo(this).getMenu(menu);		
		
	}
	
    @Delete("adm/menu/{menu.id}")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_MENU", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(final Menu menu){
    	
        Menu menuDeleted = this.menuDao.get(menu);
        
        if(this.menuDao.delete(menuDeleted)){
        		
        	this.result.forwardTo(this).list();
        		
        }else{
        		
            this.result.redirectTo(this).getMenu(menu);

        		
        }  	
    	    	
	}
    
	@Path("adm/menu/flexi")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW } ) }) 
	public void getAllMenuInFlexiJson( int page, int rp, String sortname, String sortorder, String query, String qtype ){
		
		FlexiGridJson<Menu> flexi = null;
	
		try {
			
			int offset = page == 1 ? 0 : ((page - 1) * rp) ;
			
			List<Menu> flexiListAdmGroup;
			
			if( query == null || query == "" ){
				
				flexiListAdmGroup = this.menuDao.getAllLimitedAndOrdered( offset , rp, sortname, sortorder );

			}else{
				
				flexiListAdmGroup = this.menuDao.getAllByPropertyName(qtype, query);
				
			}
							
			flexi = new FlexiGridJson<Menu>( page, this.menuDao.getAll().size(), flexiListAdmGroup );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}
	
}

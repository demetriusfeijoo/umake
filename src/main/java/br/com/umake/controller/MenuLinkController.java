package br.com.umake.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.MenuLinkDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.model.MenuLink;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;



@Resource
public class MenuLinkController {

	private final Result result;
	private final MenuLinkDao menuLinkDao;
	
	public MenuLinkController(Result result, MenuLinkDao menuLinkDao){
		
		this.result = result;
		this.menuLinkDao = menuLinkDao;
		
	}
	
	@Get("adm/link/{menuLink.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW})}) 
	public void getMenuLink( MenuLink menuLink ){
		 
		MenuLink menuLinkRecovered = this.menuLinkDao.get(menuLink);		
		
		if(menuLinkRecovered != null ){
			
			this.result.include("menuLink", menuLinkRecovered);
			this.result.forwardTo(this).formMenuLink();
			
		}else{
			
			this.result.redirectTo(this).list();
			
		}
				
	}
	
    @Get("adm/link/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW})}) 
	public void formMenuLink(){
    	
    }
    
	@Get("adm/link")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}
    
	@Post("adm/link")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final MenuLink menuLink) {

		this.result.include("retorno", this.menuLinkDao.insert(menuLink));
		this.result.include("tipoSubmit", "cadastrado");		
		this.result.include("menuLink", menuLink);

		this.result.redirectTo(this).formMenuLink();

	}
	
	@Put("adm/link")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(final MenuLink menuLink){
				
		this.result.include("retorno", this.menuLinkDao.edit(menuLink) );
		this.result.include("tipoSubmit", "editado" );
		
		this.result.redirectTo(this).getMenuLink(menuLink);		
		
	}
	
    @Delete("adm/link/{menuLink.id}")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_MENU", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(final MenuLink menuLink){
    	
        MenuLink menuLinkDeleted = this.menuLinkDao.get(menuLink);
        
        if(this.menuLinkDao.delete(menuLinkDeleted)){
        		
        	this.result.redirectTo(this).list();
        		
        }else{
        		
            this.result.redirectTo(this).getMenuLink(menuLink);

        		
        }  	
    	    	
	}
    
	@Path("adm/link/flexi")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_MENU", permissionsTypes = { PermissionType.VIEW } ) }) 
	public void getAllMenuInFlexiJson( int page, int rp, String sortname, String sortorder, String query, String qtype ){
		
		FlexiGridJson<MenuLink> flexi = null;
	
		try {
			
			int offset = page == 1 ? 0 : ((page - 1) * rp) ;
			
			List<MenuLink> flexiListMenuLink;
			
			if( query == null || query == "" ){
				
				flexiListMenuLink = this.menuLinkDao.getAllLimitedAndOrdered( offset , rp, sortname, sortorder );

			}else{
				
				flexiListMenuLink = this.menuLinkDao.getAllByPropertyName(qtype, query);
				
			}

			flexi = new FlexiGridJson<MenuLink>( page, this.menuLinkDao.getAll().size(), flexiListMenuLink );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}
	
}

package br.com.umake.controller;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.PageDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.helper.utils.TextHelper;
import br.com.umake.interceptor.AdmUserControl;
import br.com.umake.model.Page;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
@RequestScoped
public class PageController {

	private final Result result;
	private final PageDao pageDao;
	private final AdmUserControl admUserControl;
	
	public PageController(Result result, PageDao pageDao, AdmUserControl admUserControl){
		
		this.result = result;
		this.pageDao = pageDao;
		this.admUserControl = admUserControl;
		
	}
	
	@Get("adm/page/{page.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_PAGE", permissionsTypes = { PermissionType.VIEW})}) 
	public void getPage( Page page ){
		
		Page pageRecovered = this.pageDao.get(page);		
		
		if(pageRecovered != null ){
			
			this.result.include("page", pageRecovered);
			this.result.forwardTo(this).formPage();
			
		}else{
			
			this.result.redirectTo(this).list();
			
		}
				
	}
	
    @Get("adm/page/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_PAGE", permissionsTypes = { PermissionType.VIEW})}) 
	public void formPage(){
    	
    }
    
	@Get("adm/page")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_PAGE", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}
    
	@Post("adm/page")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_PAGE", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final Page page) {
		
		page.setDateOfRegistration( new Date() );
		page.setAuthor(this.admUserControl.getAdmUser().getName());
		page.setSlug(TextHelper.createSlug(page.getTitle()));
		
		this.result.include("retorno", this.pageDao.insert(page) );
		this.result.include("tipoSubmit", "cadastrada" );		
		this.result.include("page", page);

		this.result.redirectTo(this).formPage();

	}
	
	@Put("adm/page")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_PAGE", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(final Page page){
		
		page.setSlug(TextHelper.createSlug(page.getTitle()));
		
		this.result.include("retorno", this.pageDao.edit(page) );
		this.result.include("tipoSubmit", "editado" );
		
		this.result.redirectTo(this).getPage(page);		
		
	}
	
    @Delete("adm/page/{page.id}")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_PAGE", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(final Page page){
    	
    	if(this.pageDao.delete(page)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
        	this.result.redirectTo(this).getPage(page);

    		
    	}
    	    	
	}
    
	@Path("adm/page/flexi")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_PAGE", permissionsTypes = { PermissionType.VIEW } ) }) 
	public void getAllPageInFlexiJson( int page, int rp, String sortname, String sortorder, String query, String qtype ){
		
		FlexiGridJson<Page> flexi = null;
	
		try {
			
			int offset = page == 1 ? 0 : ((page - 1) * rp) ;
			
			List<Page> flexiListAdmGroup;
			
			if( query == null || query == "" ){
				
				flexiListAdmGroup = this.pageDao.getAllLimitedAndOrdered( offset , rp, sortname, sortorder );

			}else{
				
				flexiListAdmGroup = this.pageDao.getAllByPropertyName(qtype, query);
				
			}
							
			flexi = new FlexiGridJson<Page>( page, this.pageDao.getAll().size(), flexiListAdmGroup );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}
	
}

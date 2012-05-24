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
import br.com.umake.dao.AdmGroupDao;
import br.com.umake.dao.AdmPermissionDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.model.AdmGroup;
import br.com.umake.model.AdmPermission;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class AdmGroupController {
	
	private AdmGroupDao admGroupDao;
	private AdmPermissionDao admPermissionDao;
	private Result result;
	
	public AdmGroupController( AdmGroupDao admGroupDao, AdmPermissionDao admPermissionDao,  Result result ) {
	
		this.admGroupDao = admGroupDao;
		this.admPermissionDao = admPermissionDao;
		this.result = result;
		
	}
	
	@Get("adm/group/{admGroup.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAdmGroup( AdmGroup admGroup ){
		
		AdmGroup admGroupRecovered = this.admGroupDao.get(admGroup);		
		
		if(admGroupRecovered != null ){
			
			this.result.include("admGroup", admGroupRecovered);
			this.result.forwardTo(this).formAdmGroup();
			
		}else{
			
			this.result.redirectTo(this).list();
			
		}
				
	}
	
    @Get("adm/group/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void formAdmGroup(){
    	
		this.result.include("allAdmGroups", this.admGroupDao.getAll());
    	this.result.include("permissions", this.admPermissionDao.getAll());


	}
    
	@Get("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}
    
	@Post("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final AdmGroup admGroup, List<AdmPermission> permissions) {

		if(admGroup.getParentAdmGroup().getId() == null){

			admGroup.setParentAdmGroup(admGroup);
			
		}
		
		if(permissions != null){
			
			admGroup.getAdmPermissions().addAll(permissions); 		
			
		}
			
		Boolean feedbackInsert = this.admGroupDao.insert(admGroup);
		
		this.result.include("retorno",feedbackInsert );
		this.result.include("tipoSubmit", "cadastrado" );		

		if(!feedbackInsert){
			
			this.result.include("admGroup", admGroup);
			this.result.redirectTo(this).formAdmGroup();	
			
		}else{
		
			this.result.redirectTo(this).getAdmGroup(admGroup);
			
		}

	}
	
	@Put("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(AdmGroup admGroup, List<AdmPermission> permissions){
		
		AdmGroup oldAdmGroup = this.admGroupDao.get(admGroup);
		admGroup.setAdmUsers(oldAdmGroup.getAdmUsers());
		
		if(permissions != null){

			admGroup.getAdmPermissions().addAll(permissions); 		
			
		}
		
		Boolean feedbackUpdate = this.admGroupDao.edit(admGroup);
		
		this.result.include("retorno", feedbackUpdate );
		this.result.include("tipoSubmit", "editado" );
		
		this.result.redirectTo(this).getAdmGroup(admGroup);
		
	}
	
    @Delete("adm/group/{admGroup.id}")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_GROUP", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(AdmGroup admGroup){
    	
    	AdmGroup admRecovered = this.admGroupDao.get(admGroup);
    	
    	if(this.admGroupDao.delete(admRecovered)){
    		
        	this.result.redirectTo(this).list();
    		
    	}else{
    		
        	this.result.redirectTo(this).getAdmGroup(admGroup);
    		
    	}
    	    	
	}
    
	@Path("adm/group/flexi")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW } ) }) 
	public void getAllAdmUserInFlexiJson( int page, int rp, String sortname, String sortorder, String query, String qtype ){
		
		FlexiGridJson<AdmGroup> flexi = null;
	
		try {
			
			int offset = page == 1 ? 0 : ((page - 1) * rp) ;
			
			List<AdmGroup> flexiListAdmGroup;
			
			if( query == null || query == "" ){
				
				flexiListAdmGroup = this.admGroupDao.getAllLimitedAndOrdered( offset , rp, sortname, sortorder );

			}else{
				
				flexiListAdmGroup = this.admGroupDao.getAllByPropertyName(qtype, query);
				
			}
							
			flexi = new FlexiGridJson<AdmGroup>( page, this.admGroupDao.getAll().size(), flexiListAdmGroup );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}
	
}

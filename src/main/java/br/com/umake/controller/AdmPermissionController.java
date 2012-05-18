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
import br.com.umake.dao.AdmPermissionDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.model.AdmPermission;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class AdmPermissionController {
	
	private AdmPermissionDao admPermissionDao;
	private Result result;
	
	public AdmPermissionController( AdmPermissionDao admPermissionDao, Result result) {
	
		this.admPermissionDao = admPermissionDao;
		this.result = result;
		
	}
	
	@Get("adm/permission/{admPermission.id}")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAdmPermission( AdmPermission admPermission ){
						
		AdmPermission admPermissionRecovered = this.admPermissionDao.get(admPermission);

		if(admPermissionRecovered != null ){
			
			this.result.include("admPermission", admPermissionRecovered);
			this.result.forwardTo(this).formAdmPermission();	
			
		}else{
			
			this.result.redirectTo(this).list();
			
		}
				
	}
	
    @Get("adm/permission/create")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.VIEW})}) 
	public void formAdmPermission(){

	}
    
	@Get("adm/permission")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.VIEW})}) 
	public void list(){
						
	}
    
	@Post("adm/permission")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.CREATE})}) 
	public void create(final AdmPermission admPermission) {
				
		Boolean feedbackInsert =  this.admPermissionDao.insert(admPermission);
		
		this.result.include("retorno", feedbackInsert  );
		this.result.include("tipoSubmit", "cadastrada");
		
		if(!feedbackInsert){
			
	    	this.result.include("admPermission", admPermission);
			this.result.redirectTo(this).formAdmPermission();
			
		}else{
		
			this.result.redirectTo(this).getAdmPermission(admPermission);
			
		}

	}
	
	@Put("adm/permission")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(AdmPermission admPermission){

		AdmPermission newAdmPermission = this.admPermissionDao.get(admPermission);
		newAdmPermission.setContext(admPermission.getContext());
		newAdmPermission.setType(admPermission.getType());

		this.result.include("retorno", this.admPermissionDao.edit(admPermission) );
		this.result.include("tipoSubmit", "editada" );		
		
		this.result.redirectTo(this).getAdmPermission(admPermission);	
		
	}
	
    @Delete("adm/permission/{admPermission.id}")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.DELETE})}) 
	public void delete(AdmPermission admPermission){
    	    	
    	if(this.admPermissionDao.delete(admPermission)){
    		
        	this.result.redirectTo(this).list();
    		
    	}else{
    		
        	this.result.redirectTo(this).getAdmPermission(admPermission);
    		
    	}
    	    	
	}
    
	@Path("adm/permission/flexi")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.VIEW } ) }) 
	public void getAllAdmUserInFlexiJson( int page, int rp, String sortname, String sortorder, String query, String qtype ){
		
		FlexiGridJson<AdmPermission> flexi = null;
	
		try {
			
			int offset = page == 1 ? 0 : ((page - 1) * rp) ;
			
			List<AdmPermission> flexiListAdmGroup;
			
			if( query == null || query == "" ){
				
				flexiListAdmGroup = this.admPermissionDao.getAllLimitedAndOrdered( offset , rp, sortname, sortorder );

			}else{
				
				flexiListAdmGroup = this.admPermissionDao.getAllByPropertyName(qtype, query);
				
			}
							
			flexi = new FlexiGridJson<AdmPermission>( page, this.admPermissionDao.getAll().size(), flexiListAdmGroup );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}
}

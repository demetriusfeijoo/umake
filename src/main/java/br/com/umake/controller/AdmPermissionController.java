package br.com.umake.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.umake.dao.AdmPermissionDao;
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
		
		this.result.include("admPermission", this.admPermissionDao.get(admPermission));
		
		this.result.forwardTo(this).formAdmPermission();
				
	}
	
    @Get("adm/permission/create")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.CREATE})}) 
	public void formAdmPermission(){

	}
    
	@Get("adm/permission")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.VIEW})}) 
	public void list(){
						
	}
    
	@Post("adm/permission")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.CREATE})}) 
	public void create(final AdmPermission admPermission) {
				
		if(this.admPermissionDao.insert(admPermission)){
			
    		this.result.include("admPermission", admPermission);

    	}

		this.result.redirectTo(this).formAdmPermission();

	}
	
	@Put("adm/permission")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.EDIT})}) 
	public void editAdmPermission(AdmPermission admPermission){

		AdmPermission newAdmPermission = this.admPermissionDao.get(admPermission);
		newAdmPermission.setContext(admPermission.getContext());
		newAdmPermission.setType(admPermission.getType());

		this.admPermissionDao.edit(newAdmPermission);
		
		this.result.include("admPermission", this.admPermissionDao.get(admPermission));
		
		this.result.forwardTo(this).formAdmPermission();		
		
	}
	
    @Delete("adm/permission")
    @Restrictable(permissions={ @PermissionAnnotation(context="ADM_PERMISSION", permissionsTypes = { PermissionType.DELETE})}) 
	public void delete(final AdmPermission admPermission){
    	
    	if(this.admPermissionDao.delete(admPermission)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("admPermission", admPermission);
        	this.result.forwardTo(this).formAdmPermission();
    		
    	}
    	    	
	}

}

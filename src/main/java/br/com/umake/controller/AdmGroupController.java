package br.com.umake.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.umake.dao.AdmGroupDao;
import br.com.umake.model.AdmGroup;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class AdmGroupController {
	
	private AdmGroupDao groupAdmDao;
	private Result result;
	
	public AdmGroupController( AdmGroupDao admGroupDao, Result result ) {
	
		this.groupAdmDao = admGroupDao;
		this.result = result;
		
	}
	
	@Get("adm/group/{admGroup.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAdmGroup( AdmGroup admGroup ){
		
		this.result.include("admGroup", this.groupAdmDao.get(admGroup));
		
		this.result.forwardTo(this).formAdmGroup();
				
	}
	
    @Get("adm/group/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void formAdmGroup(){
    	
		this.result.include("allAdmGroups", this.groupAdmDao.getAll());

	}
    
	@Get("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}
    
	@Post("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final AdmGroup admGroup) {
		
		if(this.groupAdmDao.insert(admGroup)){
			
			AdmGroup newGroup = this.groupAdmDao.get(admGroup.getParentAdmGroup());
			
			admGroup.getParentAdmGroup().setName(newGroup.getName());
			
    		this.result.include("admGroup", admGroup);

    	}

		this.result.redirectTo(AdmGroupController.class).formAdmGroup();

	}
	
	@Put("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.EDIT})}) 
	public void editAdmGroup(AdmGroup admGroup){
		
		AdmGroup newAdmGroup = this.groupAdmDao.get(admGroup);
		newAdmGroup.setName(admGroup.getName());
		newAdmGroup.setParentAdmGroup(admGroup.getParentAdmGroup());
		
		this.groupAdmDao.edit(newAdmGroup);
		
		AdmGroup newGroup = this.groupAdmDao.get(newAdmGroup.getParentAdmGroup());
		
		admGroup.getParentAdmGroup().setName(newGroup.getName());
		
		this.result.include("admGroup", this.groupAdmDao.get(admGroup));
		
		this.result.forwardTo(this).formAdmGroup();		
		
	}
	
    @Delete("adm/group")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_GROUP", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(final AdmGroup admGroup){
    	
    	if(this.groupAdmDao.delete(admGroup)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("admGroup", admGroup);
        	this.result.forwardTo(this).formAdmGroup();
    		
    	}
    	    	
	}
	
}

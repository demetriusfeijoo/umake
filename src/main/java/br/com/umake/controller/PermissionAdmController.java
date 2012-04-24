package br.com.umake.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.PermissionAdmDao;
import br.com.umake.model.PermissionAdm;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class PermissionAdmController {
	
	//private UserAdmControl userAdmControl;
	private PermissionAdmDao permissionAdmDao;
	private Result result;
	//private Validator validator;
	
	public PermissionAdmController(/*UserAdmControl userControl,*/ PermissionAdmDao permissionAdmDao, Result result/*, Validator validator*/) {
	
		//this.userAdmControl = userControl;
		this.permissionAdmDao = permissionAdmDao;
		this.result = result;
		//this.validator = validator;
		
	}
	
	@Get("adm/permissions/{permissionAdm.id}")
    @Restrictable(permissions={ @PermissionAnnotation(context="PERMISSION", permissionsTypes = { PermissionType.VIEW})}) 
	public void getPermissionAdm( PermissionAdm permissionAdm ){
		
		this.result.include("permissionAdm", this.permissionAdmDao.getPermissionAdm(permissionAdm));
		
		this.result.forwardTo(this).formCreatePermissionAdm();
				
	}
	
    @Get("adm/permissions/create")
    @Restrictable(permissions={ @PermissionAnnotation(context="PERMISSION", permissionsTypes = { PermissionType.CREATE})}) 
	public void formCreatePermissionAdm(){

	}
    
	@Get("adm/permissions")
    @Restrictable(permissions={ @PermissionAnnotation(context="PERMISSION", permissionsTypes = { PermissionType.VIEW})}) 
	public void list(){
						
	}
    
	@Post("adm/permissions")
    @Restrictable(permissions={ @PermissionAnnotation(context="PERMISSION", permissionsTypes = { PermissionType.CREATE})}) 
	public void create(final PermissionAdm permissionAdm) {
				
		if(this.permissionAdmDao.insertPermissionAdm(permissionAdm)){
			
    		this.result.include("permissionAdm", this.permissionAdmDao.getPermissionAdm(permissionAdm));

    	}

		this.result.redirectTo(this).formCreatePermissionAdm();

	}
	
	@Put("adm/permissions")
    @Restrictable(permissions={ @PermissionAnnotation(context="PERMISSION", permissionsTypes = { PermissionType.EDIT})}) 
	public void editPermissionAdm(PermissionAdm permissionAdm){

		PermissionAdm newPermissionAdm = this.permissionAdmDao.getPermissionAdm(permissionAdm);
		newPermissionAdm.setContext(permissionAdm.getContext());
		newPermissionAdm.setType(permissionAdm.getType());

		this.permissionAdmDao.editPermissionAdm(newPermissionAdm);
		
		this.result.include("permissionAdm", this.permissionAdmDao.getPermissionAdm(permissionAdm));
		
		this.result.forwardTo(this).formCreatePermissionAdm();		
		
	}
	
    @Delete("adm/permissions")
    @Restrictable(permissions={ @PermissionAnnotation(context="PERMISSION", permissionsTypes = { PermissionType.DELETE})}) 
	public Boolean delete(final PermissionAdm permissionAdm){
    	
    	if(this.permissionAdmDao.deletePermissionAdm(permissionAdm)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("permissionAdm", permissionAdm);
        	this.result.forwardTo(this).formCreatePermissionAdm();
    		
    	}
    	
    	return true;
    	
	}

	@Path("adm/permissions/json")
	public void getAllPermissionAdmInJson(){
		
		this.result.use(Results.json()).from(this.permissionAdmDao.getAllPermissionAdm()).serialize();	
		
	}	
}

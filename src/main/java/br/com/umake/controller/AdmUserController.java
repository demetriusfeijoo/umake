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
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.AdmGroupDao;
import br.com.umake.dao.AdmPermissionDao;
import br.com.umake.dao.AdmUserDao;
import br.com.umake.helper.FlexiGridJson;
import br.com.umake.interceptor.AdmUserControl;
import br.com.umake.model.AdmGroup;
import br.com.umake.model.AdmPermission;
import br.com.umake.model.AdmUser;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class AdmUserController { 

	private AdmUserControl admUserControl;
	private AdmUserDao admUserDao;
	private AdmGroupDao admGroupDao;
	private AdmPermissionDao admPermissionDao;
	private Result result;
	private Validator validator;
	
	public AdmUserController(AdmUserControl admUserControl, AdmUserDao admUserDao, AdmGroupDao admGroupDao, AdmPermissionDao admPermissionDao, Result result, Validator validator) {
	
		this.admUserControl = admUserControl;
		this.admUserDao = admUserDao;
		this.admGroupDao = admGroupDao;
		this.admPermissionDao = admPermissionDao;
		this.result = result;
		this.validator = validator;
		
	}
	
	@Get("adm/user/login")
	public void formLogin() {
		
		if( this.admUserControl.isLogged() ) 
			
			this.result.redirectTo(AdmController.class).index();
					
	}
    
	@Post("adm/user/login")
	public void login(final AdmUser admUser) { 
		
		AdmUser recovery = this.admUserDao.findAdmUser(admUser);
		
		if (recovery == null) {

			validator.add(new ValidationMessage("Login e/ou senha inválidos",""));

		}
		
		this.admUserControl.login(recovery);

		validator.onErrorUsePageOf(this).formLogin();
		
		result.redirectTo(AdmController.class).index();

	}

	@Get("adm/user/logout")
	@Restrictable
	public void logout() {
		
		this.admUserControl.logout();
		this.result.redirectTo(this).formLogin();

	}
	
	@Get("adm/user/{admUser.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAdmUser( AdmUser admUser ){
		
		AdmUser admUserRecovered = this.admUserDao.get(admUser);

		if(admUserRecovered != null ){
			
			this.result.include("admUser", admUserRecovered);
			this.result.forwardTo(this).formAdmUser();	
			
		}else{
			
			this.result.redirectTo(this).list();
			
		}
					
	}
	
    @Get("adm/user/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void formAdmUser(){
    	
    	this.result.include("admGroups", this.admGroupDao.getAll());
    	this.result.include("admPermissions", this.admPermissionDao.getAll());
    	
	}

	@Get("adm/user")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_USER", permissionsTypes = { PermissionType.VIEW }) } ) 
	public void list(){
						
	}

	@Post("adm/user")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_USER", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final AdmUser admUser, List<AdmGroup> admGroups, List<AdmPermission> admPermissions){
    	
		if(admGroups != null){
			
			admUser.getAdmGroups().addAll(admGroups);
			
		}
		
		if(admPermissions != null){
			
			admUser.getAdmPermissions().addAll(admPermissions);
			
		}
		
		Date dateInsert = new Date();
		
		admUser.setDateOfRegistration(dateInsert);
		admUser.setDateLastVisit(dateInsert);
    	 
		this.result.include("retorno", this.admUserDao.insert(admUser) );
		this.result.include("tipoSubmit", "cadastrado" );
		this.result.include("admUser", admUser);
		
		this.result.redirectTo(AdmUserController.class).formAdmUser();

	}
	
	@Put("adm/user")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_USER", permissionsTypes = { PermissionType.EDIT})}) 
	public void editAdmUser(AdmUser admUser, List<AdmGroup> admGroups, List<AdmPermission> admPermissions){

		AdmUser oldAdmUser = this.admUserDao.get(admUser);
		admUser.setDateLastVisit(oldAdmUser.getDateLastVisit());
		oldAdmUser = null;
		
		if(admGroups != null){
			
			admUser.getAdmGroups().addAll(admGroups);
			
		}
		
		if(admPermissions != null){
			
			admUser.getAdmPermissions().addAll(admPermissions);
			
		}
		
		this.result.include("retorno", this.admUserDao.edit(admUser) );
		this.result.include("tipoSubmit", "editado" );
		this.result.include("admUser", this.admUserDao.get(admUser) );
		
		this.result.forwardTo(this).formAdmUser();		
		
	}
	
    @Delete("adm/user")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_USER", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(final AdmUser admUser){

		admUser.setDateLastVisit(new Date());
		
    	if(this.admUserDao.delete(admUser)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("admUser", admUser);
        	this.result.forwardTo(this).formAdmUser();
    		
    	}
    	    	
	}

	@Path("adm/user/flexi")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_USER", permissionsTypes = { PermissionType.VIEW } ) }) 
	public void getAllAdmUserInFlexiJson( int page, int rp, String sortname, String sortorder, String query, String qtype ){
		
		FlexiGridJson<AdmUser> flexi = null;
	
		try {
			
			int offset = page == 1 ? 0 : ((page - 1) * rp) ;
			
			List<AdmUser> flexiListAdmUser;
			
			if( query == null || query == "" ){
				
				flexiListAdmUser = this.admUserDao.getAllLimitedAndOrdered( offset , rp, sortname, sortorder );

			}else{
				
				flexiListAdmUser = this.admUserDao.getAllByPropertyName(qtype, query);
				
			}
							
			flexi = new FlexiGridJson<AdmUser>( page, this.admUserDao.getAll().size(), flexiListAdmUser );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}
	


}

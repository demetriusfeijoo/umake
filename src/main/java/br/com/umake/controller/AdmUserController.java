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
import br.com.umake.dao.PageDao;
import br.com.umake.helper.flexigrid.FlexiGridJson;
import br.com.umake.interceptor.AdmUserControl;
import br.com.umake.model.AdmGroup;
import br.com.umake.model.AdmPermission;
import br.com.umake.model.AdmUser;
import br.com.umake.model.Page;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class AdmUserController { 

	private AdmUserControl admUserControl;
	private AdmUserDao admUserDao;
	private AdmGroupDao admGroupDao;
	private AdmPermissionDao admPermissionDao;
	private PageDao pageDao;
	private Result result;
	private Validator validator;
	
	public AdmUserController(AdmUserControl admUserControl, AdmUserDao admUserDao, AdmGroupDao admGroupDao, AdmPermissionDao admPermissionDao, PageDao pageDao, Result result, Validator validator) {
	
		this.admUserControl = admUserControl;
		this.admUserDao = admUserDao;
		this.admGroupDao = admGroupDao;
		this.admPermissionDao = admPermissionDao;
		this.pageDao = pageDao;
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

		}else{
			
			this.admUserControl.login(recovery);			
			
		}
		
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
    	
		Boolean feedbackInsert =  this.admUserDao.insert(admUser);
		
		this.result.include("retorno", feedbackInsert );
		this.result.include("tipoSubmit", "cadastrado" );
		
		if(!feedbackInsert){
			
			this.result.include("admUser", admUser);
			this.result.redirectTo(this).formAdmUser();	
			
		}else{
		
			this.result.redirectTo(this).getAdmUser(admUser);
			
		}
		
	}
	
	@Put("adm/user")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_USER", permissionsTypes = { PermissionType.EDIT})}) 
	public void edit(AdmUser admUser, List<AdmGroup> admGroups, List<AdmPermission> admPermissions){

		AdmUser oldAdmUser = this.admUserDao.get(admUser);
		admUser.setDateLastVisit(oldAdmUser.getDateLastVisit());
		oldAdmUser = null;
		
		if(admGroups != null){
			
			admUser.getAdmGroups().addAll(admGroups);
			
		}
		
		if(admPermissions != null){
			
			admUser.getAdmPermissions().addAll(admPermissions);
			
		}
		
		Boolean feedbackUpdate = this.admUserDao.edit(admUser);

		this.result.include("retorno", feedbackUpdate );
		this.result.include("tipoSubmit", "editado" );
		
		this.result.redirectTo(this).getAdmUser(admUser);		
		
	}
	
    @Delete("adm/user/{admUser.id}")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_USER", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(AdmUser admUser){
		
		AdmUser admUserTemp = this.admUserDao.get(admUser);
		List<Page> pages = pageDao.getAutor(admUserTemp);
		admUserTemp.setDateLastVisit(new Date());
		
		if(pages.size() > 0){
			
			admUserTemp.setStatus(0);
			admUserDao.desativar(admUserTemp);
			pageDao.disablePages(pages);
			
        	this.result.redirectTo(this).list();
			
		}else{
		
	    	if(this.admUserDao.delete(admUserTemp)){
	    		
	        	this.result.redirectTo(this).list();
	    		
	    	}else{
	    		
	        	this.result.redirectTo(this).getAdmUser(admUser);
	    		
	    	}
    	
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

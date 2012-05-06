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
import br.com.umake.helper.FlexiGridJson;
import br.com.umake.model.AdmGroup;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class AdmGroupController {
	
	private AdmGroupDao admGroupDao;
	private Result result;
	
	public AdmGroupController( AdmGroupDao admGroupDao, Result result ) {
	
		this.admGroupDao = admGroupDao;
		this.result = result;
		
	}
	
	@Get("adm/group/{admGroup.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAdmGroup( AdmGroup admGroup ){
		
		this.result.include("admGroup", this.admGroupDao.get(admGroup));
		
		this.result.forwardTo(this).formAdmGroup();
				
	}
	
    @Get("adm/group/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void formAdmGroup(){
    	
		this.result.include("allAdmGroups", this.admGroupDao.getAll());

	}
    
	@Get("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}
    
	@Post("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final AdmGroup admGroup) {
		
		if(this.admGroupDao.insert(admGroup)){
			
			AdmGroup newGroup = this.admGroupDao.get(admGroup.getParentAdmGroup());
			
			admGroup.getParentAdmGroup().setName(newGroup.getName());
			
    		this.result.include("admGroup", admGroup);

    	}

		this.result.redirectTo(AdmGroupController.class).formAdmGroup();

	}
	
	@Put("adm/group")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.EDIT})}) 
	public void editAdmGroup(AdmGroup admGroup){
		
		AdmGroup newAdmGroup = this.admGroupDao.get(admGroup);
		newAdmGroup.setName(admGroup.getName());
		newAdmGroup.setParentAdmGroup(admGroup.getParentAdmGroup());
		
		this.admGroupDao.edit(newAdmGroup);
		
		this.result.include("admGroup", this.admGroupDao.get(admGroup));
		
		this.result.forwardTo(this).formAdmGroup();		
		
	}
	
    @Delete("adm/group")
    @Restrictable(permissions={@PermissionAnnotation(context="ADM_GROUP", permissionsTypes={ PermissionType.DELETE} )})
	public void delete(final AdmGroup admGroup){
    	
    	if(this.admGroupDao.delete(admGroup)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("admGroup", admGroup);
        	this.result.forwardTo(this).formAdmGroup();
    		
    	}
    	    	
	}
    
	@Path("adm/group/flexi")
	@Restrictable(permissions={ @PermissionAnnotation(context="ADM_GROUP", permissionsTypes = { PermissionType.VIEW } ) }) 
	public void getAllAdmUserInFlexiJson( int page, int rp, String sortname, String sortorder, String query, String qtype ){
		
		FlexiGridJson<AdmGroup> flexi = null;
	
		try {
			
			int offset = page == 1 ? 0 : ((page - 1) * rp) ;
			
			List<AdmGroup> flexiListAdmUser;
			
			if( query == null || query == "" ){
				
				flexiListAdmUser = this.admGroupDao.getAllLimitedAndOrdered( offset , rp, sortname, sortorder );

			}else{
				
				flexiListAdmUser = this.admGroupDao.getAllByPropertyName(qtype, query);
				
			}
							
			flexi = new FlexiGridJson<AdmGroup>( page, this.admGroupDao.getAll().size(), flexiListAdmUser );
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		this.result.use(Results.json()).withoutRoot().from(flexi).recursive().serialize();
		
	}
	
}

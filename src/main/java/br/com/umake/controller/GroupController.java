package br.com.umake.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
//import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.umake.dao.GroupDao;
//import br.com.umake.interceptor.UserAdmControl;
import br.com.umake.model.Group;
//import br.com.umake.model.UserAdm;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Resource
public class GroupController {
	
	//private UserAdmControl userAdmControl;
	private GroupDao groupDao;
	private Result result;
	//private Validator validator;
	
	public GroupController(/*UserAdmControl userControl,*/ GroupDao gDao, Result result/*, Validator validator*/) {
	
		//this.userAdmControl = userControl;
		this.groupDao = gDao;
		this.result = result;
		//this.validator = validator;
		
	}
	
	@Get("adm/groups/{group.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void getGroup( Group group ){
		
		this.result.include("group", this.groupDao.getGroup(group));
		
		this.result.forwardTo(this).formCreateGroup();
				
	}
	
    @Get("adm/groups/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="GROUP", permissionsTypes = { PermissionType.VIEW})}) 
	public void formCreateGroup(){
    	
		this.result.include("allGroups", this.groupDao.getAllGroups());

	}
    
	@Get("adm/groups")
	@Restrictable(permissions={ @PermissionAnnotation(context="GROUP", permissionsTypes = { PermissionType.VIEW}) } ) 
	public void list(){
						
	}

	@Path("adm/groups/json")
	public void getAllGroupInJson(){
		
		System.out.println("entrou");
		this.result.use(Results.json()).from(this.groupDao.getAllGroup()).serialize();	
		
	}
    
	@Post("adm/groups")
	@Restrictable(permissions={ @PermissionAnnotation(context="GROUP", permissionsTypes = { PermissionType.CREATE }) }) 
	public void create(final Group group) {
				
		if(this.groupDao.insertGroup(group)){
			
    		this.result.include("group", this.groupDao.getGroup(group));

    	}

		this.result.redirectTo(GroupController.class).formCreateGroup();

	}
	
	@Put("adm/groups")
	@Restrictable(permissions={ @PermissionAnnotation(context="GROUP", permissionsTypes = { PermissionType.EDIT})}) 
	public void editGroup(Group group){
		
		Group newGroup = this.groupDao.getGroup(group);
		newGroup.setName(group.getName());
		newGroup.setParentGroup(group.getParentGroup());
		
		this.groupDao.editGroup(newGroup);
		
		this.result.include("group", this.groupDao.getGroup(group));
		
		this.result.forwardTo(this).formCreateGroup();		
		
	}
	
    @Delete("adm/groups")
    @Restrictable(permissions={@PermissionAnnotation(context="GROUP", permissionsTypes={ PermissionType.DELETE} )})
	public Boolean delete(final Group group){
    	
    	if(this.groupDao.deleteGroup(group)){
    		
        	this.result.forwardTo(this).list();
    		
    	}else{
    		
    		this.result.include("group", group);
        	this.result.forwardTo(this).formCreateGroup();
    		
    	}
    	
    	return true;
    	
	}
	
}

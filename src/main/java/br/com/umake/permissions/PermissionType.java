package br.com.umake.permissions;

public enum PermissionType {
	
	CREATE("CREATE"), VIEW("VIEW"), DELETE("DELETE"), EDIT("EDIT");
	
	String name;
	
	PermissionType( String name){
		
		this.name = name;
		
	}
	
}

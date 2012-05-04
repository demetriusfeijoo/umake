$(function(){
	
	var urlOfSystem = "/umake";
	
	$("#listagem").flexigrid({
		url: urlOfSystem+"/adm/user/flexi",
		dataType: 'json',
		colModel : [
			{display: 'Id', id : 'id', width : 40, sortable : true, align: 'left'},
			{display: 'Nome', name : 'name', width : 180, sortable : true, align: 'left'},
			{display: 'E-Mail', name : 'email', width : 180, sortable : true, align: 'left'},
			{display: 'Data do registro', name : 'dateOfRegistration', width : 180, sortable : true, align: 'left'}
			],
         buttons : [
                    
             {name: 'Edit', bclass: 'edit', onpress : function(com, grid)
            	 {  
	            	 $('.trSelected', grid).each(function() {
	            		 var id = $(this).attr('id');
	            		 id = id.substring(id.lastIndexOf('row')+3); 
	            		 window.location.href=urlOfSystem+"/adm/user/"+id;
	            	 });
            	 }},
             {separator: true}
            	 
             ],
         searchitems : [
              {display: 'Nome', name : 'name'},
              {display: 'E-Mail', name : 'email'}
              ],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Usuários',
		useRp: true,
		rp: 2,
		resizable: false,
		showTableToggleBtn: true,
		singleSelect: true,
		width: 580,
		height: 200
	}); 
	
	$("#formLogin").validate({
		rules: {
			
			login: {
				
				required: true,
				minlength: 2
				
			},
			
			pwd: {
				
				required: true,
				minlength: 6
				
			}
			
		},
		
		messages: "Login e/ou senha inválidos"
		
	});
	
	
	$("#formAdmUser").validate({
		
		rules: { 
			
			"admUser.name": {
				
				required: true,
				minlength: 2,
				maxlength: 45
				
			},
			
			"admUser.email": {
				
				required: true,
				email: true,
				maxlength: 80
				
			},
			
			"admUser.login": {
				
				required: true,
				minlength: 2,
				maxlength: 45
				
			},
			
			"admUser.password": {
				
				required: true,
				minlength: 6,
				maxlength: 45
				
			}
			
		},
		
		messages: {
			
			"admUser.name": {
				
				required: "Preencha o nome",
				minlength: "O nome necessita conter no mínimo 2 caracteres",
				maxlength: "O nome necessita conter no máximo 45 caracteres"
				
			},
			
			"admUser.email": {
				
				required: "Preencha o email",
				maxlength: "O e-mail necessita conter no máximo 80 caracteres",
				email: "Preencha com um email válido"
				
			},
			
			"admUser.login": {
				
				required: "Preencha o login",
				minlength: "O login necessita conter no mínimo 2 caracteres",
				maxlength: "O login necessita conter no máximo 45 caracteres"
				
			},
			
			"admUser.password": {
				
				required: "Preencha o password",
				minlength: "O password necessita ter no mínimo 6 caracteres",
				maxlength: "O password necessita ter no máximo 45 caracteres"
				
			}
			
		} 
		
	});
	
	$("#formAdmGroup").validate({
		
		rules: {
			
			"admGroup.name": {
				
				required: true,
				minlength: 2,
				maxlength: 45
				
			}	
	
		},
	
		messages: {
			
			"admGroup.name": {
				
				required: "Preencha o nome",
				minlength: "O nome necessita ter no mínimo 2 caracteres",
				maxlength: "O nome necessita ter no máximo 45 caracteres"
				
			}
			
		}
		
	});
	
	$("#formAdmPermission").validate({
		
		rules: {
			
			"admPermission.context": {
				
				required: true,
				minlength: 2,
				maxlength: 45
				
			}	
	
		},
	
		messages: {
			
			"admPermission.context": {
				
				required: "Preencha o contexto",
				minlength: "O contexto necessita ter no mínimo 2 caracteres",
				maxlength: "O contexto necessita ter no máximo 45 caracteres"
				
			}
			
		}
		
	});

});
$(function(){
	
	var urlOfSystem = "/umake";
	
	$("#listagemUser").flexigrid({
		url: urlOfSystem+"/adm/user/flexi",
		dataType: 'json',
		colModel : [
			{display: 'Id', name : 'id', width : 40, sortable : true, align: 'left'},
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
              {display: 'Id', name : 'id'},
              {display: 'Nome', name : 'name'},
              {display: 'E-Mail', name : 'email'}
              ],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Usuários',
		useRp: true,
		rp: 10,
		resizable: false,
		showTableToggleBtn: true,
		singleSelect: true,
		width: 580,
		height: 200
	}); 

	$("#listagemGroup").flexigrid({
		url: urlOfSystem+"/adm/group/flexi",
		dataType: 'json',
		colModel : [
			{display: 'Id', name : 'id', width : 40, sortable : true, align: 'left'},
			{display: 'Nome', name : 'name', width : 180, sortable : true, align: 'left'}
			],
         buttons : [
                    
             {name: 'Edit', bclass: 'edit', onpress : function(com, grid)
            	 {  
	            	 $('.trSelected', grid).each(function() {
	            		 var id = $(this).attr('id');
	            		 id = id.substring(id.lastIndexOf('row')+3); 
	            		 window.location.href=urlOfSystem+"/adm/group/"+id;
	            	 });
            	 }},
             {separator: true}
            	 
             ],
         searchitems : [
              {display: 'Id', name : 'id'},
              {display: 'Nome', name : 'name'}
              ],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Grupos',
		useRp: true,
		rp: 10,
		resizable: false,
		showTableToggleBtn: true,
		singleSelect: true,
		width: 580,
		height: 200
	});


	$("#listagemPermission").flexigrid({
		url: urlOfSystem+"/adm/permission/flexi",
		dataType: 'json',
		colModel : [
			{display: 'Id', name : 'id', width : 45, sortable : true, align: 'left'},
			{display: 'Context', name : 'context', width : 210, sortable : true, align: 'left'},
			{display: 'Type', name : 'type', width : 210, sortable : true, align: 'left'}
			],
         buttons : [
                    
             {name: 'Edit', bclass: 'edit', onpress : function(com, grid)
            	 {  
	            	 $('.trSelected', grid).each(function() {
	            		 var id = $(this).attr('id');
	            		 id = id.substring(id.lastIndexOf('row')+3); 
	            		 window.location.href=urlOfSystem+"/adm/permission/"+id;
	            	 });
            	 }},
             {separator: true}
            	 
             ],
         searchitems : [
              {display: 'Id', name : 'id'},
              {display: 'Context', name : 'context'},
              {display: 'Type', name : 'type'}
              ],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Permissões',
		useRp: true,
		rp: 10,
		resizable: false,
		showTableToggleBtn: true,
		singleSelect: true,
		width: 520,
		height: 200
	});


	$("#listagemPage").flexigrid({
		url: urlOfSystem+"/adm/page/flexi",
		dataType: 'json',
		colModel : [
			{display: 'Id', name : 'id', width : 45, sortable : true, align: 'left'},
			{display: 'Autor', name : 'author', width : 210, sortable : true, align: 'left'},
			{display: 'Data do registro', name : 'dateOfRegistration', width : 210, sortable : true, align: 'left'}
			],
         buttons : [
                    
             {name: 'Edit', bclass: 'edit', onpress : function(com, grid)
            	 {  
	            	 $('.trSelected', grid).each(function() {
	            		 var id = $(this).attr('id');
	            		 id = id.substring(id.lastIndexOf('row')+3); 
	            		 window.location.href=urlOfSystem+"/adm/page/"+id;
	            	 });
            	 }},
             {separator: true}
            	 
             ],
         searchitems : [
              {display: 'Id', name : 'id'},
              {display: 'Autor', name : 'author'}
              ],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Páginas',
		useRp: true,
		rp: 10,
		resizable: false,
		showTableToggleBtn: true,
		singleSelect: true,
		width: 520,
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
	
	$("#formAdmUser button").click(function(){
		
		$(this).parent("form").validate({
		
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
		
	});
	
	$("#formAdmGroup button").click(function(){
		
		$(this).parent("form").validate({
			
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
		
	});
	
	$("#formAdmPermission button").click(function(){
		
		$(this).parent("form").validate({
		
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
	
	$("#submitDelete").click(function(){
		$("#dialog-confirm").dialog({
			resizable: false,
			height: 220,
			title: "Alerta!",
			modal: true,
			buttons: {
				"Confirmar": function() {
					$( this ).dialog( "close" );
					$("#formAdmUser, #formAdmGroup, #formAdmPermission").submit();
				},
				Fechar: function() {
					$( this ).dialog( "close" );
				}
			}
		});
		return false;		
	});

	tinyMCE.init({
        // General options
        mode : "textareas",
        theme : "advanced",
        plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

        // Theme options
        theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
        theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : false,

	});
});
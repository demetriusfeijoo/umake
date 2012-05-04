$(function(){
	
	var urlOfSystem = "/umake";
	
	$("#listagemUser").flexigrid({
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
			{display: 'Id', id : 'id', width : 40, sortable : true, align: 'left'},
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
});
$(function(){

	$("#listagem").flexigrid({
		url: '/umake/adm/users/json',
		dataType: 'json',
		colModel : [
<<<<<<< HEAD
			{display: 'Id', id : 'id', width : 40, sortable : true, align: 'center'},
			{display: 'Name', name : 'name', width : 180, sortable : true, align: 'left'}
=======
			{display: 'Id', name : 'id', width : 40, sortable : true, align: 'left'},
			{display: 'Name', name : 'name', width : 180, sortable : true, align: 'right'}
>>>>>>> upstream/master
			],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Usuários',
		useRp: true,
		rp: 15,
		showTableToggleBtn: true,
		width: 220,
		height: 200
	}); 
<<<<<<< HEAD
		
=======

>>>>>>> upstream/master
});
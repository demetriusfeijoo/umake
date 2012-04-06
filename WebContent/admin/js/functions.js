$(function(){

	$("#listagem").flexigrid({
		url: '/umake/adm/users/json',
		dataType: 'json',
		colModel : [
			{display: 'Id', id : 'id', width : 40, sortable : true, align: 'center'},
			{display: 'Name', name : 'name', width : 180, sortable : true, align: 'left'}
			],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Usuários',
		useRp: true,
		rp: 15,
		showTableToggleBtn: true,
		width: 700,
		height: 200
	}); 
		
});
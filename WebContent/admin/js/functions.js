$(function(){

	$("#listagem").flexigrid({
		url: '/umake/adm/users/json',
		dataType: 'json',
		colModel : [
			{display: 'Id', name : 'id', width : 40, sortable : true, align: 'left'},
			{display: 'Name', name : 'name', width : 180, sortable : true, align: 'right'}
			],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Countries',
		useRp: true,
		rp: 15,
		showTableToggleBtn: true,
		width: 220,
		height: 200
	}); 

});
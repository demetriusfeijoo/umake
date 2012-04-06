$(function(){

	$("#listagem").flexigrid({
		url: '',
		dataType: 'json',
		colModel : [
			{display: 'Id', name : 'id', width : 40, sortable : true, align: 'center'},
			{display: 'Name', name : 'name', width : 180, sortable : true, align: 'left'}
			],
		sortname: "id",
		sortorder: "asc",
		usepager: true,
		title: 'Countries',
		useRp: true,
		rp: 15,
		showTableToggleBtn: true,
		width: 700,
		height: 200
	}); 
	
	$("body").load('<c:url value="/adm/users/alluser.json" />');
	
});
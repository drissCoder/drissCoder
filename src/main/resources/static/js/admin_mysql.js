$( document ).ready(function() {

  $('#confirmer').click(function(){
	$('#paginatedTable').DataTable( {
	      //bDestroy: true,
		    processing: true,
		    serverSide: true,
          pageLength: 10,
	       ordering: false,
	       searchable : true,
	       paging: true,
			 //dom: 'flrtip',
        ajax: {
            url: "usersFilter", 
            contentType: "application/json",
            type: "POST", 
            dataType: "json",
            cache: false,
            data: function ( data ) {
            return JSON.stringify(data);
              }
         },
        columns: [
                    { data: "id", name : "ID", title : "ID"  },
                    { data: "name", name : "Name" , title : "Name"},
                    { data: "salary", name : "Salary" , title : "Salary"}
                ]    
	});
	$('#paginatedTable').dataTable().fnSetFilteringEnterPress();
});
});
$(function() {
    $('#search').on('keyup', function() {
        var pattern = $(this).val();
        $('.searchable-container .items').hide();
        $('.searchable-container .items').filter(function() {
            return $(this).text().match(new RegExp(pattern, 'i'));
        }).show();
    });
    
});
function selectBfset(id, name){
	if(confirm("Proceed with Breakfast set " + name)){
		document.location = "selectServingSyle?bfSetId="+id;
	}
}

function selectServStyle(id, name){
	if(confirm("Proceed with Serving Style " + name)){
		document.location = "editItems?sstyleId="+id;
	}
}
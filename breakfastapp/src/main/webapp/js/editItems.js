$(function() {
    var action;
    $(".number-spinner button").mousedown(function () {
        btn = $(this);
        input = btn.closest('.number-spinner').find('input');
        btn.closest('.number-spinner').find('button').prop("disabled", false);

    	if (btn.attr('data-dir') == 'up') {
            action = setInterval(function(){
                if ( input.attr('max') == undefined || parseInt(input.val()) < parseInt(input.attr('max')) ) {
                    input.val(parseInt(input.val())+1);
                }else{
                    btn.prop("disabled", true);
                    clearInterval(action);
                }
            }, 50);
    	} else {
            action = setInterval(function(){
                if ( input.attr('min') == undefined || parseInt(input.val()) > parseInt(input.attr('min')) ) {
                    input.val(parseInt(input.val())-1);
                }else{
                    btn.prop("disabled", true);
                    clearInterval(action);
                }
            }, 50);
    	}
    }).mouseup(function(){
        clearInterval(action);
    });
});

function confirmOrder(){
	var urlName = "preconfirm";
	var data = [];
	ips = document.getElementsByTagName('input');
	
	for(var i=0;i<ips.length;i++) {
		var item = {};
		item['id'] = ips[i].id;
		item['value'] = ips[i].value;
		data.push(item);
	}
	$.ajax({ 
	    url:urlName,    
	    type:"POST", 
	    contentType : "application/json",
	    data: JSON.stringify(data), //Stringified Json Object
	    async: true,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
	    cache: false,    //This will force requested pages not to be cached by the browser          
	    processData:false, //To avoid making query String instead of JSON
	    success:function(data){
	    	document.location=data;
	    }
	    
	});
}
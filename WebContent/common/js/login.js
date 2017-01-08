$('#login').submit(function(){
	$.ajax({                
	    url: '/javaee/UserLogin.action',
	    data: {
	    	username: $('#username').val(),
	    	password: $('#password').val(),
	    	authority: $('input[name="authority"]:checked ').val()
	    },
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ 
	    	console.log("操作失败");
	    },
	    success:function(data){
	    	if(data.err === 0){
	    		alert("登录成功");
	    		window.location.href='http://localhost:8080/javaee/admin.html';
	    	}else alert(data.msg);
	    }
	});
	return false;
});
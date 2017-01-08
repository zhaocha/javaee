$('#register').submit(function(){
	$.ajax({                
	    url: '/javaee/UserRegister.action',
	    data: {
	    	username: $('#username').val(),
	    	password: $('#password').val(),
	    	repassword : $('#repassword').val()
	    },
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ 
	    	console.log("操作失败");
	    },
	    success:function(data){
	    	
	    	if(data.err === 0){
	    		alert('注册成功'); 
	    		window.location.href='http://localhost:8080/javaee/login.html';
	    	}else alert(data.msg);
	    }
	});
	return false;
});
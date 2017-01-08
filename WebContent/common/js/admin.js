selAllStu();
var obj;

// 管理员界面
function admin_stuInfo(){
	 document.getElementById('show_content').innerHTML ="<table id='content' class='table table-bordered' >\
			<tr>\
				<th>序号</th>\
				<th>学号</th>\
				<th>姓名</th>\
				<th>性别</th>\
				<th>年龄</th>\
				<th>班级</th>\
				<th>电话</th>\
				<th>地址</th>\
				<th colspan='2'>操作</th>\
			</tr>\
			</table>";
	 for(var i=0;i<obj.length;i++){
		 var t = i+1;
		 document.getElementById('content').innerHTML += "<tr><td>"+t+"</td><td>"+obj[i].stu_id+"</td><td>"+obj[i].stu_name+"</td><td>"+obj[i].sex+"</td><td>"+obj[i].age+"</td><td>"+obj[i].stu_class+"</td><td>"+obj[i].phone+"</td><td>"+obj[i].address+"</td><td><a href='javascript:void(0);' onclick='alterStu("+obj[i].stu_id+","+i+")' id='' class='btn btn-info' data-toggle='modal' data-target='#update'>修改</a></td><td><a class='btn btn-danger' href='javascript:void(0);' onclick='delStu("+obj[i].stu_id+")' id='' >删除</a></td></tr>";
	 }
}

//普通用户界面
function stuInfo(){
	 document.getElementById('show_content').innerHTML ="<table id='content' class='table table-bordered'>\
			<tr>\
				<th>序号</th>\
				<th>学号</th>\
				<th>姓名</th>\
				<th>性别</th>\
				<th>年龄</th>\
				<th>班级</th>\
				<th>电话</th>\
				<th>地址</th>\
				<th>操作</th>\
			</tr>\
			</table>";
	 for(var i=0;i<obj.length;i++){
		 var t = i+1;
		 document.getElementById('content').innerHTML += "<tr><td>"+t+"</td><td>"+obj[i].stu_id+"</td><td>"+obj[i].stu_name+"</td><td>"+obj[i].sex+"</td><td>"+obj[i].age+"</td><td>"+obj[i].stu_class+"</td><td>"+obj[i].phone+"</td><td>"+obj[i].address+"</td><td><a href='javascript:void(0);' onclick='alterStu("+obj[i].stu_id+","+i+")' id='' data-toggle='modal' class='btn btn-info' data-target='#update'>修改</a></td></tr>";
	 }
}


//查看所有学生信息
function selAllStu(){
	$.ajax({                
	    url: '/javaee/SelectAllInfo.action',
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ //失败
	    	alert("操作失败");
	    },
	    success:function(data){
	    	obj = data.msg;
	    	if(data.err === 2) admin_stuInfo();
	    	else stuInfo();
	    	
	    	
	    } ,
	});
}

function selStu(stu_id){
	$.ajax({                
	    url: '/javaee/SelectInfo.action',
	    data: {
	    	stu_id:stu_id
	    },
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ //失败
	    	alert("操作失败");
	    },
	    success:function(data){
	    	stuInfo();
	    } ,
	});
}

//删除学生信息
function delStu(stu_id){
	$.ajax({                
	    url: '/javaee/DeleteInfo.action',
	    data: {
	        stu_id:stu_id
	    } ,
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ //失败
	    	alert("操作失败");
	    },
	    success:function(data){
	    	alert("操作成功");
	    	selAllStu();
	    } ,
	});
}


//添加学生信息,监听了form表单
$('#form-add').submit(function(){
	$.ajax({
		url: '/javaee/AddInfo.action',
		cache: true,
        type: "POST",
        data: {
            stu_id: $('#stu_id').val(),
            stu_name: $('#stu_name').val(),
            sex: $('input[name="sex"]:checked ').val(),
            age: $('#age').val(),
            stu_class: $('#stu_class').val(),
            phone: $('#phone').val(),
            address: $('#address').val()
        },
        dataType:'json',
        error: function(request) {
        },
        success: function(data) {
        	$('#add').modal('hide');
            	selAllStu();
        }
    });
	return false;
});

//修改学生信息,监听了form表单
$('#form-update').submit(function(){
	$.ajax({
		url: '/javaee/UpdateInfo.action',
		cache: true,
        type: "POST",
        data: {
            stu_id: $('#up_stu_id').val(),
            stu_name: $('#up_stu_name').val(),
            sex: $('input[name="up_sex"]:checked ').val(),
            age: $('#up_age').val(),
            stu_class: $('#up_stu_class').val(),
            phone: $('#up_phone').val(),
            address: $('#up_address').val()
        },
        dataType:'json',
        error: function(request) {
        	alert('操作失败');
        },
        success: function(data) {
        	$('#update').modal('hide');
            selAllStu();
        }
    });
	return false;
});

function newForm(){
	document.getElementById('stu_id').value = null;
    document.getElementById('stu_name').value = null;
    $("input[type='radio']").removeAttr('checked');
    document.getElementById('age').value = null;
    document.getElementById('stu_class').value = null;
    document.getElementById('phone').value = null;
    document.getElementById('address').value = null;
}

//修改信息
function alterStu(stu_id, j){
	var s =  obj[j].sex == '男'? 'man' : 'woman';
    document.getElementById('up_stu_id').value = obj[j].stu_id;
    document.getElementById('up_stu_id').readOnly = "readonly";
    document.getElementById('up_stu_name').value = obj[j].stu_name;
    //对radio值要单独重新赋值
    $('input').removeAttr('checked'); 
    $($('#'+s).eq(0)).prop('checked',true);
    document.getElementById('up_age').value = obj[j].age;
    document.getElementById('up_stu_class').value = obj[j].stu_class;
    document.getElementById('up_phone').value = obj[j].phone;
    document.getElementById('up_address').value = obj[j].address;
}

//统计班级人数
function classPerson(){
	$.ajax({                
	    url: '/javaee/classPerson.action',
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ //失败
	    	alert("操作失败");
	    },
	    success:function(data){ 
	    	console.log(data);
	    	echartsPerson(data.msg); 	
	    } ,
	});
}


//统计学生性别
function stuSex(){
	$.ajax({                
	    url: '/javaee/stuSex.action',
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ //失败
	    	alert("操作失败");
	    },
	    success:function(data){ 
	    	echartsSex(data.msg);   	
	    } ,
	});
}

//统计学生年龄
function stuAge(){
	$.ajax({                
	    url: '/javaee/stuAge.action',
	    type: 'POST',
	    cache:false,
	    dataType:'json',
	    error: function(){ //失败
	    	alert("操作失败");
	    },
	    success:function(data){ 
	    	echartsAge(data.msg); 	    	
	    	
	    } ,
	});
}

// 百度图形统计插件 echarts

//obj -->二维数组   各班级人数统计
function echartsPerson(obj){
	var myChart = echarts.init(document.getElementById('show_content'));
	var myObj = [];
	var myName = [];
	for(var i=0; i<obj.length; i++){
		myObj.push({
			name: obj[i][0],
			value: obj[i][1]
		});
		myName.push({name: obj[i][0]});
	}
	
	var option = {
		    title : {
		        text: '各个班级人数统计',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}有 {c}人，占总人数 ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: myName,
		    },
		    series : [
		        {
		            name: '班级人数',
		            type: 'pie',
		            radius : '55%',
		            center : ['50%', '40%'],
		            data : myObj,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	myChart.setOption(option);	
}

function echartsAge(obj){
	var myChart = echarts.init(document.getElementById('show_content'));
	var myObj = [];
	var myName = [];
	for(var i=0; i<obj.length; i++){
		myObj.push({
			name: obj[i][0],
			value: obj[i][1]
		});
		myName.push({name: obj[i][0]});
	}
	
	var option = {
			title: {
                text: '学生年龄统计',
                x:'center'
            },
			tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>年龄在{b}岁的有{c}人，占总人数 ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data : myName
		    },
		    series: [
		        {
		            name:'学生年龄统计',
		            type:'pie',
		            radius: ['50%', '70%'],
		            avoidLabelOverlap: false,
		            label: {
		                normal: {
		                    show: false,
		                    position: 'center'
		                },
		                emphasis: {
		                    show: true,
		                    textStyle: {
		                        fontSize: '30',
		                        fontWeight: 'bold'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            data : myObj
		        }
		    ]
		};

	myChart.setOption(option);
}

function echartsSex(obj){
	var myChart = echarts.init(document.getElementById('show_content'));
	var myName = [];
	var myValue = [];
	for(var i=0; i<obj.length; i++){
		myName[i] = obj[i][0];
		myValue[i] = obj[i][1];
	}
	var option = {
            title: {
                text: '学生性别统计',
                x:'center'
            },
            tooltip: {},
            xAxis: {
                data: myName
            },
            yAxis: {},
            series: [{
                name: '性别',
                type: 'bar',
                data: myValue
            }]
        };
	myChart.setOption(option);
}



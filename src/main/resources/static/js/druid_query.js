var start = '2019-08-01 00';
var end = '2019-08-31 23';



var defaultColor = 'aliceblue';
var liStr = '';
liStr = liStr + '<li style="width:60%;">'
+'<select id="" ><option value="url">網址</option><option value="refer">參照網址</option></select>'
+'<select id="" ><option value="contain">包含</option><option value="not_contain">不包含</option><option value="equal">等於</option><option value="not_equal">不等於</option><option value="start">開頭</option><option value="not_start">開頭不是</option><option value="end">結尾</option><option value="not_end">結尾不是</option></select>'
+'<input type="text" id="" value="" style="width:50%;" placeholder = "請填寫查詢內容"/ ><input type="button" style="height:21px;display:none;" onclick="deleteCondition(this);" value="刪除此條件" ></li>';


var defaultTitleContent= '<span id="titleSpan">造訪過的網頁必須與這個群組中的「所有」規則相符</span>';

$(document).ready(function(){
	$(function() {
		  $('input[name="daterange"]').daterangepicker({
			  opens: 'left',
			  numberOfMonths   : 2,
			  hideIfNoPrevNext : true, // 此設定需搭配maxDate、minDate才能正常work
			  minDate          : '2019-08-01',
			  maxDate          : '2019-08-31',
			  locale: {
		            format: 'YYYY-MM-DD',
		        },
		        startDate: '2019-08-01',
	            endDate: '2019-08-31'
			 }, 
			function(start, end, label) {
		    	console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
		    	start = start.format('YYYY-MM-DD');
		    	end = end.format('YYYY-MM-DD');
		    	  console.log("start 1>>>"+start);
		  	});
	});
	
	$('input[name="daterange"]').on('apply.daterangepicker', function(ev, picker) {
		start = picker.startDate.format('YYYY-MM-DD 00');
		end = picker.endDate.format('YYYY-MM-DD 23');
	});
	$("#conditionUl").append('<ul style="background-color:'+defaultColor+'">'+defaultTitleContent+liStr+'<a href="javascript:void(0);" onclick="clickAddCondition(this);">且</a>'+'</ul>');
	defaultColor = 'cornsilk';
	
	$('#queryGroupSelect').on('change', function() {
		  changeQueryGroupSelect();
	});
	
});

function changeQueryGroupSelect(){
	if($("#queryGroupSelect").val() == 'query_any_group'){
		$("#titleSpan")[0].innerHTML = "造訪過的網頁必須與這個群組中的「所有」規則相符";
		$("#createButton").val("OR");
		$(".title_span").each(function() {
			this.innerHTML = '或與這個群組中的「任何」規則相符';
		});
	}else{
		$("#titleSpan")[0].innerHTML = "造訪過的網頁必須與這個群組中的「任何」規則相符";
		$("#createButton").val("且");
		$(".title_span").each(function() {
			this.innerHTML = '或與這個群組中的「所有」規則相符';
		});
	}
	
	$("a").each(function() {
		if($("#queryGroupSelect").val() == 'query_any_group'){
			if(this.innerHTML == "OR"){
				this.innerHTML = "且";
			}
		}else{
			if(this.innerHTML == "且"){
				this.innerHTML = "OR";
			}
		}
		
	});
	
	
	
}

function deleteCondition(obj){
	var ul = $($(obj).parent()).parent()[0];
	$(obj).parent().remove();
	if($(ul).children("li").length == 1){
		$(ul).children("li").children().last().css("display","none");
	}
}

//刪除條件群組
function deleteAllCondition(obj){
	$(obj).parent().remove()
	//重新繪製ul背景顏色
	defaultColor = 'aliceblue';
	$("#conditionUl").children("ul").each(function(index,val) {
		if(defaultColor == "aliceblue"){
			$(val).css("background-color",defaultColor);
			defaultColor = "cornsilk";
		}else if(defaultColor == "cornsilk"){
			$(val).css("background-color",defaultColor);
			defaultColor = "aliceblue";
		}
	});
}


function clickAddCondition(obj){
	$(obj).before(liStr);
	$($(obj).parent().children("li").children("input")).each(function(index,val) {
		if(val.value=="刪除此條件"){
			$(val).css("display","");
		}
	})
}

//點擊新增條件群組
function clickConditionButton(){
	var spanTitleContent = '';
	if($("#queryGroupSelect").val() == 'query_any_group'){
		spanTitleContent = '<span class="title_span">或與這個群組中的「所有」規則相符</span>';
	}else{
		spanTitleContent = '<span class="title_span">且與這個群組中的「任何」規則相符</span>';
	}
	var addType = "";
	if($("#queryGroupSelect").val() == 'query_any_group'){
		addType = "且";
	}else{
		addType = "OR";
	}
	$("#conditionUl").append('<ul style="background-color:'+defaultColor+'">'+spanTitleContent+liStr+'<a href="javascript:void(0);" onclick="clickAddCondition(this);">'+addType+'</a><a href="javascript:void(0);" onclick="deleteAllCondition(this);" style="float:right;margin-right:56%;">刪除此群組條件</a>'+'</ul>');
	if(defaultColor == 'aliceblue'){
		defaultColor = 'cornsilk';
	}else{
		defaultColor = 'aliceblue';
	}
}



var first_report_tbody_1 = false;
var first_report_tbody_2 = false;
var first_report_tbody_3 = false;
function dataQuery(){
	
	
	var dataStr = '';
	$("#conditionUl ul").each(function(ul_index,ul) {
		var jsonGroupStr = '';
		$(ul).children("li").each(function(li_index,li) {
			var jsonDetailStr = '';
			$(li).children().each(function(index,val) {
				if(index == 0){
					jsonDetailStr = jsonDetailStr + '"url_tyle":"'+$(val).val()+'",';
				}else if(index == 1){
					jsonDetailStr = jsonDetailStr + '"condition_tyle":"'+$(val).val()+'",';
				}else if(index == 2){
					jsonDetailStr = jsonDetailStr + '"condition_value":"'+$(val).val()+'"';
				}
			});
			
			if(jsonDetailStr != ''){
				jsonDetailStr = "{"+jsonDetailStr+"}";
				if(jsonGroupStr == ''){
					jsonGroupStr = jsonGroupStr+jsonDetailStr;
				}else{
					jsonGroupStr = jsonGroupStr+","+jsonDetailStr;	
				}
				
			}
		});
		if(dataStr == ''){
			dataStr = dataStr + '"group_'+ul_index+'":['+jsonGroupStr+']';
		}else{
			dataStr = dataStr + ',"group_'+ul_index+'":['+jsonGroupStr+']';
		}
	});
	dataStr = "{"+dataStr+"}";
	var jsonCondition = JSON.parse(dataStr);
	jsonCondition.query_type = $("#queryGroupSelect").val();
	var dataStr = JSON.stringify(jsonCondition);
//	console.log(jsonCondition);
//	console.log(alex);
	 $.blockUI();  
//	
//	 var today=new Date();// 定義日期物件
//     var yyyy = today.getFullYear();// 通過日期物件的getFullYear()方法返回年
//     var MM = today.getMonth()+1;// 通過日期物件的getMonth()方法返回年
//     var dd = today.getDate();// 通過日期物件的getDate()方法返回年
//     var hh=today.getHours();// 通過日期物件的getHours方法返回小時
//     var mm=today.getMinutes();// 通過日期物件的getMinutes方法返回分鐘
//     var ss=today.getSeconds();// 通過日期物件的getSeconds方法返回秒
//     console.log("start:"+yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss);
     
     
     
	$.ajax({
		url : "querydata",
		type:"POST",
        contentType: 'application/json;charset=utf-8',
        data: dataStr,
		dataType : 'json',
		success : function(respone) {
		},
		error : function(xtl) {
			alert("系統繁忙，請稍後再試！");
			$.unblockUI();
		}
	}).done(function(msg) {
		
		var today=new Date();// 定義日期物件
		var yyyy = today.getFullYear();// 通過日期物件的getFullYear()方法返回年
		var MM = today.getMonth()+1;// 通過日期物件的getMonth()方法返回年
		var dd = today.getDate();// 通過日期物件的getDate()方法返回年
		var hh=today.getHours();// 通過日期物件的getHours方法返回小時
		var mm=today.getMinutes();// 通過日期物件的getMinutes方法返回分鐘
		var ss=today.getSeconds();// 通過日期物件的getSeconds方法返回秒
		console.log("end:"+yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss);
		
		
////			if($("#querySelect").val() == 'type_1'){
////				if(layer == '1'){
////					$('#report_tbody').empty();
////					$('#myDataTalbe_title').empty();
////					
////					$('#report_tbody2').empty();
////					$('#myDataTalbe_title2').empty();
////					
////					$('#report_tbody3').empty();
////					$('#myDataTalbe_title3').empty();
////					
////					$('#myDataTalbe_title').append('<th>館別名稱</th><th>不重覆UUID數</th><th>曝光數</th><th>查詢下一層</th>');
////				}
////				if(layer == '2'){
////					$('#report_tbody2').empty();
////					$('#myDataTalbe_title2').empty();
////					
////					$('#report_tbody3').empty();
////					$('#myDataTalbe_title3').empty();
////					
////					$('#myDataTalbe_title2').append('<th>館別名稱</th><th>不重覆UUID數</th><th>曝光數</th><th>查詢下一層</th>');
////				}
////				if(layer == '3'){
////					$('#report_tbody3').empty();
////					$('#myDataTalbe_title3').empty();
////					
////					$('#myDataTalbe_title3').append('<th>館別名稱</th><th>不重覆UUID數</th><th>曝光數</th><th>查詢下一層</th>');
////				}
////				
////				var today=new Date();// 定義日期物件
////				var yyyy = today.getFullYear();// 通過日期物件的getFullYear()方法返回年
////				var MM = today.getMonth()+1;// 通過日期物件的getMonth()方法返回年
////				var dd = today.getDate();// 通過日期物件的getDate()方法返回年
////				var hh=today.getHours();// 通過日期物件的getHours方法返回小時
////				var mm=today.getMinutes();// 通過日期物件的getMinutes方法返回分鐘
////				var ss=today.getSeconds();// 通過日期物件的getSeconds方法返回秒
////				console.log("append start:"+yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss);
////				
////				
////				msg.forEach(obj => {
////					var tableDataStr = '';
////					Object.entries(obj).forEach(([key, value]) => {
////						tableDataStr = tableDataStr + '<td>'+`${value}`+'</td>';
////					});
////				        
////					if(layer == '1'){
////						$('#report_tbody').append('<tr>'+tableDataStr+'<td><button type="button" onclick="reportQuery(\'2\')">查看第二層</button></td></tr>');	
////					}
////					if(layer == '2'){
////						$('#report_tbody2').append('<tr>'+tableDataStr+'<td><button type="button" onclick="reportQuery(\'3\')">查看第三層</button></td></tr>');
////					}
////					if(layer == '3'){
////						$('#report_tbody3').append('<tr>'+tableDataStr+'<td>無下一層</td></tr>');
////					}  
////				});
////				
////				var today=new Date();// 定義日期物件
////				var yyyy = today.getFullYear();// 通過日期物件的getFullYear()方法返回年
////				var MM = today.getMonth()+1;// 通過日期物件的getMonth()方法返回年
////				var dd = today.getDate();// 通過日期物件的getDate()方法返回年
////				var hh=today.getHours();// 通過日期物件的getHours方法返回小時
////				var mm=today.getMinutes();// 通過日期物件的getMinutes方法返回分鐘
////				var ss=today.getSeconds();// 通過日期物件的getSeconds方法返回秒
////				console.log("append end:"+yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss);
////				
////			}
////		
////			if(!first_report_tbody_1 && layer == "1"){
////				$("#myDataTalbe").DataTable({
////					destroy: true, // Cannot reinitialise
////									// DataTable,解决重新加载表格内容问题
////					searching : false, // 關閉filter功能
////					ordering: false
////				});
////				first_report_tbody_1 = true;
////			}
////			
////			if(!first_report_tbody_2 && layer == "2"){
////				$("#myDataTalbe2").DataTable({
////					destroy: true, // Cannot reinitialise
////									// DataTable,解决重新加载表格内容问题
////					searching : false, // 關閉filter功能
////					ordering: false
////				});
////				first_report_tbody_2 = true;
////			}
////		
////			if(!first_report_tbody_3 && layer == "3"){
////				$("#myDataTalbe3").DataTable({
////					destroy: true, // Cannot reinitialise
////									// DataTable,解决重新加载表格内容问题
////					searching : false, // 關閉filter功能
////					ordering: false
////				});
////				first_report_tbody_2 = true;
//			}
			$.unblockUI();  
	});
}


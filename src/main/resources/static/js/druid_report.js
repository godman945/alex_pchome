﻿var start = '2019-08-01 00';
var end = '2019-08-31 23';
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
	
	
	
	
});
 

var first_report_tbody_1 = false;
var first_report_tbody_2 = false;
var first_report_tbody_3 = false;
function reportQuery(layer){
	
	 $.blockUI();  
	
	 var today=new Date();// 定義日期物件
     var yyyy = today.getFullYear();// 通過日期物件的getFullYear()方法返回年
     var MM = today.getMonth()+1;// 通過日期物件的getMonth()方法返回年
     var dd = today.getDate();// 通過日期物件的getDate()方法返回年
     var hh=today.getHours();// 通過日期物件的getHours方法返回小時
     var mm=today.getMinutes();// 通過日期物件的getMinutes方法返回分鐘
     var ss=today.getSeconds();// 通過日期物件的getSeconds方法返回秒
     console.log("start:"+yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss);
	var str = {
		"query_type":$("#querySelect").val(),
		"start":start,
		"end":end,
		"layer":layer
	};
	$.ajax({
		url : "data",
		type:"POST",
        contentType: 'application/json;charset=utf-8',
        data: JSON.stringify(str),
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
		
		
			if($("#querySelect").val() == 'type_1'){
				if(layer == '1'){
					$('#report_tbody').empty();
					$('#myDataTalbe_title').empty();
					
					$('#report_tbody2').empty();
					$('#myDataTalbe_title2').empty();
					
					$('#report_tbody3').empty();
					$('#myDataTalbe_title3').empty();
					
					$('#myDataTalbe_title').append('<th>館別名稱</th><th>不重覆UUID數</th><th>曝光數</th><th>查詢下一層</th>');
				}
				if(layer == '2'){
					$('#report_tbody2').empty();
					$('#myDataTalbe_title2').empty();
					
					$('#report_tbody3').empty();
					$('#myDataTalbe_title3').empty();
					
					$('#myDataTalbe_title2').append('<th>館別名稱</th><th>不重覆UUID數</th><th>曝光數</th><th>查詢下一層</th>');
				}
				if(layer == '3'){
					$('#report_tbody3').empty();
					$('#myDataTalbe_title3').empty();
					
					$('#myDataTalbe_title3').append('<th>館別名稱</th><th>不重覆UUID數</th><th>曝光數</th><th>查詢下一層</th>');
				}
				
				var today=new Date();// 定義日期物件
				var yyyy = today.getFullYear();// 通過日期物件的getFullYear()方法返回年
				var MM = today.getMonth()+1;// 通過日期物件的getMonth()方法返回年
				var dd = today.getDate();// 通過日期物件的getDate()方法返回年
				var hh=today.getHours();// 通過日期物件的getHours方法返回小時
				var mm=today.getMinutes();// 通過日期物件的getMinutes方法返回分鐘
				var ss=today.getSeconds();// 通過日期物件的getSeconds方法返回秒
				console.log("append start:"+yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss);
				
				
				msg.forEach(obj => {
					var tableDataStr = '';
					Object.entries(obj).forEach(([key, value]) => {
						tableDataStr = tableDataStr + '<td>'+`${value}`+'</td>';
					});
				        
					if(layer == '1'){
						$('#report_tbody').append('<tr>'+tableDataStr+'<td><button type="button" onclick="reportQuery(\'2\')">查看第二層</button></td></tr>');	
					}
					if(layer == '2'){
						$('#report_tbody2').append('<tr>'+tableDataStr+'<td><button type="button" onclick="reportQuery(\'3\')">查看第三層</button></td></tr>');
					}
					if(layer == '3'){
						$('#report_tbody3').append('<tr>'+tableDataStr+'<td>無下一層</td></tr>');
					}  
				});
				
				var today=new Date();// 定義日期物件
				var yyyy = today.getFullYear();// 通過日期物件的getFullYear()方法返回年
				var MM = today.getMonth()+1;// 通過日期物件的getMonth()方法返回年
				var dd = today.getDate();// 通過日期物件的getDate()方法返回年
				var hh=today.getHours();// 通過日期物件的getHours方法返回小時
				var mm=today.getMinutes();// 通過日期物件的getMinutes方法返回分鐘
				var ss=today.getSeconds();// 通過日期物件的getSeconds方法返回秒
				console.log("append end:"+yyyy+"-"+MM+"-"+dd+" "+hh+":"+mm+":"+ss);
				
			}
		
			if(!first_report_tbody_1 && layer == "1"){
				$("#myDataTalbe").DataTable({
					destroy: true, // Cannot reinitialise
									// DataTable,解决重新加载表格内容问题
					searching : false, // 關閉filter功能
					ordering: false
				});
				first_report_tbody_1 = true;
			}
			
			if(!first_report_tbody_2 && layer == "2"){
				$("#myDataTalbe2").DataTable({
					destroy: true, // Cannot reinitialise
									// DataTable,解决重新加载表格内容问题
					searching : false, // 關閉filter功能
					ordering: false
				});
				first_report_tbody_2 = true;
			}
		
			if(!first_report_tbody_3 && layer == "3"){
				$("#myDataTalbe3").DataTable({
					destroy: true, // Cannot reinitialise
									// DataTable,解决重新加载表格内容问题
					searching : false, // 關閉filter功能
					ordering: false
				});
				first_report_tbody_2 = true;
			}
			$.unblockUI();  
	});
}


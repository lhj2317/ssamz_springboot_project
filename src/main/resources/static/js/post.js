let postObject = {
	//init() 함수 선언
	init: function(){
		let _this = this;
		 
		$("#btn-insert").on("click", ()=> {
			_this.insertPost();
		});	
		
		$("#btn-update").on("click", ()=> {
			_this.updatePost();
		});	
		
		$("#btn-delete").on("click", ()=> {
			_this.deletePost();
		});
	},
	
	insertPost: function(){
		alert("포스트 등록 요청됨");
		let post = {
			title : $("#title").val(),
			content : $("#content").val()
		}		
		
		$.ajax({
			type: "POST",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"					
		}).done(function(response) {
			console.log("Response status:", response["status"]);
    		console.log("Response data:", response["data"]);
			
			let status = response["status"];
			if(status == 200) {
				let message = response["data"];
				alert(message);
				window.location.href = "/"; 
			} else {
				let warn = "";
				let errors = response["data"];
				if(errors.title != null) warn = warn + errors.title + "\n";
				if(errors.content != null) warn = warn + errors.content;
				alert(warn);
			}
		}).fail(function(error) { 
			console.log("Error status:", error.status);
    		console.log("Error response:", error.responseJSON);
			let message = error["data"];
			alert("문제 발생 : " + message);
		});
	},
	
	updatePost : function() {
		alert("포스트 수정 요청됨");
		let post = {
			id : $("#id").val(),
			title : $("#title").val(),
			content : $("#content").val()
		}
 
		$.ajax({
			type: "PUT",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let message = response["data"];
			alert(message);
			window.location.href = "/"; 
		}).fail(function(error) {
			let message = error["data"];
			alert("문제 발생 : " + message);
		});
	},

	
	deletePost: function(){
		alert("포스트 삭제 요청됨"); 
		let id = $("#id").text();
		
		$.ajax({
			type: "DELETE",                        //요청방식
			url: "/post/" + id,                    //요청경로
			contentType: "application/json; charset=utf-8" 
		}).done(function(response){ 
			let message = response["data"];
			alert(message);
			window.location.href = "/"; 
		}).fail(function(error){ 
			let message = error["data"];
			alert("문제발생 : " + message);
		});
	},
}
 
postObject.init();
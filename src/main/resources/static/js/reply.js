let replyObject = { 
	init: function(){
		let _this = this;
		 
		$("#btn-save-reply").on("click", ()=> {
			_this.insertReply();
		});	 		
	},
	
	insertReply: function(){
		alert("댓글 등록 요청됨"); 
		
		let id = $("#postId").val();
		
		let reply = {
			content: $("#reply-content").val()
		}
		
		$.ajax({
			type: "POST",                        //요청방식
			url: "/reply/" + id,                 //요청경로
			data: JSON.stringify(reply),          //user 객체를 JSON 형식으로 변환 
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
	
	deleteReply: function(postId, replyId){
		alert("댓글 삭제 요청됨"); 		 
				
		$.ajax({
			type: "DELETE",                        //요청방식
			url: "/reply/" + replyId,              //요청경로
		}).done(function(response){ 
			let message = response["data"];
			alert(message);
			window.location.href = "/post/" + postId; 
		}).fail(function(error){ 
			let message = error["data"];
			alert("문제발생 : " + message);
		});
	},
	
}
 
replyObject.init();
$(document).ready(function() {

	// ajax 전송
	$.ajax('/bitschool/ajax/selectUsers.contact', {
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			
			if(data.result == 'success'){
			var users = data.users;	
			$('#user_id').val(users.id);
			$('#name').val(users.name);
			$('#email').val(users.email);
			$('#password').val(users.password);
			$('#passwordconfirm').val(users.password);
		}else{
			
			$(location).attr('href','index.html');
			alert('로그인을 해주세요!');
			}
		}
			
	});
	
});

$(document).ready(function() {

	$('#join_btn').click(function() {

		// ajax 전송
		$.ajax('/bitschool/ajax/insertUsers.contact', {
			type : 'POST',
			dataType : 'json',
			
			data : {
				name : $('#input_name').val(),
				email : $('#input_email').val(),
				password : $('#input_password').val()
			},
			success : function(data) {
				if (data.result == 'success') {
					alert(data.users.name + '님 반갑습니다.');
					$(location).attr('href','icontacts.html');
				} else if (data.result == 'nullEmail') {
					alert('이메일은 필수 항목입니다.')
				} else {
					alert('중복된 이메일 주소입니다.');
				}
			}
		});

	});

});
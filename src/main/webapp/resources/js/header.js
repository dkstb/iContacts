$(document).ready(function() {

	// 로그인 체크
	$.ajax('/bitschool/ajax/checkUsers.contact', {
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			if (data.result == 'login') {
				$('#header_login').attr('style', 'display:none');
				$('#header_signup').attr('style', 'display:none');
				$('#header_account').removeAttr('style');
				$('#account_name').text(data.users.name + ' 님');
			} else if (data.result == 'logout'){
				$('#header_account').attr('style', 'display:none');
				$('#header_login').removeAttr();
				$('#header_signup').removeAttr();
			} else {
				alert('회원님의 로그인 정보가 DB에 존재하지 않습니다. 브라우저를 재실행 하시고, 다시 가입해주세요.');
			}
		}
	});
	
	$('#header_login_btn').click(function(){
		
		// 로그인 정보 서버 전송
		$.ajax('/bitschool/ajax/loginUsers.contact', {
			type: 'POST',
			dataType: 'json',
			async: false,
			data: {
				email: $('#header_email').val(),
				password: $('#header_password').val(),
			},					
			success:function(data){
				if(data.result=='success'){
					location.reload();
				} else if(data.result=='fail') {
					window.alert('비밀번호가 일치하지 않습니다.');
					$('#password').addClass("ui-state-error");
					updateTips('비밀번호가 일치하지 않습니다.', login_password_tips);
					$('#password').focus();
				}else if(data.result == 'noInformation') {
					window.alert('가입정보가 없습니다.');
				} else {
					$("#dialog-login").dialog("close");
					window.alert('오류 발생, 다시 시도해 주세요');
				}
			}
		});
	});

});
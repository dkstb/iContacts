$(document).ready(function(){
        
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
					$(location).attr('href','icontacts.html');
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
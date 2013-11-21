$(document).ready(function() {

	// 로그인 체크
	$.ajax('/bitschool/ajax/checkUsers.contact', {
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			if (data.result == 'login') {
				$('#header_login').attr('style', 'display:none');
				$('#header_signup').attr('style', 'display:none');
				$('#header_icontact').removeAttr('style');
				$('#header_account').removeAttr('style');
				$('#account_name').text(data.users.name + ' 님 ');
			} else if (data.result == 'logout'){
				$('#header_account').attr('style', 'display:none');
				$('#header_icontact').attr('style', 'display:none');
				$('#header_login').removeAttr();
				$('#header_signup').removeAttr();
			} else {
				alert('회원님의 로그인 정보가 DB에 존재하지 않습니다. 브라우저를 재실행 하시고, 다시 가입해주세요.');
			}
		}
	});

});
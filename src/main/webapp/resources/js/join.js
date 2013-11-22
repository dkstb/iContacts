$(document).ready(function() {
	
	// 변수 선언
	var name = $('#input_name'), email = $('#input_email'), 
		password = $('#input_password'), confirmPassword = $('#confirm_password'),
		nameHelp = $('#name-help'), emailHelp = $('#email-help'),
		passwordHelp = $('#password-help'), confirmPasswordHelp = $('#confirm_password-help'),
		nameValid = false, emailValid = false, passwordValid = false,
		confirmPasswordValid = false; 
	
	// 이름 유효성 체크 (키업)
	name.keyup(function(){
		var bValid = true;
		bValid = bValid && checkLength(nameHelp, name, "이름", 2, 30);
		bValid = bValid
				&& checkRegexp(nameHelp, name, /^([가-힣a-zA-Z\s\-])+$/i,
						"이름에는 한글, 영문만 들어갑니다.");

		if (bValid) {
			nameValid=true;
			trueWork();
		}else{
			$('#join_btn').attr('disabled', 'disabled');
		}
	});
	
	// 이름 유효성 체크(포커스아웃)
	name.blur(function(){
		immdUpdateTips('', nameHelp);
	});
	
	// 이메일(아이디) 유효성 체크 (포커스인)
	email.keyup(function(){
		var bValid = true;
			bValid = bValid && checkLength(emailHelp, email, "이메일", 5, 50);
			bValid = bValid
				&& checkRegexp(emailHelp,
						email,
						/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
						"예) contact@iContacts.com");

		if (bValid) {
			// ajax loading image
			trueWork();
			
			// 이메일  정보 서버 전송
			$.ajax('/bitschool/ajax/checkEmail.contact', {
				type: 'POST',
				dataType: 'json',
				data: {
					email: email.val()
				},					
				success:function(data){
					if(data.result=='success'){
						updateTips("가입이 가능 합니다.", emailHelp);
						emailValid=true;
					} else {
						updateTips('중복된 아이디(이메일)가 있습니다.', emailHelp);
						$('#join_btn').attr('disabled', 'disabled');
					}
				}
			});
		}else{
			$('#join_btn').attr('disabled', 'disabled');
		}
	});
	
	// 이메일(아이디) 유효성 체크 (포커스아웃)
	email.blur(function(){
		var bValid = true;
			bValid = bValid && checkLength(emailHelp, email, "이메일", 5, 50);
			bValid = bValid
				&& checkRegexp(emailHelp,
						email,
						/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
						"예) contact@iContacts.com");

		if (bValid) {
			// ajax loading image
			trueWork();
			
			// 이메일  정보 서버 전송
			$.ajax('/bitschool/ajax/checkEmail.contact', {
				type: 'POST',
				dataType: 'json',
				data: {
					email: email.val()
				},					
				success:function(data){
					if(data.result=='success'){
						immdUpdateTips('', emailHelp);
						emailValid=true;
					} else {
						updateTips('중복된 아이디(이메일)가 있습니다.', emailHelp);
						$('#join_btn').attr('disabled', 'disabled');
					}
				}
			});
		}else{
			$('#join_btn').attr('disabled', 'disabled');
		}
	});
	
	// 패스워드 유효성 체크 (포커스인)
	password.keyup(function(){
		var bValid = true;
		bValid = bValid
		&& checkLength(passwordHelp, password, "비밀번호", 4, 16);
		
		bValid = bValid
		&& checkRegexp(passwordHelp, password,
				/^([0-9a-zA-Z])+$/,
				"비밀번호는 영문과 숫자로 이루어져야 합니다. : a-z 0-9");
		
		if (bValid) {
			passwordValid = true;
			trueWork();
		}else{
			$('#join_btn').attr('disabled', 'disabled');
		}
	});
	
	// 패스워드 유효성 체크 (포커스아웃)
	password.blur(function(){
		immdUpdateTips('', passwordHelp);
	});
	
	// 비밀번호 일치 체크 (포커스 인)
	confirmPassword.keyup(function(){
		var bValid = true;
			bValid = bValid	&& checkpassword(password, confirmPassword);
		
		if (bValid) {
			confirmPasswordValid = true;
			trueWork();
		}else{
			$('#join_btn').attr('disabled', 'disabled');
		}
	});
	
	// 비밀번호 일치 체크 (포커스 아웃)
	confirmPassword.blur(function(){
		immdUpdateTips('', confirmPasswordHelp);
	});
	
	// 길이 유효성 체크
	function checkLength(helpLocation, name, text, min, max) {
		if (name.val().length > max || name.val().length < min) {
			updateTips(text + "의 길이는 " + min + "~"
					+ max + " 사이입니다.", helpLocation);
			return false;
		} else {
			return true;
		}
	}
	
	// 정규표현식 유효성 체크
	function checkRegexp(helpLocation, name, regexp, text) {
		if (!(regexp.test(name.val()))) {
			updateTips(text,helpLocation);
			return false;
		} else {
			updateTips('정상입니다', helpLocation);
			return true;
		}
	}
	
	// 유효성 성공 안내
	function trueWork(){
		// 모든 필드의 유효성 체크 통과 시 로그인 버튼 활성화
		if (nameValid && emailValid && passwordValid && confirmPasswordValid) {
			$('#join_btn').removeAttr('disabled');
		}else{
			$('#join_btn').attr('disabled', 'disabled');
		}
	}
	
	// 유효성 팁
	function updateTips(text, helpLocation) {
		helpLocation.text(text);
	}
	// 효과 즉시 제거
	function immdUpdateTips(text, helpLocation){
		helpLocation.text(text).remove();
	}
	
	// 비밀번호 일치 확인 함수
	function checkpassword(password, confirmPassword) {
		console.log('비밀번호 : ' + password.val());
		console.log('비밀번호 확인 : ' + confirmPassword.val());
		if (password.val() != confirmPassword.val()) {
			updateTips("비밀번호가 일치하지 않습니다.", confirmPasswordHelp);
			return false;
		} else {
			updateTips("비밀번호가 일치합니다.", confirmPasswordHelp);
			return true;
		}
	}

	// 회원 가입 버튼 클릭 시 가입 정보 전송
	$('#join_btn').click(function() {

		// ajax 전송
		$.ajax('/bitschool/ajax/insertUsers.contact', {
			type : 'POST',
			dataType : 'json',
			
			data : {
				name : name.val(),
				email : email.val(),
				password : password.val()
			},
			success : function(data) {
				if (data.result == 'success') {
					alert(data.users.name + '님 반갑습니다.');
					$(location).attr('href','icontacts.html');
				} else if (data.result == 'nullEmail') {
					alert('이메일은 필수 항목입니다.');
				} else {
					alert('중복된 이메일 주소입니다.');
				}
			}
		});

	});

});
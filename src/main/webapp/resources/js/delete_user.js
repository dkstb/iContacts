$(document).ready(function() {

	$('#delete').click(function() {
		// ajax 전송
		$.ajax('/bitschool/ajax/deleteUser.contact', {
			type : 'POST',
			dataType : 'json',
			data : {
				id : $('#user_id').val()
			},
			success : function(data) {
				if (data.result == 'success') {
					var result = confirm($('#name').val()+'을 정말로 삭제하겠습니까?'); 
					if(result){
					alert($('#name').val() + '님이 삭제되었습니다.');
					$(location).attr('href','index.html');
					}else{
						alert('삭제가 실패했습니다.')
					}
				} else {
					alert('삭제가 실패했습니다.');
				}
			}
		});
	});
});
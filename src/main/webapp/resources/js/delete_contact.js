$(document).ready(function() {

	$('#delete_contact').click(function() {
		// ajax 전송
		$.ajax('/bitschool/ajax/deleteContact.contact', {

			type : 'POST',
			dataType : 'json',
			data : {
				id : $('#contact_id').val()
			},
			success : function(data) {
				if (data.result == 'success') {
					alert($('#contact_name').val() + '님이 삭제되었습니다.');
					location.reload();
				} else {
					alert('주소록 삭제가 실패했습니다.');
				}
			}
		});
	});
});
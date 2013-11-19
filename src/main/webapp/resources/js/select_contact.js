$(document).ready(function() {
	
	// 상세 주소 조회
	$(document).on('click', '.contact_id', function(){
		console.log('주소록 아이디 : ' + $(this).attr('contact_id'));
		var contact_id = $(this).attr('contact_id');
		
		// ajax 전송
		$.ajax('/bitschool/ajax/selectContact.contact', {
			type : 'POST',
			dataType : 'json',
			data : {
				id : contact_id
			},
			success : function(data) {
				var contacts = data.contacts;
				$('#contact_name').val(contacts.name);
				$('#contact_phone_num').val(contacts.phoneNum);
				$('#contact_work').val(contacts.work);
				$('#contact_email').val(contacts.email);
				$('#contact_homepage').val(contacts.webPage);
				$('#contact_addr').val(contacts.homeAddr);
				$('#contact_memo').val(contacts.memo);
			}
		});
	});
	
});

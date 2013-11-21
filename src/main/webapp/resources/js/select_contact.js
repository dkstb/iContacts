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
				$('#contact_id').val(contacts.id);
				$('#contact_name').val(contacts.name);
				$('#contact_phone_num').val(contacts.phoneNum);
				$('#contact_work').val(contacts.work);
				$('#contact_email').val(contacts.email);
				$('#contact_homepage').val(contacts.webPage);
				$('#contact_addr').val(contacts.homeAddr);
				$('#contact_memo').val(contacts.memo);
			}
		});
		
		// edit, delete 버튼 생성
		$('#insert_contact').remove();
		$('#update_contact').remove();
		$('#delete_contact').remove();
		$('#detail_contact').append('<button id="delete_contact" type="button" class="btn btn-danger pull-right">Delete</button>')
		.append('<button id="update_contact" type="button" class="btn btn-info pull-right">Edit</button>');
	});
	
	// 주소록 추가 버튼 클릭시 save 버튼 생성
	$(document).on('click', '#plus_contact', function(){
		$('#contact_name').val('');
        $('#contact_phone_num').val('');
        $('#contact_email').val('');
        $('#contact_work').val('');
        $('#contact_addr').val('');
        $('#contact_homepage').val('');
        $('#contact_memo').val('');
		$('#insert_contact').remove();
		$('#update_contact').remove();
		$('#delete_contact').remove();
		$('#detail_contact').append('<button id="insert_contact" type="button" class="btn btn-info pull-right">Save</button>');
	});
	
});

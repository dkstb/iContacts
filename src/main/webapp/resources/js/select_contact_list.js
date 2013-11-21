$(document).ready(function() {
	
	var contact_id = [];
	
	// 주소록 조회
	$.ajax('/bitschool/ajax/selectContactList.contact', {
		type : 'GET',
		dataType : 'json',
		async : false,
		success : function(data) {
			if (data.result=='success') {
				var contactList = data.contactList;
				for (var i = 0; i < contactList.length; i++) {
					contact_id[i] = contactList[i].id; 
					$('<tr>').append($('<td>')
							.addClass('contact_id')
							.attr('contact_id', contactList[i].id)
							.text(contactList[i].name))
							.appendTo('#contact_list');
				}
			} else if(data.result=='logout') {
				alert('로그인을 해주세요');
				$(location).attr('href','index.html');
			}
		}
	});
	
	// 주소록 리스트 첫줄 상세 주소 조회
	$.ajax('/bitschool/ajax/selectContact.contact', {
		type : 'POST',
		dataType : 'json',
		data : {
			id : contact_id[0]
		},
		async : false,
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
			
			// edit, delete 버튼 생성
			$('#insert_contact').remove();
			$('#update_contact').remove();
			$('#delete_contact').remove();
			$('#detail_contact').append('<button id="delete_contact" type="button" class="btn btn-danger pull-right">Delete</button>')
			.append('<button id="update_contact" type="button" class="btn btn-info pull-right">Save</button>');
		}
	});
	
});

$(document).ready(function() {
	
	// 주소록 조회
	$.ajax('/bitschool/ajax/selectContactList.contact', {
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var contactList = data.contactList;

			for (var i = 0; i < contactList.length; i++) {
				$('<tr>').append($('<td>')
						.addClass('contact_id')
						.attr('contact_id', contactList[i].id)
						.text(contactList[i].name))
				.appendTo('#contact_list');
			}
		}
	});
	
});

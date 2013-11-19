$(document).ready(function() {
	
	// ajax 전송
	$.ajax('/bitschool/ajax/selectContactList.contact', {
		type : 'GET',
		dataType : 'json',
		success : function(data) {
			var contacts = data.contacts;

			for (var i = 0; i < contacts.length; i++) {
				$('<tr>').append($('<td>').text(contacts[i].name)).appendTo(
						'#contact_list');
				console.log('이름 : ' + contacts[i].name);
			}
		}
	});
});

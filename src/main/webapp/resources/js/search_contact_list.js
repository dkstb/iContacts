$(document).ready(function() {
	
	var contact_id = [];
	
	$('#search_name').keyup(function(){
		//ajax 전송
		$.ajax('/bitschool/ajax/searchContactList.contact', {
			
			type : 'POST',
			dataType : 'json',
 	 		data: {
 	 			name : $('#search_name').val(),
 	 		} ,
 	 		 success:function(data){
                 if(data.result=='success'){
                	 var contactList = data.contactList;
                	 $('#contact_list tr').remove();
	     				for (var i = 0; i < contactList.length; i++) {
	     					contact_id[i] = contactList[i].id; 
	     					$('<tr>').append($('<td>')
	     							.addClass('contact_id')
	     							.attr('contact_id', contactList[i].id)
	     							.text(contactList[i].name))
	     							.appendTo('#contact_list');
	     				}
     				
     				// 주소록 리스트 첫줄 상세 주소 조회
     				$.ajax('/bitschool/ajax/selectContact.contact', {
     					type : 'POST',
     					dataType : 'json',
     					data : {
     						id : contact_id[0]
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
     						
     						// save, delete 버튼 생성
     						$('#insert_contact').remove();
     						$('#update_contact').remove();
     						$('#delete_contact').remove();
     						$('#detail_contact').append('<button id="delete_contact" type="button" class="btn btn-danger pull-right"><i class="fa fa-trash-o fa-lg"></i> Delete</button>')
     						.append('<button id="update_contact" type="button" class="btn btn-success pull-right"><i class="fa fa-check"></i> Save</button>');
     					}
     				});
                 } else {
     				$('#insert_contact').remove();
     				$('#update_contact').remove();
     				$('#delete_contact').remove();
     				$('#detail_contact').append('<button id="insert_contact" type="button" class="btn btn-success pull-right"><i class="fa fa-check"></i> Save</button>');
     			}
 	 		 }
		});
	});
	
	$('#search_btn').click(function(){
		//ajax 전송
		$.ajax('/bitschool/ajax/searchContactList.contact', {
			
			type : 'POST',
			dataType : 'json',
 	 		data: {
 	 			name : $('#search_name').val(),
 	 		} ,
 	 		 success:function(data){
                 if(data.result=='success'){
                	 var contactList = data.contactList;
                	 $('#contact_list tr').remove();
	     				for (var i = 0; i < contactList.length; i++) {
	     					contact_id[i] = contactList[i].id; 
	     					$('<tr>').append($('<td>')
	     							.addClass('contact_id')
	     							.attr('contact_id', contactList[i].id)
	     							.text(contactList[i].name))
	     							.appendTo('#contact_list');
	     				}
     				
     				// 주소록 리스트 첫줄 상세 주소 조회
     				$.ajax('/bitschool/ajax/selectContact.contact', {
     					type : 'POST',
     					dataType : 'json',
     					data : {
     						id : contact_id[0]
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
     						
     						// save, delete 버튼 생성
     						$('#insert_contact').remove();
     						$('#update_contact').remove();
     						$('#delete_contact').remove();
     						$('#detail_contact').append('<button id="delete_contact" type="button" class="btn btn-danger pull-right"><i class="fa fa-trash-o fa-lg"></i> Delete</button>')
     						.append('<button id="update_contact" type="button" class="btn btn-success pull-right"><i class="fa fa-check"></i> Save</button>');
     					}
     				});
                 } else {
     				$('#insert_contact').remove();
     				$('#update_contact').remove();
     				$('#delete_contact').remove();
     				$('#detail_contact').append('<button id="insert_contact" type="button" class="btn btn-success pull-right"><i class="fa fa-check"></i> Save</button>');
     			}
 	 		 }
		});
	});
});
		

			


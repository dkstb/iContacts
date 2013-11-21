$(document).ready(function() {
	
	$(document).on('click',  '#delete_contact', function(){
		// ajax 전송
		
		var result = confirm('정말로 삭제하겠습니까?');
		if(result){
			$.ajax('/bitschool/ajax/deleteContact.contact', {
	
				type : 'POST',
				dataType : 'json',
				data : {
					id : $('#contact_id').val()
				},
				success : function(data) {
					
					alert('정상적으로 삭제되었습니다.');
					location.reload();
					
				}
			});
			
			
		}else{
			alert($('삭제가 취소되었습니다.'));

		}
		
	});
		
	});

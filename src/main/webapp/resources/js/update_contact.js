$(document).ready(function(){
        
	$(document).on('click', '#update_contact', function(){
            // ajax 전송
            $.ajax('/bitschool/ajax/updateContact.contact', {
            		
                    type: 'POST',
                    dataType: 'json',
                    data: {
                            name : $('#contact_name').val(),
                            phoneNum : $('#contact_phone_num').val(),
                            email : $('#contact_email').val(),
                            work : $('#contact_work').val(),
                            homeAddr : $('#contact_addr').val(),
                            webPage : $('#contact_homepage').val(),
                            memo : $('#contact_memo').val(),
                            id : $('#contact_id').val()
                    },                                        
                    success:function(data){
                            if(data.result=='success'){
                                    alert($('#contact_name').val() + '님이 수정되었습니다.');
                                    location.reload();
                            } else {
                                    alert('주소록 수정이 실패했습니다.');
                            }
                    }
            });
            
    });
    
        
});
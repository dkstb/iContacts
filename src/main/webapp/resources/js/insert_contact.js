$(document).ready(function(){
	
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
		$('#detail_contact').append('<button id="insert_contact" type="button" class="btn btn-success pull-right"><i class="fa fa-check"></i> Save</button>');
	});
        
	$(document).on('click', '#insert_contact', function(){
            
            // ajax 전송
            $.ajax('/bitschool/ajax/insertContact.contact', {
                    type: 'POST',
                    dataType: 'json',
                    data: {
                            name : $('#contact_name').val(),
                            phoneNum : $('#contact_phone_num').val(),
                            email : $('#contact_email').val(),
                            work : $('#contact_work').val(),
                            homeAddr : $('#contact_addr').val(),
                            webPage : $('#contact_homepage').val(),
                            memo : $('#contact_memo').val()
                    },                                        
                    success:function(data){
                            if(data.result=='success') {
                                alert($('#contact_name').val() + '님이 추가되었습니다.');
                                location.reload();
                            } else if (data.result=='nullname') {
                            	alert('이름은 필수 항목입니다.');
                            } else {
                                alert('주소록 등록이 실패했습니다.');
                            }
                    }
            });
            
    });
        
});
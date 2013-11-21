$(document).ready(function(){

        $('#save').click(function(){
            // ajax 전송
            $.ajax('/bitschool/ajax/updateUser.contact', {
            		
                    type: 'POST',
                    dataType: 'json',
                    data: {
                            id : $('#user_id').val(),
                            name : $('#name').val(),
                            email : $('#email').val(),
                            password : $('#password').val()
                    },                                        
                    success:function(data){
                            if(data.result=='success'){
                                alert($('#name').val() + '님이 수정되었습니다.');
                                $(location).attr('href','icontacts.html');
                            } else if(data.result == 'nullEmail') {
                            	alert('이메일은 필수 항목입니다.');
                            } else {
                                alert('동일한 이메일이 있습니다.');
                            }
                    }
            });
            
    });
        
});
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
                            } else {
                                    alert('주소록 수정이 실패했습니다.');
                            }
                    }
            });
            
    });
        
});
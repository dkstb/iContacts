$(document).ready(function(){
        
        $('#login_btn').click(function(){
                alert('email' + $('#input_email').val());
                alert('password' + $('#input_password').val());
                
                // ajax 전송
                $.ajax('/bitschool/ajax/getUsers.contact', {
                        type: 'POST',
                        dataType: 'json',
                        data: {
                                email : $('#input_email').val(),
                                password : $('#input_password').val()
                        },                                        
                        success:function(data){
                                if(data.result=='success'){
                                        alert('로그인 되었습니다.');
                                } else {
                                        alert('로그인 실패했습니다.');
                                }
                        }
                });
                
        });
        
});
$(document).ready(function(){
        
        $('#join_btn').click(function(){
                alert('name' + $('#input_name').val());
                alert('email' + $('#input_email').val());
                alert('password' + $('#input_password').val());
                
                // 회원가입 정보 서버 전송
                $.ajax('/bitschool/ajax/insertUsers.contact', {
                        type: 'POST',
                        dataType: 'json',
                        data: {
                                name : $('#input_name').val(),
                                email : $('#input_email').val(),
                                password : $('#input_password').val()
                        },                                        
                        success:function(data){
                                if(data.result=='success'){
                                        alert('회원가입이 완료되었습니다.');
                                } else {
                                        alert('회원가입에 실패했습니다.');
                                }
                        }
                });
                
        });
        
});
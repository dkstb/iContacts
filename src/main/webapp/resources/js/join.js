$(document).ready(function(){
        
        $('#join_btn').click(function(){
                alert('name' + $('#input_name').val());
                alert('email' + $('#input_email').val());
                alert('password' + $('#input_password').val());
                
                // ȸ������ ���� ���� ����
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
                                        alert('ȸ�������� �Ϸ�Ǿ����ϴ�.');
                                } else {
                                        alert('ȸ�����Կ� �����߽��ϴ�.');
                                }
                        }
                });
                
        });
        
});
$(document).ready(function(){
        
        $('#header_logout_btn').click(function(){
               
                // ajax 전송
                $.ajax('/bitschool/ajax/logout.contact', {
                        type: 'POST',
                        dataType: 'json',        
                        success:function(data){
                                if(data.result=='success'){
                                        alert('안전하게 로그아웃 되었습니다.');
                                        $(location).attr('href','index.html');
                                        
                                } else {
                                        alert('로그아웃 실패했습니다.');
                                }
                        }
                });
                
        });
        
});
function loginOrLogOutCheck(sessionIdValue){
    $('#LoginArea').empty()
    if(sessionIdValue===null){
        $('#LoginArea').append('<a onclick="goLogin()">로그인</a>')
    }else{
        $('#LoginArea').append('<a onclick="goLogOut()">로그아웃</a>')
    }
}

function goLogin(){
    window.location.href = "/Login";
}

function goLogOut(){
    $.ajax({
        type: "GET",
        url: "/Login/logout",
        success: function (logoutResult) {
            alert(logoutResult)
            window.location.reload();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.")
        }
    });
}
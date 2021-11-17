$('#SendJoinCode').click(function () {
    // ajax 통신
    $.ajax({
        type: "POST",                  // HTTP method type(GET, POST) 형식이다.
        url: "/Join/emailCertification",      // 컨트롤러에서 대기중인 URL 주소이다.
        data: $("#email"),              // 데이터
        success: function (res) {
            if (res === true) {
                alert("메일함을 확인해 주세요")
            }else{
                alert("올바른 메일형식을 맞춰주세요..")
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
            alert("통신 실패.")
        }
    });
});


$('#CodeComparison').click(function () {
    $.ajax({
        type: "POST",
        url: "/Join/compare",
        async:false, // success로 받아온 값을 전역변수에 저장하기 위한 async:false; ajax는 기본적으로 비동기로 작동하지만, 동기식으로 작동하게끔 변환한것
        data: $("#joinCode"),
        success: function (compareResult) {
            if (compareResult == 1) {
                alert("인증완료")
            } else {
                alert("인증 실패")
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.")
        }
    });
});
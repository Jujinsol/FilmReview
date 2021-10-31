window.DropFunc = function (OriName) {
    const sendData = {
        'deleteName': OriName
    }
    $.ajax({
        type: "Delete",
        url: "/DeleteMovie/DeleteFunc",
        data: sendData,
        success: function (result) {
            if(result>=1){
                alert("삭제 성공")
                location.href='/';
            }else{
                alert("삭제 실패")
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패." + XMLHttpRequest + textStatus + errorThrown);
        }
    });
};
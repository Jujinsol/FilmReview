$('#reviewUpload').click(function () {
    // json 형식으로 데이터 set
    var params = {
        movieReivew : $("#movieReivew").val()
        ,moviePoint : $("#moviePoint").val()
    }
    $.ajax({
        type:"GET",
        url: "/EachMovie/reviewUpload",
        data:params,
        success: function (result) {
            if(result ===  1){
                alert("리뷰업로드가 완료되었습니다.");
            }else{
                alert("리뷰업로드가 실패했습니다.");
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.");
        }
    });
});


// 비어있는지 확인
const isEmpty = function (value) {
    if (value == "null" || value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)) {
        return true
    } else {
        return false
    }
};

var reviewMenIds = []; // 리뷰등록한유저 id를 갖고잇는 객체

if (getReview.length === 1) { // 리뷰전체보기
    $("#reviewsInfo").append('<div>등록된 리뷰가 없습니다</div>')
} else {
    $("#reviewsInfo").empty();
    for (let i = 1; i < getReview.length; i++) {
        $("#reviewsInfo").append('리뷰단사람 id : <div>' + getReview[i].reviewUser + '</div>')
        $("#reviewsInfo").append('영화 점수 : <div>' + getReview[i].moviePoint + '</div>')
        $("#reviewsInfo").append('리뷰 본문 : <div>' + getReview[i].movieReivew + '</div>')
        $("#reviewsInfo").append('<button onclick="deleteReview(\'' + sessionId + '\',\'' + photoOriName + '\');" >리뷰 삭제하기</button>')
        reviewMenIds.push(getReview[i].reviewUser);
    }
}

window.deleteReview = function (reviewUser, photoOriName) { // 리뷰삭제
    if (isEmpty(reviewUser)) {
        alert("로그인부터 진행해 주세요.")
    } else {
        if (confirm("리뷰를 삭제하시겠습니까?") == true) {
            const data = {
                'reviewUser' : reviewUser,
                'photoOriName' : photoOriName
            }
            $.ajax({
                type: "GET",
                url: "/EachMovie/reviewDelete",
                data: data,
                success: function (deleteResult) {
                    if (deleteResult == 0) {
                        alert("로그인부터 진행해 주세요.")
                    } else {
                        alert("리뷰가 삭제되었습니다.")
                        window.location.reload();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("통신 실패.")
                }
            });
        } else {
            return;
        }
    }
};

window.reviewUpload = function (reviewUser) { // 리뷰업로드
    if (isEmpty(reviewUser)) {
        alert("로그인부터 진행해 주세요")
    } else {
        const params = {
            movieReivew: $("#movieReivew").val(),
            moviePoint: $("#moviePoint").val(),
            'ids' : JSON.stringify(reviewMenIds)
        }
        $.ajax({
            type: "GET",
            url: "/EachMovie/reviewUpload",
            data: params,
            success: function (result) {
                if (result === 1) {
                    alert("리뷰업로드가 완료되었습니다.");
                    window.location.reload()
                } else if(result===2){
                    alert("리뷰는 한번만 등록해주세요..");
                    window.location.reload()
                } else{
                    alert("리뷰등록이 실패하였습니다.");
                    window.location.reload()
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("통신 실패.");
            }
        });
    }
}
if(myReviews.length === 0){
    $("#reviewPosition").append('<div>등록된 리뷰가 없습니다</div>')
}else{
    $("#reviewPosition").empty();
    for (let i = 0; i < myReviews.length; i++) {
        $("#reviewPosition").append('영화 제목 : <div>' + OriMovieName[i] + '</div>');
        $("#reviewPosition").append('영화 점수 : <div>' + myReviews[i].moviePoint + '</div>');
        $("#reviewPosition").append('리뷰 본문 : <div>' + myReviews[i].movieReivew + '</div>');
    }
}
window.goMoviePage = function(photoOriName){
    const newWindow = window.open("about:blank");
    newWindow.location.href="/EachMovie/getMovieInfo?photoOriName="+photoOriName;
};

$('#movieView').click(function () {
    const data = $("#movieName");
    $.ajax({
        type: "POST",
        url: "/oneMovie/viewOneMovie",
        data: data,
        success: function (jpaMovieInfos) {
            $('ol').empty(); // append결과 초기화
            $('ol').append('<h3>검색결과</h3>')
            for (const i in jpaMovieInfos) {
                const value = jpaMovieInfos[i].photoUri;
                $('ol').append('</div></div><img id="moviePoster" src="' + jpaMovieInfos[i].photoUri + '" >');
                $('ol').append('<li onclick="goMoviePage(\''+value+'\')" type="hidden" name="movieName" id="goEachMovieInfo" >' + jpaMovieInfos[i].movieName + '</li>');
                // String 값을 전달해주기 위해 정규표현식 사용
                // \'' + value + '\'

            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.")
        }
    });
});
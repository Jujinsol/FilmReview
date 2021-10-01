function goSearchPage(result) {
    const jsonParseResult = JSON.parse(result);
    const form = document.createElement('form'); // 폼객체 생성

    const stringJson = JSON.stringify(jsonParseResult);

    let objs;
    objs = document.createElement('input'); // 값이 들어있는 애의 형식
    objs.setAttribute('type', 'text'); // 값이 들어있는 애의 type
    objs.setAttribute('name', 'movieJsonData'); // 객체이름
    objs.setAttribute('value', stringJson); //객체값

    form.appendChild(objs);

    form.setAttribute('method', 'post'); //get,post 가능
    form.setAttribute('action', "/oneMovie/newPage"); //보내는 url
    document.body.appendChild(form);
    form.submit();
}


window.goMoviePage = function (photoOriName) {
    const newWindow = window.open("about:blank");
    newWindow.location.href = "/EachMovie/getMovieInfo?photoOriName=" + photoOriName;
};


$('#movieView').click(function () {
    const data = $("#movieName");
    $.ajax({
        type: "POST",
        url: "/oneMovie/viewOneMovie",
        data: data,
        success: function (searchResult) {
            goSearchPage(JSON.stringify(searchResult));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.")
        }
    });
});
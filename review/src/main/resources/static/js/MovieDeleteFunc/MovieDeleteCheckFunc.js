$('#deleteMovie').click(function () {
    const checkData = new Object();

    const sendData = {
        'deleteName': $("#movieName").val()
    }

    // ajax 통신
    $.ajax({
        type: "GET",
        dataType: 'json',
        url: "/DeleteMovie",
        data: sendData,
        success: function (movieOriName) {
            const splitResult = movieOriName.toString().split(",");
            for (let i = 0; i < splitResult.length; i++) {
                checkData[i] = splitResult[i];
            }

            console.log("객체생성완료" + checkData[0] + checkData[1])

            location.href='/DeleteMovie/DeleteCheck?'+$.param(checkData);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패." + XMLHttpRequest + textStatus + errorThrown);
        }
    });
});
$('#logout').click(function () {
    // ajax 통신
    $.ajax({
        type: "GET",
        url: "/logout",
        success: function (logoutResult) {
            alert(logoutResult)
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.")
        }
    });
});
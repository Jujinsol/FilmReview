
function uploadAlarm() {

    let form = $("#HtmlForm")[0];
    const data = new FormData(form);

    $.ajax({
        type: "POST",
        url: "/Upload/doUpload",
        data: data,
        contentType : false,
        processData : false,
        success: function (PageUri) {
            send($("#movieName").val());
            window.location.replace(PageUri);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("통신 실패.")
        }
    });

}

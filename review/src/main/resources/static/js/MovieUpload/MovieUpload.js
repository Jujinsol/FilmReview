// $('#uploadBtn').click(function () {
//
//     let data = new Object();
//     data.movieName = $("#movieName");
//     data.moviePoster = $("#moviePoster");
//     data.trailerCode = $("#trailerCode");
//     data.openYear = $("#openYear");
//     data.directorName = $("#directorName");
//     data.storyLine = $("#storyLine");
//
//     $.ajax({
//         type: "POST",
//         url: "/oneMovie/viewOneMovie",
//         data: data,
//         success: function (Result) {
//             alert("업로드 성공 " + Result);
//         },
//         error: function (XMLHttpRequest, textStatus, errorThrown) {
//             alert("통신 실패.")
//         }
//     });
// });
package movieReview.review.Domain.MovieInfo;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public class movieInfoBuilder {
    private String movieName;
    private MultipartFile moviePoster;
    private int openYear;
    private String directorName;
    private String storyLine;
    private String trailerCode;
    private int moviePoint;
    private String movieReview;
    private String photoOriName; // 원본 파일명
    private String photoUri; // 파일 저장 경로
    private Path path; // 출력할때 쓸 경로

    public movieInfoBuilder setMovieName(String movieName) {
        this.movieName = movieName;
        return this;
    }

    public movieInfoBuilder setMoviePoster(MultipartFile moviePoster) {
        this.moviePoster = moviePoster;
        return this;
    }

    public movieInfoBuilder setOpenYear(int openYear) {
        this.openYear = openYear;
        return this;
    }

    public movieInfoBuilder setDirectorName(String directorName) {
        this.directorName = directorName;
        return this;
    }

    public movieInfoBuilder setStoryLine(String storyLine) {
        this.storyLine = storyLine;
        return this;
    }

    public movieInfoBuilder setTrailerCode(String trailerCode) {
        this.trailerCode = trailerCode;
        return this;
    }

    public movieInfoBuilder setMoviePoint(int moviePoint) {
        this.moviePoint = moviePoint;
        return this;
    }

    public movieInfoBuilder setMovieReview(String movieReview) {
        this.movieReview = movieReview;
        return this;
    }

    public movieInfoBuilder setPhotoOriName(String photoOriName) {
        this.photoOriName = photoOriName;
        return this;
    }

    public movieInfoBuilder setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
        return this;
    }

    public movieInfoBuilder setPath(Path path) {
        this.path = path;
        return this;
    }

    public movieInfo build(){
        movieInfo movieInfo = new movieInfo();
        movieInfo.setMovieName(movieName);
        movieInfo.setMoviePoster(moviePoster);
        movieInfo.setOpenYear(openYear);
        movieInfo.setDirectorName(directorName);
        movieInfo.setStoryLine(storyLine);
        movieInfo.setTrailerCode(trailerCode);
        movieInfo.setMoviePoint(moviePoint);
        movieInfo.setMovieReview(movieReview);
        movieInfo.setPhotoOriName(photoOriName);
        movieInfo.setPhotoUri(photoUri);
        movieInfo.setPath(path);
        return movieInfo;
    }
}

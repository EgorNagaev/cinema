package com.example.cinema2.Start;

public class FilmToken {
    String movieId,backgroundImage,foregroundImage;

    public FilmToken(String movieId, String backgroundImage, String foregroundImage) {
        this.movieId = movieId;
        this.backgroundImage = backgroundImage;
        this.foregroundImage = foregroundImage;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getForegroundImage() {
        return foregroundImage;
    }

    public void setForegroundImage(String foregroundImage) {
        this.foregroundImage = foregroundImage;
    }
}

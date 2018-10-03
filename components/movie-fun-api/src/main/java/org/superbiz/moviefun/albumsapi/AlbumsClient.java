package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import org.superbiz.moviefun.moviesapi.MovieInfo;

import java.util.List;

public class AlbumsClient {

    private static ParameterizedTypeReference<List<AlbumInfo>> movieListType = new ParameterizedTypeReference<List<AlbumInfo>>() {};
    private String albumsUrl;
    private RestOperations restOperations;

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl = albumsUrl;
        this.restOperations = restOperations;
    }


    public void addAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl + "/add" , album, AlbumInfo.class);
    }


    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumsUrl + "/" + id, AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {
        return restOperations.exchange(albumsUrl + "/getAll", HttpMethod.GET, null, movieListType).getBody();
    }


    public void deleteAlbum(AlbumInfo album) {
        restOperations.postForEntity(albumsUrl + "/delete", album, MovieInfo.class);
    }



    public void updateAlbum(AlbumInfo album) {
        restOperations.put(albumsUrl, album);
    }
}

package org.superbiz.moviefun.albums;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albumService")
public class AlbumServiceController {

    private AlbumsRepository albumsRepository;

    public AlbumServiceController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping("/add")
    public void add(@RequestBody Album album) {
        albumsRepository.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album find(@PathVariable Long id) {
        return albumsRepository.find(id);
    }

    @GetMapping("/getAll")
    public List<Album> getAll() {
        return albumsRepository.getAlbums();
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Album album) {
        albumsRepository.deleteAlbum(album);
    }

    @PutMapping
    public void update(@RequestBody Album album) {
        albumsRepository.updateAlbum(album);
    }


}

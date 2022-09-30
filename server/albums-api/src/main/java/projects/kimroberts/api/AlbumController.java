package projects.kimroberts.api;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import projects.kimroberts.model.album.Album;
import projects.kimroberts.service.AlbumService;

@RestController
@RequestMapping("api/v1/albums")
public class AlbumController {
	
	private AlbumService albumService;
	
	@Autowired
	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@GetMapping
	public ResponseEntity<List<Album>> getAllAlbums(@RequestParam(name="search", required=false) String searchPattern) {
		List<Album> albums;
		if (searchPattern == null) {
			albums = albumService.getAllAlbums();
		} else {
			albums = albumService.searchAlbums(searchPattern);
		}
		return new ResponseEntity<List<Album>>(albums, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Album> getAlbumById(@PathVariable("id") int id) {
		try {
			Album album = albumService.getAlbumById(id);
			return new ResponseEntity<Album>(album, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Album> updateAlbumById(@PathVariable("id") int id, @RequestBody Album album) {
		try {
			Album updatedAlbum = albumService.updateAlbumById(id, album); 
			return new ResponseEntity<Album>(updatedAlbum, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAlbumById(@PathVariable("id") int id) {
		albumService.deleteAlbumById(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
		Album createdAlbum = albumService.createAlbum(album); 
		return new ResponseEntity<Album>(createdAlbum, HttpStatus.CREATED);
	}
	

}

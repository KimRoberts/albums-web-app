package projects.kimroberts.api;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Album> getAllAlbums(@RequestParam(name="search", required=false) String searchPattern) {
		if (searchPattern == null) {
			return albumService.getAllAlbums();
		}
		return albumService.searchAlbums(searchPattern);
	}
	
	@GetMapping("/{id}")
	public Album getAlbumById(@PathVariable("id") int id) {
		try {
			return albumService.getAlbumById(id);
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@PutMapping("/{id}")
	public Album updateAlbumById(@PathVariable("id") int id, @RequestBody Album album) {
		try {
			return albumService.updateAlbumById(id, album);			
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteAlbumById(@PathVariable("id") int id) {
		albumService.deleteAlbumById(id);
	}
	
	@PostMapping
	public Album createAlbum(@RequestBody Album album) {
		return albumService.createAlbum(album);
	}
	

}

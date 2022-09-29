package projects.kimroberts.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projects.kimroberts.model.Album;
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
	public List<Album> getAllAlbums() {
		return albumService.getAllAlbums();
	}

}

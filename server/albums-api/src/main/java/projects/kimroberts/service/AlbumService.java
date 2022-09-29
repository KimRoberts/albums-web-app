package projects.kimroberts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import projects.kimroberts.dao.IAlbumDao;
import projects.kimroberts.model.album.Album;

@Service
public class AlbumService {
	
	private IAlbumDao albumDao;
	
	@Autowired
	public AlbumService(@Qualifier("albums-postgres") IAlbumDao albumDao) {
		this.albumDao = albumDao;
	}
	
	public List<Album> getAllAlbums() {
		return albumDao.getAllAlbums();
	}

}

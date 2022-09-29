package projects.kimroberts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import projects.kimroberts.model.Album;

@Repository("albums-postgres")
public class AlbumPostgresDao implements IAlbumDao {

	@Override
	public List<Album> getAllAlbums() {
		List<Album> albums = new ArrayList<>();
		albums.add(new Album(1, "Testing", "The Tests", 1969));
		return albums;
	}

}

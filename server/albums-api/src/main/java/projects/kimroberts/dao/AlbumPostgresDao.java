package projects.kimroberts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import projects.kimroberts.model.album.Album;
import projects.kimroberts.model.album.AlbumRowMapper;

@Repository("albums-postgres")
public class AlbumPostgresDao implements IAlbumDao {
	
	private JdbcTemplate jdbcTemplate;
	
	//test
	
	@Autowired
	public AlbumPostgresDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Album> getAllAlbums() {
		String query = "SELECT * FROM albums;";
		return jdbcTemplate.query(query, new AlbumRowMapper());
	}

	@Override
	public Album getAlbumById(int id) {
		String query = String.format("SELECT * FROM albums WHERE id=%d", id);
		List<Album> albums = jdbcTemplate.query(query, new AlbumRowMapper());
		if (albums.size() == 0) {
			throw new NoSuchElementException();
		}
		return albums.get(0);
	}

	@Override
	public Album updateAlbumById(int id, Album album) {
		String query = String.format("UPDATE albums SET title=\'%s\', artist=\'%s\', year=%d WHERE id=%d",
									album.getTitle(),
									album.getArtist(),
									album.getYear(),
									id);
		jdbcTemplate.update(query);
		return getAlbumById(id);
	}

	@Override
	public void deleteAlbumById(int id) {
		String query = String.format("DELETE FROM albums WHERE id=%d", id);
		jdbcTemplate.update(query);
	}

	@Override
	public List<Album> searchAlbums(String searchPattern) {
		// TODO Auto-generated method stub
		return null;
	}

}

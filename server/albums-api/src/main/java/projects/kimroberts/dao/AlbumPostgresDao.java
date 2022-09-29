package projects.kimroberts.dao;

import java.util.ArrayList;
import java.util.List;

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

}

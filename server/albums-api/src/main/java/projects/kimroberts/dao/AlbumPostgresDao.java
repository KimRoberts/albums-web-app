package projects.kimroberts.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import projects.kimroberts.model.album.Album;
import projects.kimroberts.model.album.AlbumRowMapper;

@Repository("albums-postgres")
public class AlbumPostgresDao implements IAlbumDao {
	
	private static final String TABLE_NAME = "albums";
	private static final String ID_COL = "id";
	private static final String TITLE_COL = "title";
	private static final String ARTIST_COL = "artist";
	private static final String YEAR_COL = "year";
	private static final String DONE_COL = "done";
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	private AlbumRowMapper albumRowMapper;
	
	@Autowired
	public AlbumPostgresDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
									.withTableName(TABLE_NAME)
									.usingGeneratedKeyColumns("id");
		this.albumRowMapper = new AlbumRowMapper();
	}

	@Override
	public List<Album> getAllAlbums() {
		String query = String.format("SELECT * FROM %s;", TABLE_NAME);
		return jdbcTemplate.query(query, albumRowMapper);
	}

	@Override
	public Album getAlbumById(int id) {
		String query = String.format("SELECT * FROM %s WHERE %s=%d",
									TABLE_NAME,
									ID_COL, id);
		List<Album> albums = jdbcTemplate.query(query, albumRowMapper);
		if (albums.size() == 0) {
			throw new NoSuchElementException();
		}
		return albums.get(0);
	}

	@Override
	public Album updateAlbumById(int id, Album album) {
		String query = String.format("UPDATE %s SET %s=\'%s\', %s=\'%s\', %s=%d, %s=%s WHERE %s=%d",
									TABLE_NAME,
									TITLE_COL, album.getTitle(),
									ARTIST_COL, album.getArtist(),
									YEAR_COL, album.getYear(),
									DONE_COL, album.isDone(),
									ID_COL, id);
		jdbcTemplate.update(query);
		return getAlbumById(id);
	}

	@Override
	public void deleteAlbumById(int id) {
		String query = String.format("DELETE FROM %s WHERE %s=%d",
									TABLE_NAME,
									ID_COL, id);
		jdbcTemplate.update(query);
	}

	@Override
	public List<Album> searchAlbums(String searchPattern) {
		String query = String.format(
				"SELECT * FROM %s WHERE LOWER(%s) LIKE LOWER(\'%%%s%%\') OR LOWER(%s) LIKE LOWER(\'%%%s%%\')",
									TABLE_NAME,
									TITLE_COL, searchPattern,
									ARTIST_COL, searchPattern);
		return jdbcTemplate.query(query, albumRowMapper);
	}

	@Override
	public Album createAlbum(Album album) {
		Map<String, Object> values = new HashMap<>();
		values.put(TITLE_COL, album.getTitle());
		values.put(ARTIST_COL, album.getArtist());
		values.put(YEAR_COL, album.getYear());
		values.put(DONE_COL, album.isDone());
		int id = (int) simpleJdbcInsert.executeAndReturnKey(values);
		return getAlbumById(id);
	}

}

package projects.kimroberts.model.album;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AlbumRowMapper implements RowMapper<Album> {

	@Override
	public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
		Album album = new Album();
		album.setId(rs.getInt("id"));
		album.setTitle(rs.getString("title"));
		album.setArtist(rs.getString("artist"));
		album.setYear(rs.getInt("year"));
		return album;
	}

}

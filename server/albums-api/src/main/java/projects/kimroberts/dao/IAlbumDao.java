package projects.kimroberts.dao;

import java.util.List;

import projects.kimroberts.model.album.Album;

public interface IAlbumDao {
	
	public List<Album> getAllAlbums();
	public Album getAlbumById(int id);
	public Album updateAlbumById(int id, Album album);
	public void deleteAlbumById(int id);
	public List<Album> searchAlbums(String searchPattern);
	public Album createAlbum(Album album);

}

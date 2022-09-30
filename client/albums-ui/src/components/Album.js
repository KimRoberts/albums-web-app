import React from 'react'

const Album = ({album, deleteAlbum}) => {
  return (
    <div className="card album">
      <img src="https://upload.wikimedia.org/wikipedia/en/3/3b/Dark_Side_of_the_Moon.png" />
      <div className="info">
        <h1>{album.title}</h1>
        <p>{album.artist}</p>
        <p>{album.year}</p>
      </div>
      <button className='close-btn' onClick={() => deleteAlbum(album.id)}>
        <span className="material-symbols-outlined">delete_forever</span>
      </button>
    </div>
  );
}

export default Album
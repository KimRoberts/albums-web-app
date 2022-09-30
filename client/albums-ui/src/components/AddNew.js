import React, { useState } from 'react'

const AddNew = ({createAlbum}) => {

    const [title, setTitle] = useState("");
    const [artist, setArtist] = useState("");
    const [year, setYear] = useState();

  return (
    <div className="card add-new">
      <input
        type="text"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        placeholder="Title"
      />
      <input
        type="text"
        value={artist}
        onChange={(e) => setArtist(e.target.value)}
        placeholder="Artist"
      />
      <input
        type="text"
        value={year}
        onChange={(e) => setYear(e.target.value)}
        placeholder="Year"
      />
      <button
        onClick={() => createAlbum({"title": title, "artist": artist, "year": year})}>
        add
        </button>
    </div>
  );
}

export default AddNew
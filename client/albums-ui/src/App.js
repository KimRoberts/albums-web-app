import { useState, useEffect } from 'react';
import axios from 'axios';
import Album from './components/Album';
import './styles/app.css';

function App() {

  const [albums, setAlbums] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/albums')
      .then((res) => {
        setAlbums(res.data);
      })
      .catch((err) => {
        console.error(err);
      })
  }, [])

  const deleteAlbum = (id) => {
    axios.delete(`http://localhost:8080/api/v1/albums/${id}`)
      .then((res) => {
        if (res.status == 204) {
          setAlbums((prev) => {
            return prev.filter((album) => album.id != id);
          })
        }
      })
      .catch((err) => {
        console.error(err)
      })
  }

  return (
    <>
      <h1 className="title">albums.</h1>
      <div className="list album-list">
        {albums.map((album) => {
          return (
            <Album key={album.id} album={album} deleteAlbum={deleteAlbum} />
          );
        })}
        <div className='add-new'>
          <button>
            <span className="material-symbols-outlined">add_circle</span>
          </button>
          <p>Add new</p>
        </div>
      </div>
    </>
  );
}

export default App;

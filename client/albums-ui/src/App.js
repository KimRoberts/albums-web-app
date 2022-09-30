import { useState, useEffect } from 'react';
import axios from 'axios';
import Album from './components/Album';
import './styles/app.css';
import AddNew from './components/AddNew';

function App() {

  const [albums, setAlbums] = useState([]);
  const [showAddNew, setShowAddNew] = useState(false);

  useEffect(() => {
    axios
      .get('http://localhost:8080/api/v1/albums')
      .then((res) => {
        setAlbums(res.data);
      })
      .catch((err) => {
        console.error(err);
      })
  }, [])

  const deleteAlbum = (id) => {
    axios
      .delete(`http://localhost:8080/api/v1/albums/${id}`)
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

  const createAlbum = (album) => {
    axios
      .post(`http://localhost:8080/api/v1/albums`, album)
      .then((res) => {
        const newAlbum = {
          "id": res.data.id,
          "title": res.data.title,
          "artist": res.data.artist,
          "year": res.data.year
        };
        setAlbums([...albums, newAlbum]);
        setShowAddNew(false);
      })
      .catch((err) => {
        console.error(err);
      });
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
        <div className="card add-new-btn">
          <button onClick={() => setShowAddNew(true)}>
            <span className="material-symbols-outlined">add_circle</span>
            <p>Add new</p>
          </button>
          {
            showAddNew && <AddNew createAlbum={createAlbum}/>
          }
        </div>
      </div>
    </>
  );
}

export default App;

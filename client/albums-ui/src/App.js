import { useState, useEffect } from 'react';
import axios from 'axios';
import Album from './components/Album';

function App() {

  const [albums, setAlbums] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/albums')
      .then((res) => {
        setAlbums(res.data)
      })
      .catch((err) => {
        console.error(err)
      })
  }, [])
  return <>
    {
      albums.map((album) => {
        return <Album key={album.id} album={album}/>
      })
    }
  </>
}

export default App;

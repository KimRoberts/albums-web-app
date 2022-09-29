import React from 'react'

const Album = ({album}) => {
  return (
    <div>
        <p>Title: {album.title}</p>
        <p>Artist: {album.artist}</p>
        <p>Year: {album.year}</p>
    </div>
  )
}

export default Album
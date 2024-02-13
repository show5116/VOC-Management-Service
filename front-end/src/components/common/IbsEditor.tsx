import { useState, useEffect, memo, Dispatch, SetStateAction } from 'react'

import ReactQuill from 'react-quill'
import 'react-quill/dist/quill.snow.css'

const modules = {
  toolbar: [
    [{ header: [1, 2, false] }],
    ['bold', 'italic', 'underline', 'strike', 'blockquote'],
    [
      { list: 'ordered' },
      { list: 'bullet' },
      { indent: '-1' },
      { indent: '+1' },
    ],
    ['link', 'image'],
    [{ align: [] }, { color: [] }, { background: [] }], // dropdown with defaults from theme
    ['clean'],
  ],
}

const formats = [
  'header',
  'bold',
  'italic',
  'underline',
  'strike',
  'blockquote',
  'list',
  'bullet',
  'indent',
  'link',
  'image',
  'align',
  'color',
  'background',
]

interface IProps {
  title?: string
  width: string
  height: string
  content?: string
  setContent?: Dispatch<SetStateAction<string>>
}

const IbsEditor = (props: IProps) => {
  return (
    <>
      {props.title && <h3>{props.title}</h3>}
      <ReactQuill
        style={{
          width: `${props.width}`,
          height: `${props.height}`,
        }}
        theme='snow'
        modules={modules}
        formats={formats}
        value={props.content || ''}
        onChange={props.setContent}
      />
    </>
  )
}

export default memo(IbsEditor)

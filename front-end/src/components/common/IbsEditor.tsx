import { forwardRef, memo, useImperativeHandle, useState } from 'react'

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

interface IbsEditorProps {
  title?: string
  width: string
  height: string
}

export type IbsEditorHandle = {
  getContent: () => string
}

const IbsEditor = forwardRef<IbsEditorHandle, IbsEditorProps>(
  (editorProps, ref) => {
    useImperativeHandle(ref, () => ({
      getContent,
    }))
    const [content, setContent] = useState('')

    const getContent = () => content

    return (
      <>
        {editorProps.title && <h3>{editorProps.title}</h3>}
        <ReactQuill
          style={{
            width: `${editorProps.width}`,
            height: `${editorProps.height}`,
          }}
          theme='snow'
          modules={modules}
          formats={formats}
          value={content || ''}
          onChange={setContent}
        />
      </>
    )
  },
)

export default memo(IbsEditor)

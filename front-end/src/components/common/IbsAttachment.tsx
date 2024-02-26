import { Dispatch, memo, SetStateAction } from 'react'

import styled from '@emotion/styled'
import Button from '@mui/material/Button'
import FormControl from '@mui/material/FormControl'
import TextField from '@mui/material/TextField'
import InputAdornment from '@mui/material/InputAdornment'

import { FaFile, FaRegSave } from 'react-icons/fa'
import { FaDeleteLeft } from 'react-icons/fa6'

interface IbsAttachmentProps {
  files: File[]
  setFiles: Dispatch<SetStateAction<File[]>>
  multiple?: boolean
  accept?: string
}

interface AttachmentProps {
  file?: File
  index: number
}

const IbsAttachment = (props: IbsAttachmentProps) => {
  const handleFileChange = (e: any) => {
    const eventFiles = Array.from<File>(e.target.files)
    props.setFiles([...props.files, ...eventFiles])
  }

  const onClickDeleteFile = (index: number) => {
    const files = props.files
    files.splice(index, 1)
    props.setFiles([...files])
  }

  const Attachment = (attachmentProps: AttachmentProps) => (
    <div
      style={{
        display: 'flex',
        flexDirection: 'row',
        gap: '10px',
      }}
    >
      <TextField
        style={{
          width: attachmentProps.index ? '100%' : 'calc(100% - 160px)',
        }}
        InputProps={{
          startAdornment: (
            <InputAdornment position='start'>
              <FaFile />
            </InputAdornment>
          ),
          endAdornment: attachmentProps.file && (
            <InputAdornment position='end'>
              <FaDeleteLeft
                cursor='pointer'
                onClick={() => onClickDeleteFile(attachmentProps.index)}
              />
            </InputAdornment>
          ),
        }}
        label='File'
        size='small'
        value={
          attachmentProps.file
            ? attachmentProps.file.name
            : '파일을 첨부할 수 있습니다.'
        }
        disabled={true}
      />
      {!attachmentProps.index && (
        <Button
          style={{ width: '150px' }}
          component='label'
          variant='contained'
          startIcon={<FaRegSave />}
        >
          파일 업로드
          <VisuallyHiddenInput
            type='file'
            multiple={props.multiple}
            accept={props.accept}
            onChange={handleFileChange}
          />
        </Button>
      )}
    </div>
  )

  return (
    <>
      <FormControl
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '10px',
        }}
      >
        {props.multiple && props.files.length ? (
          props.files.map((file, index) => (
            <Attachment key={file.name + index} file={file} index={index} />
          ))
        ) : (
          <Attachment index={0} />
        )}
      </FormControl>
    </>
  )
}

export default memo(IbsAttachment)

const VisuallyHiddenInput = styled('input')({
  clip: 'rect(0 0 0 0)',
  clipPath: 'inset(50%)',
  height: 1,
  overflow: 'hidden',
  position: 'absolute',
  bottom: 0,
  left: 0,
  whiteSpace: 'nowrap',
  width: 1,
})

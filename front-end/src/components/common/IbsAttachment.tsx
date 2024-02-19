import { forwardRef, memo, useImperativeHandle, useState } from 'react'

import styled from '@emotion/styled'
import Button from '@mui/material/Button'
import FormControl from '@mui/material/FormControl'
import TextField from '@mui/material/TextField'
import InputAdornment from '@mui/material/InputAdornment'

import { FaFile, FaRegSave } from 'react-icons/fa'

interface IbsAttachmentProps {}

export type IbsAttachmentHandle = {
  getFile: () => File
}

const IbsAttachment = forwardRef<IbsAttachmentHandle, IbsAttachmentProps>(
  (attachmentProps, ref) => {
    useImperativeHandle(ref, () => ({
      getFile,
    }))
    const [files, setFiles] = useState<File[]>([])

    const getFile = () => {
      console.log(files[0])
      return files[0]
    }
    const handleFileChange = (e: any) => {
      setFiles(e.target.files)
    }

    return (
      <>
        <Button
          style={{ marginRight: '10px' }}
          component='label'
          variant='contained'
          startIcon={<FaRegSave />}
        >
          파일 업로드
          <VisuallyHiddenInput type='file' onChange={handleFileChange} />
        </Button>
        <FormControl style={{ width: '180px' }}>
          <TextField
            InputProps={{
              startAdornment: (
                <InputAdornment position='start'>
                  <FaFile />
                </InputAdornment>
              ),
            }}
            label='File'
            size='small'
            value={files[0]?.name}
            disabled={true}
          />
        </FormControl>
      </>
    )
  },
)

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

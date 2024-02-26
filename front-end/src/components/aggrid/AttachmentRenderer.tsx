import { useState, useRef, memo, useEffect } from 'react'

import { Unstable_Popup as Popup } from '@mui/base/Unstable_Popup'

import * as S from './AttachmentRenderer.style'
import { FaDownload, FaFileUpload, FaWindowClose } from 'react-icons/fa'
import { FaDeleteLeft } from 'react-icons/fa6'
import { colors } from '../styles/colors'
import styled from '@emotion/styled'

import ibsAxios from '@/utils/ibsAxios'
import { AxiosRequestConfig, AxiosResponse } from 'axios'

const AttachmentRenderer = (props: any) => {
  const [anchor, setAnchor] = useState<null | HTMLElement>(null)
  const [files, setFiles] = useState<File[]>([])

  const divRef = useRef<HTMLDivElement>(null)
  const fileRef = useRef<HTMLInputElement>(null)

  const onClickAttachment = (event: React.MouseEvent<HTMLElement>) => {
    if (anchor === null) {
      divRef.current!.focus()
      setAnchor(event.currentTarget)
    }
  }

  const onBlurAttachment = (event: React.FocusEvent<HTMLElement>) => {
    if (event.relatedTarget === null) {
      setAnchor(null)
    }
  }

  const onKeyDown = (event: React.KeyboardEvent<HTMLElement>) => {
    if (event.key === 'Escape') {
      setAnchor(null)
    }
  }

  const onClickClose = (event: React.MouseEvent<SVGElement>) => {
    event.stopPropagation()
    setAnchor(null)
  }

  const onClickSave = () => {
    const config: AxiosRequestConfig = {
      responseType: 'blob',
      params: {
        fileId: props.data.fileId,
      },
    }

    const success = (response: AxiosResponse) => {
      const blob = new Blob([response.data])

      const fileObjectUrl = window.URL.createObjectURL(blob)

      const link = document.createElement('a')
      link.href = fileObjectUrl
      link.style.display = 'none'

      link.download = props.data.fileName

      document.body.appendChild(link)
      link.click()
      link.remove()

      window.URL.revokeObjectURL(fileObjectUrl)
    }

    ibsAxios.get('/download', config).then(success)
  }

  const onClickChange = () => {
    fileRef.current!.click()
  }

  const handleFileChange = (e: any) => {
    setFiles(e.target.files)
  }

  const onClickDelete = () => {}

  const iconColor = colors.blueGrren

  const open = Boolean(anchor)

  return (
    <div
      ref={divRef}
      onClick={onClickAttachment}
      onBlur={onBlurAttachment}
      onKeyDown={onKeyDown}
      tabIndex={0}
    >
      {props.value}
      <Popup open={open} anchor={anchor} tabIndex={0}>
        <S.PopupBody>
          <S.PopupHeader>
            <h3>첨부파일</h3>
            <FaWindowClose size='12px' onClick={onClickClose} />
          </S.PopupHeader>
          <S.ButtonGroup>
            <S.AttachmentButton onClick={onClickSave}>
              <FaDownload color={iconColor} size='20px' />
              <span>저장</span>
            </S.AttachmentButton>
            <S.AttachmentButton onClick={onClickChange}>
              <VisuallyHiddenInput
                ref={fileRef}
                type='file'
                onChange={handleFileChange}
              />
              <FaFileUpload color={iconColor} size='20px' />
              <span>변경</span>
            </S.AttachmentButton>
            <S.AttachmentButton onClick={onClickDelete}>
              <FaDeleteLeft color={iconColor} size='20px' />
              <span>삭제</span>
            </S.AttachmentButton>
          </S.ButtonGroup>
        </S.PopupBody>
      </Popup>
    </div>
  )
}

export default memo(AttachmentRenderer)

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

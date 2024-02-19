import { useState, useRef, memo, useEffect } from 'react'

import { Unstable_Popup as Popup } from '@mui/base/Unstable_Popup'

import * as S from './AttachmentRenderer.style'
import {
  FaDownload,
  FaFileUpload,
  FaFolderOpen,
  FaWindowClose,
} from 'react-icons/fa'
import { FaDeleteLeft } from 'react-icons/fa6'
import { colors } from '../styles/colors'
import styled from '@emotion/styled'

const AttachmentRenderer = (props: any) => {
  const [anchor, setAnchor] = useState<null | HTMLElement>(null)
  const [files, setFiles] = useState<File[]>([])

  const divRef = useRef<HTMLDivElement>(null)

  useEffect(() => {
    console.log(files)
  }, [files])

  const onClickAttachment = (event: React.MouseEvent<HTMLElement>) => {
    divRef.current!.focus()
    setAnchor(event.currentTarget)
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

  const onClickSave = () => {}

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
            <S.AttachmentButton>
              <FaFileUpload color={iconColor} size='20px' />
              <span>변경</span>
              <VisuallyHiddenInput type='file' onChange={handleFileChange} />
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

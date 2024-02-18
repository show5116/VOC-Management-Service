import { useState, useRef, memo } from 'react'

import { Unstable_Popup as Popup } from '@mui/base/Unstable_Popup'

import * as S from './AttachmentRenderer.style'
import {
  FaDownload,
  FaFileUpload,
  FaFolderOpen,
  FaWindowClose,
} from 'react-icons/fa'
import { FaDeleteLeft } from 'react-icons/fa6'

const AttachmentRenderer = (props: any) => {
  const [anchor, setAnchor] = useState<null | HTMLElement>(null)

  const divRef = useRef<HTMLDivElement>(null)

  const onClickAttachment = (event: React.MouseEvent<HTMLElement>) => {
    divRef.current!.focus()
    setAnchor(anchor ? null : event.currentTarget)
  }

  const onBlurAttachment = () => {
    console.log('onBlur')
    setAnchor(null)
  }

  const open = Boolean(anchor)

  return (
    <div ref={divRef} onClick={onClickAttachment} onBlur={onBlurAttachment}>
      {props.value}
      <Popup open={open} anchor={anchor}>
        <S.PopupBody>
          <S.CloseButton>
            <FaWindowClose size='15px' />
          </S.CloseButton>
          <S.AttachmentButton>
            <FaDownload size='25px' />
            <span>저장</span>
          </S.AttachmentButton>
          <S.AttachmentButton>
            <FaFileUpload size='25px' />
            <span>변경</span>
          </S.AttachmentButton>
          <S.AttachmentButton>
            <FaDeleteLeft size='25px' />
            <span>삭제</span>
          </S.AttachmentButton>
        </S.PopupBody>
      </Popup>
    </div>
  )
}

export default memo(AttachmentRenderer)

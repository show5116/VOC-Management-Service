import { useState, useRef, memo, useEffect } from 'react'

import { Unstable_Popup as Popup } from '@mui/base/Unstable_Popup'

import * as S from './AttachmentRenderer.style'
import { FaDownload, FaFileUpload, FaWindowClose } from 'react-icons/fa'
import { FaDeleteLeft } from 'react-icons/fa6'
import { colors } from '../styles/colors'
import styled from '@emotion/styled'

import ibsAxios, { multipartConfig } from '@/utils/ibsAxios'
import { AxiosRequestConfig, AxiosResponse } from 'axios'

const AttachmentRenderer = (props: any) => {
  const [anchor, setAnchor] = useState<null | HTMLElement>(null)

  const fileNameList = props.value && props.value.split(',')
  const fileIdList = props.data.fileId.split(',')

  const divRef = useRef<HTMLDivElement>(null)
  const popupRef = useRef<Element>(null)
  const fileRef = useRef<HTMLInputElement>(null)

  const onClickAttachment = (
    event: React.MouseEvent<HTMLElement>,
    anchor: any,
  ) => {
    if (anchor === null) {
      divRef.current!.focus()
      setAnchor(event.currentTarget)
    } else if (event.currentTarget !== anchor) {
      divRef.current!.focus()
      setAnchor(event.currentTarget)
    }
  }

  const onBlurAttachment = (event: React.FocusEvent<HTMLElement>) => {
    console.log(popupRef.current?.contains(event.target))
    if (
      !popupRef.current?.contains(event.target) &&
      (event.relatedTarget === null ||
        !popupRef.current?.contains(event.relatedTarget))
    ) {
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

  const onClickSave = (index: number) => {
    const config: AxiosRequestConfig = {
      responseType: 'blob',
      params: {
        fileId: fileIdList[index],
      },
    }

    const success = (response: AxiosResponse) => {
      const blob = new Blob([response.data])

      const fileObjectUrl = window.URL.createObjectURL(blob)

      const link = document.createElement('a')
      link.href = fileObjectUrl
      link.style.display = 'none'

      link.download = fileNameList[index]

      document.body.appendChild(link)
      link.click()
      link.remove()

      window.URL.revokeObjectURL(fileObjectUrl)
    }

    ibsAxios.get('/file', config).then(success)
  }

  const onClickAdd = (event: React.MouseEvent) => {
    fileRef.current!.click()
  }

  const handleFileChange = (e: any) => {
    e.preventDefault()
    const files = e.target.files

    const formData = new FormData()
    formData.append('plant', props.data.plant)
    formData.append('systemName', props.data.systemName)
    formData.append('systemNameMtype', 'VOC')
    formData.append('systemNameStype', 'VOC')
    formData.append('qmsNumber', props.data.qmsNumber)
    formData.append('revisionNo', '1')
    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i])
    }

    const success = (response: AxiosResponse) => {
      props.getRowData()
    }

    ibsAxios.post('/file', formData, multipartConfig).then(success)
  }

  const onClickDelete = (index: number) => {
    const config: AxiosRequestConfig = {
      params: {
        fileId: fileIdList[index],
      },
    }

    const success = (response: AxiosResponse) => {
      props.getRowData()
    }

    ibsAxios.delete('file', config).then(success)
  }

  const iconColor = colors.blueGrren

  const open = Boolean(anchor)

  return (
    <>
      {fileNameList.length ? (
        fileNameList.map((fileName: any, index: any) => (
          <S.Container
            key={fileName + index}
            ref={divRef}
            onClick={(event) => onClickAttachment(event, anchor)}
            onBlur={onBlurAttachment}
            onKeyDown={onKeyDown}
            tabIndex={0}
          >
            {fileName}
            <Popup open={open} anchor={anchor} tabIndex={0} ref={popupRef}>
              <S.PopupBody>
                <S.PopupHeader>
                  <h3>첨부파일</h3>
                  <FaWindowClose size='12px' onClick={onClickClose} />
                </S.PopupHeader>
                <S.ButtonGroup>
                  <S.AttachmentButton onClick={() => onClickSave(index)}>
                    <FaDownload color={iconColor} size='20px' />
                    <span>저장</span>
                  </S.AttachmentButton>
                  <S.AttachmentButton onClick={onClickAdd}>
                    <VisuallyHiddenInput
                      ref={fileRef}
                      type='file'
                      onChange={handleFileChange}
                    />
                    <FaFileUpload color={iconColor} size='20px' />
                    <span>추가</span>
                  </S.AttachmentButton>
                  <S.AttachmentButton onClick={() => onClickDelete(index)}>
                    <FaDeleteLeft color={iconColor} size='20px' />
                    <span>삭제</span>
                  </S.AttachmentButton>
                </S.ButtonGroup>
              </S.PopupBody>
            </Popup>
          </S.Container>
        ))
      ) : (
        <S.Container
          ref={divRef}
          onClick={(event) => onClickAttachment(event, anchor)}
          onBlur={onBlurAttachment}
          onKeyDown={onKeyDown}
          tabIndex={0}
        >
          <S.NonFileSpan>첨부파일이 없습니다.</S.NonFileSpan>
          <Popup open={open} anchor={anchor} tabIndex={0} ref={popupRef}>
            <S.PopupBody>
              <S.PopupHeader>
                <h3>첨부파일</h3>
                <FaWindowClose size='12px' onClick={onClickClose} />
              </S.PopupHeader>
              <S.ButtonGroup>
                <S.AttachmentButton onClick={onClickAdd}>
                  <VisuallyHiddenInput
                    ref={fileRef}
                    type='file'
                    onChange={handleFileChange}
                  />
                  <FaFileUpload color={iconColor} size='20px' />
                  <span>추가</span>
                </S.AttachmentButton>
              </S.ButtonGroup>
            </S.PopupBody>
          </Popup>
        </S.Container>
      )}
    </>
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

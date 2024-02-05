import React, { useEffect, useState } from 'react'
import DocViewerComponent from './IBSDocViewer'
import ExcelViewer from './ExcelViewer'
import axios from 'axios'

const WebViewer = () => {
  const [fileData, setFileData] = useState<Blob>()
  const [byteData, setByteData] = useState<string>('')
  const [extension, setExtension] = useState<string>('')
  const [isLoading, setIsLoading] = useState(false)

  const handleSubmit = () => {
    setIsLoading(true)
    const postData = {
      path: 'TPMS_SMI_요구사항_231213_Rev1_최종 (1).xlsx',
    }
    //http://localhost:8081/api/viewer/
    //Welcome to Word.docx
    //11월요금상세보기 _ CNCITY에너지.pdf
    //TPMS_SMI_요구사항_231213_Rev1_최종.xlsx
    //SLMT_QMS 시스템_완료보고서_20200728_V0.3.pptx
    axios
      .post('http://localhost:8081/api/viewer', postData)
      .then((response) => {
        setByteData(response.data.fileData)
        const binaryString = window.atob(response.data.fileData)
        const binaryLen = binaryString.length
        const bytes = new Uint8Array(binaryLen)

        for (let i = 0; i < binaryLen; i++) {
          bytes[i] = binaryString.charCodeAt(i)
        }
        //application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
        // Blob 객체 생성
        const blob = new Blob([bytes], {
          type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
        })
        setFileData(blob)
        setExtension(response.data.extension)
        setIsLoading(false)
      })
      .catch((error) => {
        console.error('Error:', error)
        setIsLoading(false)
      })
  }

  useEffect(() => {
    handleSubmit()
  }, [])

  return (
    <div>
      <div>
        {isLoading && <p>Loading...</p>}
        {!isLoading &&
        fileData &&
        (extension === 'PDF' ||
          extension === 'DOCX' ||
          extension === 'DOC' ||
          extension === 'PPT' ||
          extension === 'PPTX') ? (
          <DocViewerComponent fileData={fileData} />
        ) : (
          !isLoading && fileData && <ExcelViewer fileData={fileData} />
        )}
      </div>
    </div>
  )
}

export default WebViewer

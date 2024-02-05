import React, { useEffect, useState } from 'react'
import DocViewer, { DocViewerRenderers } from '@cyntler/react-doc-viewer'

const IBSDocViewer = ({ fileData }: { fileData: Blob }) => {
  const [documentUrl, setDocumentUrl] = useState<any>(null)

  useEffect(() => {
    // fileData가 존재할 때만 처리
    if (fileData) {
      const url = URL.createObjectURL(fileData)
      setDocumentUrl(url)

      // Blob URL 해제 함수
      return () => {
        URL.revokeObjectURL(url)
      }
    }
  }, [fileData])

  return (
    <div>
      {documentUrl && (
        <DocViewer
          pluginRenderers={DocViewerRenderers}
          documents={[{ uri: documentUrl, fileType: 'pdf' }]}
          style={{ height: 1000 }}
          config={{
            header: {
              disableHeader: false,
              disableFileName: false,
              retainURLParams: false,
            },
          }}
        />
      )}
    </div>
  )
}

export default IBSDocViewer

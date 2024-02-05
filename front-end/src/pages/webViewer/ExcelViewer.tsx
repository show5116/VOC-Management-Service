import React, { useState, useEffect, useRef } from 'react'
import * as GC from '@grapecity/spread-sheets'
import * as ExcelIO from '@grapecity/spread-excelio'
import { SpreadSheets, Worksheet } from '@grapecity/spread-sheets-react'
import './Excelviewer.css'
import '@grapecity/spread-sheets/styles/gc.spread.sheets.excel2013white.css'

const ExcelViewer = ({ fileData }: { fileData: Blob }) => {
  const spreadRef = useRef(null)
  const [workbook, setWorkbook] = useState<any>()
  const excelIO = useRef<any>(null)
  const [file, setFile] = useState(null)

  useEffect(() => {
    if (workbook) {
      importFile(fileData)
    }
  }, [workbook])
  const applyWorkbookSettings = (workbook: any) => {
    // 워크북 설정을 재적용
    workbook.options.isProtected = true
    workbook.options.newTabVisible = false

    // 모든 시트에 대한 설정을 재적용
    const sheetCount = workbook.getSheetCount()
    for (let i = 0; i < sheetCount; i++) {
      const sheet = workbook.getSheet(i)
      sheet.options.isProtected = true
      sheet.options.protectionOptions = {
        allowResizeColumns: true,
        allowEditObjects: false, // 이미지 이동 비활성화
      }

      // 더블클릭을 통한 셀 편집 비활성화
      sheet.bind(GC.Spread.Sheets.Events.EditStarting, (e: any) => {
        e.cancel = true
      })
      sheet.setActiveCell(0, 0) // A1 셀을 활성화
      sheet.showCell(
        0,
        0,
        GC.Spread.Sheets.VerticalPosition.top,
        GC.Spread.Sheets.HorizontalPosition.left,
      )
    }

    // 컨텍스트 메뉴를 비활성화합니다.
    workbook.contextMenu.menuData = []
  }
  const initSpread = (spread: any) => {
    spreadRef.current = spread
    const sheet = spread.getActiveSheet()
    sheet.options.isProtected = true
    sheet.options.protectionOptions = {
      allowResizeColumns: true,
      allowEditObjects: false,
    }
    spread.options.allowContextMenu = false
    setWorkbook(spread)
    excelIO.current = new ExcelIO.IO()
    spread.bind(
      GC.Spread.Sheets.Events.ActiveSheetChanging,
      (sender: any, args: any) => {
        applyWorkbookSettings(spread)
      },
    )
  }

  const importFile = (fileData: Blob) => {
    if (!fileData) return

    workbook.suspendPaint()
    workbook.suspendCalcService()
    workbook.suspendEvent()

    excelIO.current.open(
      fileData,
      function (json: string) {
        workbook.fromJSON(json, {
          doNotRecalculateAfterLoad: true,
          incrementalLoading: true,
        })
        applyWorkbookSettings(workbook)
        workbook.resumeEvent()
        workbook.resumeCalcService()
        workbook.resumePaint()
      },
      function (e: any) {
        workbook.resumeEvent()
        workbook.resumeCalcService()
        workbook.resumePaint()
      },
      { importPictureAsFloatingObject: true },
    )
  }

  return (
    <div className='excel-viewer'>
      <div style={{ height: '500px', width: '100%' }}>
        <div className='sample-tutorial'>
          <div className='sample-container'>
            <div className='sample-spreadsheets'>
              <SpreadSheets workbookInitialized={initSpread}>
                <Worksheet />
              </SpreadSheets>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ExcelViewer

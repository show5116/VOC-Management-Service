import { useState, useRef, useCallback, useMemo } from 'react'

import Aggrid from '@components/aggrid/Aggrid'
import {
  ColDef,
  CellDoubleClickedEvent,
  RowHeightParams,
} from 'ag-grid-community'

import RequestKindRenderer, {
  requestKindArray,
} from '@components/voc/RequestKindRenderer'
import ImportanceRenderer, {
  importanceArray,
} from '@components/voc/ImportanceRenderer'
import RequestProgressRenderer, {
  requestProgressArray,
} from '@components/voc/ReqestProgressRenderer'
import LobRenderer from '@components/aggrid/LobRenderer'

import IbsTextField from '@components/common/IbsTextField'
import IbsTypeButton from '@components/common/IbsTypeButton'
import IbsButton from '@components/common/IbsButton'
import IbsModal from '@components/common/IbsModal'
import IbsEditor from '@components/common/IbsEditor'

import VocRegistration, {
  VocRegistrationHandle,
} from '@components/voc/VocRegistration'

import { FaMinus, FaPlus } from 'react-icons/fa'
import ibsAxios from '@/utils/ibsAxios'
import { colors } from '@components/styles/colors'

import * as S from './Voc.style'

const testRowData: any[] = [
  {
    requestKind: undefined,
    service: 'SILICONMITUS',
    menu: 'Lot Status',
    vocNumber: 'V0000001',
    content: '<u>Lot Status 신규 페이지 요청</u>',
    requestUser: '김철수',
    requestDate: new Date('2024-02-06'),
    deliveryRequestDate: new Date('2024-04-01'),
    importance: 'second',
    attachment: 'aa.png',
    manager: '육이슬',
    progress: '',
    expectedCompletionDate: undefined,
    completionDate: undefined,
    rowHeight: 40,
  },
  {
    requestKind: 'error',
    service: 'OMS',
    menu: 'Lot History',
    vocNumber: 'V0000002',
    content: 'Lot 이력 검색 시 에러 발생',
    requestUser: '김민수',
    requestDate: new Date('2024-02-06'),
    deliveryRequestDate: new Date('2024-02-12'),
    importance: 'first',
    attachment: 'bb.exl',
    manager: '유영진',
    progress: '',
    expectedCompletionDate: undefined,
    completionDate: undefined,
    updateUser: '',
    updateDate: '',
    rowHeight: 80,
  },
]

const dateValueFormatter = (params: any) => {
  if (!params.value) {
    return ''
  }
  const month = params.value.getMonth() + 1
  const day = params.value.getDate()
  return `${params.value.getFullYear()}-${month < 10 ? '0' + month : month}-${
    day < 10 ? '0' + day : day
  }`
}

const Voc = () => {
  const refs = {
    vocRegistration: useRef<VocRegistrationHandle>(null),
  }

  const [rowData, setRowData] = useState<any[]>(testRowData)
  const [isOpenRegistration, setIsRegistration] = useState(false)
  const [isOpenContent, setIsOpenContent] = useState(false)
  const [content, setContent] = useState('')
  const [rowIndex, setRowIndex] = useState(0)

  const LineHeightCellRenderer = (props: any) => {
    const onClickMinus = () => {
      if (props.data.rowHeight === 40) {
        return
      }
      rowData[props.rowIndex].rowHeight = rowData[props.rowIndex].rowHeight - 40
      props.node.setRowHeight(rowData[props.rowIndex].rowHeight)
      setRowData([...rowData])
    }

    const onClickPlus = () => {
      rowData[props.rowIndex].rowHeight = rowData[props.rowIndex].rowHeight + 40
      props.node.setRowHeight(rowData[props.rowIndex].rowHeight)
      setRowData([...rowData])
    }

    return (
      <div
        style={{
          display: 'flex',
          height: '100%',
          flexDirection: 'column',
          justifyContent: 'space-between',
        }}
      >
        {props.data.rowHeight !== 40 && (
          <span style={{ height: '24px' }}>
            <FaMinus
              size='small'
              cursor='pointer'
              color={colors.darkGrey}
              onClick={onClickMinus}
            />
          </span>
        )}
        <span style={{ height: '24px' }}>
          <FaPlus
            size='small'
            cursor='pointer'
            color={colors.darkGrey}
            onClick={onClickPlus}
          />
        </span>
      </div>
    )
  }

  const onClickRegistButton = () => {
    refs.vocRegistration.current!.getData()
    //ibsAxios.post()
  }

  const onClickContentSaveButton = () => {
    rowData[rowIndex].content = content
    setRowData([...rowData])
    setIsOpenContent(false)
  }

  const onCellDoubleClickedContent = (
    event: CellDoubleClickedEvent<any, any>,
  ) => {
    setContent(event.value)
    event.rowIndex !== null && setRowIndex(event.rowIndex)
    setIsOpenContent(true)
  }

  const getRowId = (params: any) => {
    return params.data.vocNumber
  }

  const getRowHeight = useCallback((params: RowHeightParams<any, any>) => {
    return params.data.rowHeight
  }, [])

  const columns: ColDef[] = [
    {
      checkboxSelection: true,
      width: 40,
    },
    {
      cellRenderer: LineHeightCellRenderer,
      width: 40,
    },
    {
      headerName: '요청 종류',
      field: 'requestKind',
      width: 120,
      filter: 'agTextColumnFilter',
      cellRenderer: RequestKindRenderer,
      cellEditor: 'agRichSelectCellEditor',
      editable: true,
      cellEditorParams: {
        values: requestKindArray,
        cellRenderer: RequestKindRenderer,
        cellEditorPopup: true,
      },
      floatingFilter: true,
    },
    {
      headerName: 'Service',
      field: 'service',
      width: 124,
      filter: 'agTextColumnFilter',
      floatingFilter: true,
    },
    {
      headerName: '메뉴',
      field: 'menu',
      width: 124,
    },
    {
      headerName: '처리 번호',
      field: 'vocNumber',
      width: 124,
    },
    {
      headerName: '내용',
      field: 'content',
      cellRenderer: LobRenderer,
      onCellDoubleClicked: onCellDoubleClickedContent,
    },
    {
      headerName: '요청자',
      field: 'requestUser',
      width: 124,
    },
    {
      headerName: '요청일',
      field: 'requestDate',
      width: 124,
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
    },
    {
      headerName: '납기 요청일',
      field: 'deliveryRequestDate',
      width: 130,
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
    },
    {
      headerName: '중요도',
      field: 'importance',
      cellRenderer: ImportanceRenderer,
      cellEditor: 'agRichSelectCellEditor',
      editable: true,
      cellEditorParams: {
        values: importanceArray,
        cellRenderer: ImportanceRenderer,
        cellEditorPopup: true,
      },
      width: 110,
    },
    {
      headerName: '첨부파일',
      field: 'attachment',
      width: 124,
    },
    {
      headerName: '담당자',
      field: 'manager',
      width: 124,
    },
    {
      headerName: '진행 상황',
      field: 'progress',
      cellRenderer: RequestProgressRenderer,
      cellEditor: 'agRichSelectCellEditor',
      editable: true,
      cellEditorParams: {
        values: requestProgressArray,
        cellRenderer: RequestProgressRenderer,
        cellEditorPopup: true,
      },
      width: 124,
      floatingFilter: true,
    },
    {
      headerName: '완료 예정일',
      field: 'expectedCompletionDate',
      width: 140,
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
    },
    {
      headerName: '완료일',
      field: 'completionDate',
      width: 124,
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
    },
    {
      headerName: '수정자',
      field: 'updateUser',
      width: 124,
    },
    {
      headerName: '수정일',
      field: 'updateDate',
      width: 124,
    },
  ]

  return (
    <S.Container>
      <IbsModal
        open={isOpenRegistration}
        setOpen={setIsRegistration}
        width='800px'
        height='500px'
        title='VoC 등록'
        button={
          <IbsButton
            formControllStyle={{ marginRight: 10 }}
            width={70}
            onClick={onClickRegistButton}
          >
            등록
          </IbsButton>
        }
      >
        <VocRegistration ref={refs.vocRegistration} />
      </IbsModal>
      <IbsModal
        open={isOpenContent}
        setOpen={setIsOpenContent}
        width='800px'
        height='500px'
        title='VoC 내용'
        button={
          <IbsButton
            formControllStyle={{ marginRight: 10 }}
            width={70}
            onClick={onClickContentSaveButton}
          >
            변경
          </IbsButton>
        }
      >
        <IbsEditor
          width='800px'
          height='400px'
          content={content}
          setContent={setContent}
        />
      </IbsModal>
      <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
        <IbsTextField label='검색어' />
        <IbsTypeButton
          buttontype={'search'}
          formControllStyle={{ marginLeft: 10 }}
        />
        <IbsButton
          formControllStyle={{
            marginLeft: 10,
          }}
          width={70}
          onClick={() => setIsRegistration(true)}
        >
          등록
        </IbsButton>
        <IbsButton
          formControllStyle={{
            marginLeft: 10,
          }}
          width={70}
        >
          저장
        </IbsButton>
      </div>
      <Aggrid
        columns={columns}
        defaultRowData={rowData}
        gridHeight={'90%'}
        gridOptions={{}}
        rowStyle={{ '--ag-line-height': '24px' }}
        getRowHeight={getRowHeight}
        getRowId={getRowId}
      />
    </S.Container>
  )
}

export default Voc

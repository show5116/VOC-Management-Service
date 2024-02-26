import { useState, useRef, useCallback, useEffect } from 'react'

import { useAppDispatch } from '@reducers/index'
import { showAlert } from '@reducers/alertReducer/alertReducer'

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
import AttachmentRenderer from '@components/aggrid/AttachmentRenderer'

import IbsTextField from '@components/common/IbsTextField'
import IbsTypeButton from '@components/common/IbsTypeButton'
import IbsButton from '@components/common/IbsButton'
import IbsModal from '@components/common/IbsModal'
import IbsEditor from '@components/common/IbsEditor'

import VocRegistration, {
  VocRegistrationHandle,
} from '@components/voc/VocRegistration'

import { FaMinus, FaPlus } from 'react-icons/fa'
import { colors } from '@components/styles/colors'
import * as S from './Voc.style'

import ibsAxios, { multipartConfig } from '@/utils/ibsAxios'
import { parse } from 'date-fns'
import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
import IbsDatePicker, {
  IbsDatePickerHandel,
} from '@/components/common/IbsDatePicker'

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
    dateKindCombo: useRef<IbsComboboxHandle>(null),
    startDatePicker: useRef<IbsDatePickerHandel>(null),
    endDatePicker: useRef<IbsDatePickerHandel>(null),
  }

  const [rowData, setRowData] = useState<any[]>([])
  const [isOpenRegistration, setIsRegistration] = useState(false)
  const [isOpenContent, setIsOpenContent] = useState(false)
  const [content, setContent] = useState('')
  const [rowIndex, setRowIndex] = useState(0)

  const dispatch = useAppDispatch()

  useEffect(() => {
    getRowData()
  }, [])

  const LineHeightCellRenderer = (props: any) => {
    const onClickMinus = () => {
      if (props.data.rowHeight === 40) {
        return
      }
      console.log(rowData)
      rowData[props.rowIndex].rowHeight = rowData[props.rowIndex].rowHeight - 40
      props.node.setRowHeight(rowData[props.rowIndex].rowHeight)
      setRowData([...rowData])
    }

    const onClickPlus = () => {
      console.log(rowData)
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
    const formData = refs.vocRegistration.current!.getFormData()

    const success = (response: any) => {
      dispatch(
        showAlert({
          isOpen: true,
          message: '성공하였습니다',
          type: 'success',
        }),
      )

      setIsRegistration(false)
      getRowData()
    }

    const config = multipartConfig

    ibsAxios.post('/voc', formData, config).then(success)
  }

  const onClickContentSaveButton = () => {
    console.log(rowData)
    rowData[rowIndex].requirement = content
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

  const getRowData = useCallback(() => {
    const success = (response: any) => {
      setRowData(
        response.data.map((data: any) => ({
          ...data,
          rowHeight: 40,
          issueDate:
            data.issueDate &&
            parse(data.issueDate, 'yyyyMMddHHmmss', new Date()),
          requiredResponseDate:
            data.requiredResponseDate &&
            parse(data.requiredResponseDate, 'yyyyMMddHHmmss', new Date()),
          closedDate:
            data.closedDate &&
            parse(data.closedDate, 'yyyyMMddHHmmss', new Date()),
          updateDate:
            data.updateDate &&
            parse(data.updateDate, 'yyyyMMddHHmmss', new Date()),
        })),
      )
    }

    ibsAxios.get('/voc').then(success)
  }, [])

  const getRowId = (params: any) => {
    return params.data.qmsNumber + params.data.revisionNo
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
      field: 'classification',
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
      headerName: 'System',
      field: 'systemName',
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
      field: 'qmsNumber',
      width: 124,
    },
    {
      headerName: '내용',
      field: 'requirement',
      cellRenderer: LobRenderer,
      onCellDoubleClicked: onCellDoubleClickedContent,
    },
    {
      headerName: '요청자',
      field: 'customer',
      width: 124,
    },
    {
      headerName: '요청일',
      field: 'issueDate',
      width: 124,
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
    },
    {
      headerName: '납기 요청일',
      field: 'requiredResponseDate',
      width: 130,
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
    },
    {
      headerName: '중요도',
      field: 'remark',
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
      field: 'fileName',
      cellRenderer: AttachmentRenderer,
      width: 124,
    },
    {
      headerName: '담당자',
      field: 'personInCharge',
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
      field: 'closedDate',
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
      <S.Menu>
        <S.SearchCondition>
          <IbsCombobox
            ref={refs.dateKindCombo}
            label='기간 종류'
            width='160px'
            defaultItems={[
              { value: 'new', displayValue: '요청일' },
              { value: 'error', displayValue: '납기 요청일' },
              {
                value: 'improvement',
                displayValue: '완료 예정일',
              },
              {
                value: 'improvement',
                displayValue: '완료일',
              },
            ]}
          />
          <IbsDatePicker ref={refs.startDatePicker} type='date' />
          <span>-</span>
          <IbsDatePicker ref={refs.endDatePicker} type='date' />
          <IbsTextField label='System' />
        </S.SearchCondition>
        <S.Buttons>
          <IbsTypeButton buttontype={'search'} />
          <IbsButton width={70} onClick={() => setIsRegistration(true)}>
            등록
          </IbsButton>
          <IbsButton width={70}>저장</IbsButton>
        </S.Buttons>
      </S.Menu>
      <Aggrid
        columns={columns}
        defaultRowData={rowData}
        gridHeight={'90%'}
        gridOptions={{}}
        getRowHeight={getRowHeight}
        getRowId={getRowId}
      />
    </S.Container>
  )
}

export default Voc

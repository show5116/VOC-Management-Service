import { useState, useRef, useCallback, useEffect } from 'react'

import { useAppDispatch } from '@reducers/index'
import { showAlert } from '@reducers/alertReducer/alertReducer'

import Aggrid from '@components/aggrid/Aggrid'
import { AgGridReact } from 'ag-grid-react'
import {
  ColDef,
  RowHeightParams,
  CellDoubleClickedEvent,
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
import { firstDayThisMonth, vocBaseFormat } from '@/utils/dateUtils'
import VocModification from '@/components/voc/VocModification'

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
    systemCombo: useRef<IbsComboboxHandle>(null),
    gridRef: useRef<AgGridReact>(null),
  }

  const [rowData, setRowData] = useState<any[]>([])
  const [isOpenRegistration, setIsRegistration] = useState(false)
  const [isOpenContent, setIsOpenContent] = useState(false)
  const [modifyData, setModifyData] = useState<any>()

  const dispatch = useAppDispatch()

  useEffect(() => {
    setTimeout(getRowData, 1000)
    ibsAxios.get('/combo-box/system-name').then((response: any) => {
      refs.systemCombo.current!.setItems(response.data)
    })
    /*ibsAxios.get('/combo-box/plant').then((response: any) => {
      refs.plantCombo.current!.setItems(response.data)
    })
    ibsAxios.get('/combo-box/person-in-charge').then((response: any) => {
      refs.personInChargeCombo.current!.setItems(response.data)
    })*/
  }, [])

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

    ibsAxios.post('/voc', formData, multipartConfig).then(success)
  }

  const onCellDoubleClicked = (event: CellDoubleClickedEvent<any, any>) => {
    setModifyData(event.data)
    setIsOpenContent(true)
  }

  const getRowData = useCallback(() => {
    const config = {
      params: {
        dateKind: refs.dateKindCombo.current!.getSelectedValues()[0],
        startDate: vocBaseFormat(
          refs.startDatePicker.current!.getDate().startDate,
        ),
        endDate: vocBaseFormat(refs.endDatePicker.current!.getDate().startDate),
        system: refs.systemCombo.current!.getSelectedValues()[0],
      },
    }

    const success = (response: any) => {
      setRowData(
        response.data.map((data: any) => ({
          ...data,
          rowHeight: 40,
          issueDate: data.issueDate
            ? parse(data.issueDate, 'yyyyMMddHHmmss', new Date())
            : undefined,
          requiredResponseDate: data.requiredResponseDate
            ? parse(data.requiredResponseDate, 'yyyyMMddHHmmss', new Date())
            : undefined,
          expectedCompletionDate: data.expectedCompletionDate
            ? parse(data.expectedCompletionDate, 'yyyyMMddHHmmss', new Date())
            : undefined,
          closedDate: data.closedDate
            ? parse(data.closedDate, 'yyyyMMddHHmmss', new Date())
            : undefined,
          updateDate: data.updateDate
            ? parse(data.updateDate, 'yyyyMMddHHmmss', new Date())
            : undefined,
        })),
      )
    }

    ibsAxios.get('/voc', config).then(success)
  }, [])

  const getRowId = (params: any) => {
    return params.data.qmsNumber + params.data.revisionNo
  }

  const getRowHeight = useCallback((params: RowHeightParams<any, any>) => {
    return params.data.rowHeight
  }, [])

  const columns: ColDef[] = [
    {
      cellRenderer: LineHeightCellRenderer,
      minWidth: 40,
      maxWidth: 40,
    },
    {
      headerName: '요청 종류',
      field: 'classification',
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
      width: 120,
    },
    {
      headerName: 'System',
      field: 'systemName',
      filter: 'agTextColumnFilter',
      floatingFilter: true,
      width: 124,
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
      cellStyle: { cursor: 'pointer' },
      onCellDoubleClicked: onCellDoubleClicked,
    },
    {
      headerName: '내용',
      field: 'requirement',
      cellRenderer: LobRenderer,
      cellStyle: { cursor: 'pointer' },
      onCellDoubleClicked: onCellDoubleClicked,
    },
    {
      headerName: '요청자',
      field: 'customer',
      width: 124,
    },
    {
      headerName: '요청일',
      field: 'issueDate',
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
      width: 124,
    },
    {
      headerName: '납기 요청일',
      field: 'requiredResponseDate',
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
      width: 130,
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
      cellRenderer: (props: any) => (
        <AttachmentRenderer getRowData={getRowData} {...props} />
      ),
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
      floatingFilter: true,
      width: 124,
    },
    {
      headerName: '완료 예정일',
      field: 'expectedCompletionDate',
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
      width: 140,
    },
    {
      headerName: '완료일',
      field: 'closedDate',
      cellEditor: 'agDateCellEditor',
      editable: true,
      valueFormatter: dateValueFormatter,
      width: 124,
    },
    {
      headerName: '수정자',
      field: 'updateUser',
      width: 124,
    },
    {
      headerName: '수정일',
      field: 'updateDate',
      valueFormatter: dateValueFormatter,
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
        title='VoC 수정'
        button={
          <IbsButton
            formControllStyle={{ marginRight: 10 }}
            width={70}
            //onClick={onClickContentSaveButton}
          >
            변경
          </IbsButton>
        }
      >
        <VocModification data={modifyData} />
      </IbsModal>
      <S.Menu>
        <S.SearchCondition>
          <IbsCombobox
            ref={refs.dateKindCombo}
            label='기간 종류'
            width='164px'
            defaultItems={[
              { value: 'ISSUE_DATE', displayValue: '요청일' },
              { value: 'REQUIRED_RESPONSE_DATE', displayValue: '납기 요청일' },
              {
                value: 'EXPECTED_COMPLETION_DATE',
                displayValue: '완료 예정일',
              },
              {
                value: 'CLOSED_DATE',
                displayValue: '완료일',
              },
            ]}
          />
          <IbsDatePicker
            ref={refs.startDatePicker}
            type='date'
            startDate={firstDayThisMonth()}
          />
          <span>-</span>
          <IbsDatePicker ref={refs.endDatePicker} type='date' />
          <IbsCombobox
            ref={refs.systemCombo}
            label='System'
            width='150px'
            defaultItems={[{ displayValue: '', value: '' }]}
          />
        </S.SearchCondition>
        <S.Buttons>
          <IbsTypeButton buttontype={'search'} onClick={getRowData} />
          <IbsButton width={70} onClick={() => setIsRegistration(true)}>
            등록
          </IbsButton>
        </S.Buttons>
      </S.Menu>
      <Aggrid
        gridRef={refs.gridRef}
        columns={columns}
        rowData={rowData}
        gridHeight={'90%'}
        gridOptions={{}}
        getRowHeight={getRowHeight}
        getRowId={getRowId}
      />
    </S.Container>
  )
}

export default Voc

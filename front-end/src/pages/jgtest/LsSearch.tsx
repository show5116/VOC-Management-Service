import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
import IbsDatePicker from '@/components/common/IbsDatePicker'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@/components/common/IbsTextField'
import { Button } from '@mui/material'
import axios from 'axios'
import { useEffect, useRef, useState } from 'react'
import { AgGridReact } from 'ag-grid-react'
import { ColDef } from 'ag-grid-community'
import 'ag-grid-community/styles/ag-grid.css' // Core CSS
import 'ag-grid-community/styles/ag-theme-quartz.css' // Theme
import LsManager from './LsManager'

interface Item {
  displayValue: string
  value: string
  description?: string
}
const LsSearch = () => {
  const [productColumnDefs, setProductColumnDefs] = useState<ColDef[]>([])

  const [gridApi, setGridApi] = useState(null)
  const [gridColumnApi, setGridColumnApi] = useState(null)
  const [isLsManagerOpen, setIsLsManagerOpen] = useState(false)
  const [selectedRowData, setSelectedRowData] = useState<any>(null)
  const [rowData, setRowData] = useState<any>([])
  const refs = {
    divisionCombo: useRef<IbsComboboxHandle>(null),
    departmentCombo: useRef<IbsComboboxHandle>(null),
    issueCombo: useRef<IbsComboboxHandle>(null),
    causeCombo: useRef<IbsComboboxHandle>(null),
    processCombo: useRef<IbsComboboxHandle>(null),
    supplierCombo: useRef<IbsComboboxHandle>(null),
    statusCombo: useRef<IbsComboboxHandle>(null),
    lsNumberText: useRef<IbsTextFieldHandle>(null),
    titleText: useRef<IbsTextFieldHandle>(null),
    productText: useRef<IbsTextFieldHandle>(null),
  }
  const init = () => {
    getDivisionCombo()
    getDepartment()
    getissueCombo()
    getCauseCombo()
    getProcessCombo()
    getSupplierCombo('')
    getStatusCombo()
  }
  const getDivisionCombo = () => {
    const data = {
      plant: 'SILICONMITUS',
      tableName: 'QMS_LS_FAULTY_STEP',
      codeView: false,
      orderBy: 'CODE_NAME ASC',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.codeName,
        }))
        refs.divisionCombo.current?.setItems([
          { displayValue: '', value: '' },
          ...newItems,
        ])
      })
    }

    axios
      .post(
        'http://localhost:8081/api/syscode/system-code-list/code-view/orderby',
        data,
      )
      .then(success)
  }
  const getissueCombo = () => {
    const data = {
      plant: 'SILICONMITUS',
      tableName: 'QMS_LS_FAULTY_TYPE',
      codeView: false,
      orderBy: 'CODE_NAME ASC',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.codeName,
        }))
        refs.issueCombo.current?.setItems([
          { displayValue: '', value: '' },
          ...newItems,
        ])
      })
    }

    axios
      .post(
        'http://localhost:8081/api/syscode/system-code-list/code-view/orderby',
        data,
      )
      .then(success)
  }
  const getCauseCombo = () => {
    const data = {
      plant: 'SILICONMITUS',
      tableName: 'QMS_LS_CAUSE_TYPE',
      codeView: false,
      orderBy: 'CODE_NAME ASC',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.codeName,
        }))
        refs.causeCombo.current?.setItems([
          { displayValue: '', value: '' },
          ...newItems,
        ])
      })
    }

    axios
      .post(
        'http://localhost:8081/api/syscode/system-code-list/code-view/orderby',
        data,
      )
      .then(success)
  }
  const getProcessCombo = () => {
    const data = {
      plant: 'SILICONMITUS',
      order: 'QMS_CAUSE_TYPE',
      group: 'SPECIAL_DATA1',
      orderBy: 'CODE_SEQ',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.groupData,
        }))
        refs.processCombo.current?.setItems([
          { displayValue: '', value: '' },
          ...newItems,
        ])
      })
    }

    axios
      .post('http://localhost:8081/api/common/desc-code-group', data)
      .then(success)
  }
  const getSupplierCombo = (process: string) => {
    const data = {
      plant: 'SILICONMITUS',
      process: process,
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.subctrtDesc,
          value: e.subctrtCode,
        }))
        refs.supplierCombo.current?.setItems(newItems)
      })
    }

    axios
      .post('http://localhost:8081/api/common/supplier-list/process', data)
      .then(success)
  }
  const getDepartment = () => {
    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.deptName,
          value: e.deptId,
        }))
        refs.departmentCombo.current?.setItems([
          { displayValue: '', value: '' },
          ...newItems,
        ])
      })
    }

    axios.post('http://localhost:8081/api/common/dept').then(success)
  }

  const getStatusCombo = () => {
    const data = {
      plant: 'SILICONMITUS',
      tableName: 'QMS_CLOSING_FLAG',
      codeView: false,
      orderBy: 'CODE_SEQ ASC',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.codeName,
        }))
        refs.statusCombo.current?.setItems([
          { displayValue: '', value: '' },
          ...newItems,
        ])
      })
    }

    axios
      .post(
        'http://localhost:8081/api/syscode/system-code-list/code-view/orderby',
        data,
      )
      .then(success)
  }
  const onClickSearch = () => {
    const data = {
      plant: 'SILICONMITUS',
      systemName: 'LS',
      title: refs.titleText.current?.getValue(),
      isNumber: refs.lsNumberText.current?.getValue(),
      division: refs.divisionCombo.current?.getSelectedValues()[0],
      department: refs.departmentCombo.current?.getSelectedValues()[0],
      issue: refs.issueCombo.current?.getSelectedValues()[0],
      process: refs.processCombo.current?.getSelectedValues()[0],
      supplier: refs.supplierCombo.current?.getSelectedValues()[0],
      cause: refs.causeCombo.current?.getSelectedValues()[0],
      product: refs.productText.current?.getValue(),
      startDate: '20110101',
      endDate: '20250101',
      status: refs.statusCombo.current?.getSelectedValues()[0],
      userId: 'ibs',
      chkMyDoc: false,
    }

    const success = (response: any) => {
      console.log(response.data)
      setRowData(response.data)
    }

    axios.post('http://localhost:8081/api/ls/search', data).then(success)
  }
  const onChangeProcessCombo = () => {
    getSupplierCombo(refs.processCombo.current?.getSelectedValues()[0] ?? '')
  }

  const columnDefs: ColDef[] = [
    { headerName: '관리번호', field: 'qmsNumber' },
    { headerName: '등록일', field: 'regDate' },
    { headerName: '제목', field: 'issueTitle' },
    { headerName: '부문', field: 'deptBu' },
    { headerName: '부서', field: 'deptName' },
    { headerName: '제품명', field: 'product' },
    { headerName: '불량 스텝', field: 'faultyStep' },
    { headerName: '불량 분류', field: 'issueCategory' },
    { headerName: '원인 공정', field: 'process' },
    { headerName: '외주처', field: 'supplier' },
    { headerName: '원인 분류', field: 'cause' },
    { headerName: '불량 현상', field: 'issueSummary' },
    { headerName: '원인', field: 'causeComment' },
    { headerName: '대책', field: 'measures' },
    { headerName: 'Checklist', field: 'checkList' },
    { headerName: 'Affected Area', field: 'applyRange' },
    { headerName: 'Key word', field: 'keyWord' },
    { headerName: '담당부서', field: 'functionType' },
    { headerName: 'Remark', field: 'remark' },
    { headerName: '등록자', field: 'regUser' },
    { headerName: '상태', field: 'status' },
  ]
  const onGridReady = (params: any) => {
    setGridApi(params.api)
    setGridColumnApi(params.columnApi)
  }

  const onRowDoubleClicked = (event: any) => {
    console.log(event)
    setSelectedRowData(event.data)
    setIsLsManagerOpen(true)
  }

  const renderLsManagerModal = () => {
    if (!isLsManagerOpen || !selectedRowData) return null

    return (
      <LsManager
        plant='SILICONMITUS'
        qmsNumber={selectedRowData.qmsNumber}
        systemName='LS'
        open={isLsManagerOpen}
        onClose={() => setIsLsManagerOpen(false)} // 모달 닫기 함수
      />
    )
  }

  useEffect(() => {
    init()
  }, [])
  return (
    <>
      <div>
        <IbsTextField
          label={'관리번호'}
          formControllStyle={{ width: '100%', margin: '10px 0' }}
          InputLabelProps={{ shrink: true }}
          ref={refs.lsNumberText}
        />

        <IbsTextField
          label={'제목'}
          formControllStyle={{ width: '100%', margin: '10px 0' }}
          InputLabelProps={{ shrink: true }}
          ref={refs.titleText}
        />
        <IbsCombobox
          label={'불량 스텝'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.divisionCombo}
        />
        <IbsCombobox
          label={'부서'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.departmentCombo}
        />
        <IbsDatePicker />
      </div>
      <div>
        <IbsCombobox
          label={'불량 분류'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.issueCombo}
        />
        <IbsCombobox
          label={'원인 공정'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.processCombo}
          onChangeCombobox={onChangeProcessCombo}
        />
        <IbsCombobox
          label={'원인 분류'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.causeCombo}
        />
        <IbsCombobox
          label={'외주처'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.supplierCombo}
        />
        <IbsTextField
          label={'제품'}
          formControllStyle={{ width: '100%', margin: '10px 0' }}
          InputLabelProps={{ shrink: true }}
          ref={refs.productText}
        />
        <IbsCombobox
          label={'작업 상태'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.statusCombo}
        />
      </div>
      <div>
        <Button onClick={onClickSearch}>{'조회'}</Button>
      </div>
      <div
        className={'ag-theme-quartz'}
        style={{ width: '100%', height: '100%' }}
      >
        <AgGridReact
          onGridReady={onGridReady}
          rowData={rowData}
          columnDefs={columnDefs}
          onRowDoubleClicked={onRowDoubleClicked}
        ></AgGridReact>
      </div>
      {renderLsManagerModal()}
    </>
  )
}

export default LsSearch

import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
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
interface Item {
  displayValue: string
  value: string
  description?: string
}
const CipSearch = () => {
  const [gridApi, setGridApi] = useState(null)
  const [gridColumnApi, setGridColumnApi] = useState(null)

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
    projectTypeCombo: useRef<IbsComboboxHandle>(null),
  }
  const init = () => {
    getDivisionCombo()
    getDepartment()
    getissueCombo()
    getCauseCombo()
    getProcessCombo()
    getSupplierCombo('')
    getStatusCombo()
    getProjectTypeCombo()
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
      order: 'QMS_PROCESS_TYPE',
      group: 'CODE_GROUP2',
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
    let data = {}

    if (process === '') {
      data = {
        plant: 'SILICONMITUS',
        processes: [],
      }
    } else {
      data = {
        plant: 'SILICONMITUS',
        processes: process.split(','),
      }
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
      .post('http://localhost:8081/api/common/supplier-list', data)
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
      orderBy: 'description',
      ascYn: true,
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
      .post('http://localhost:8081/api/syscode/system-code-list', data)
      .then(success)
  }
  const getProjectTypeCombo = () => {
    const data = {
      plant: 'SILICONMITUS',
      tableName: 'QMS_PROJECT_TYPE',
      codeView: false,
      orderBy: 'DESCRIPTION ASC',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.codeName,
        }))
        refs.projectTypeCombo.current?.setItems(newItems)
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
      systemName: 'CIP',
      title: refs.titleText.current?.getValue(),
      projectTypes: refs.projectTypeCombo.current?.getSelectedValues(),
      dept: refs.departmentCombo.current?.getSelectedValues()[0],
      process: refs.processCombo.current?.getSelectedValues()[0],
      supplier: refs.supplierCombo.current?.getSelectedValues()[0],
      startDate: '20110101',
      endDate: '20250101',
      status: refs.statusCombo.current?.getSelectedValues()[0],
      userId: 'ibs',
    }

    const success = (response: any) => {
      console.log(response.data)
      setRowData(response.data)
    }

    axios.post('http://localhost:8081/api/cip/search', data).then(success)
  }
  const onChangeProcessCombo = () => {
    getSupplierCombo(refs.processCombo.current?.getSelectedValues()[0] ?? '')
  }

  const [rowData, setRowData] = useState([])

  const columnDefs: ColDef[] = [
    { headerName: 'CIP NO', field: 'qmsNumber' },
    { headerName: '등록일', field: 'regDate' },
    { headerName: '공정', field: 'process' },
    { headerName: '외주처', field: 'supplier' },
    { headerName: '제목', field: 'projectTitle' },
    { headerName: '분류', field: 'projectType' },
    { headerName: '상태', field: 'status' },
    { headerName: '완료일', field: 'closedDate' },
    { headerName: '등록자', field: 'userName' },
    { headerName: '부서', field: 'deptName' },
  ]

  const onGridReady = (params: any) => {
    setGridApi(params.api)
    setGridColumnApi(params.columnApi)
  }

  useEffect(() => {
    init()
  }, [])
  return (
    <>
      <div>
        <IbsTextField
          label={'CIP No'}
          formControllStyle={{ width: '100%', margin: '10px 0' }}
          InputLabelProps={{ shrink: true }}
          ref={refs.lsNumberText}
          toCaseType={'upper'}
        />
        <IbsCombobox
          label={'작업 상태'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.statusCombo}
        />
        <IbsTextField
          label={'제목'}
          formControllStyle={{ width: '100%', margin: '10px 0' }}
          InputLabelProps={{ shrink: true }}
          ref={refs.titleText}
        />
        <IbsCombobox
          label={'공정'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.processCombo}
          onChangeCombobox={onChangeProcessCombo}
        />
        <IbsTextField
          label={'등록자'}
          formControllStyle={{ width: '100%', margin: '10px 0' }}
          InputLabelProps={{ shrink: true }}
          ref={refs.productText}
        />
      </div>
      <div>
        <IbsCombobox
          label={'외주처'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.supplierCombo}
        />
        <IbsTextField
          label={'부서'}
          formControllStyle={{ width: '100%', margin: '10px 0' }}
          InputLabelProps={{ shrink: true }}
          ref={refs.productText}
        />
        <IbsCombobox
          label={'분류'}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={200}
          defaultItems={[{ displayValue: '', value: '' }]}
          ref={refs.projectTypeCombo}
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
        ></AgGridReact>
      </div>
    </>
  )
}

export default CipSearch

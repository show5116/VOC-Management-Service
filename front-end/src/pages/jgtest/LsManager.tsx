import Modal from '@mui/material/Modal'
import Box from '@mui/material/Box'
import { Button, Checkbox, FormControl } from '@mui/material'
import axios from 'axios'
import { useEffect, useLayoutEffect, useRef, useState } from 'react'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@/components/common/IbsTextField'
import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
import { AgGridReact } from 'ag-grid-react'
import { ColDef, ColGroupDef, GridApi } from 'ag-grid-community'
import './LsManager.scss'
const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '90%',
  height: '80%',
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
  overflowY: 'auto',
}

interface LsManagerProps {
  plant: string
  qmsNumber: string
  systemName: string
  open: boolean
  onClose: () => void
}
interface Item {
  displayValue: string
  value: string
  description?: string
}

const LsManager = ({
  plant,
  qmsNumber,
  systemName,
  open,
  onClose,
}: LsManagerProps) => {
  const [productRowData, setProductRowData] = useState<any>([])
  const [productGridApi, setProductGridApi] = useState<GridApi | null>()
  const [productGridColumnApi, setProductGridColumnApi] = useState<any>(null)
  const [detailRowData, setDetailRowData] = useState<any>([])
  const [detailGridApi, setDetailGridApi] = useState<GridApi | null>()
  const [detailGridColumnApi, setDetailGridColumnApi] = useState(null)
  const [detailColumnDefs, setDetailColumnDefs] =
    useState<(ColDef | ColGroupDef)[]>()
  const [fileRowData, setFileRowData] = useState<any>([])
  const [fileGridApi, setFileGridApi] = useState(null)
  const [fileGridColumnApi, setFileGridColumnApi] = useState(null)
  const [processDetail, setProcessDetail] = useState<any>([])
  const [functionType, setFunctionType] = useState<any>([])

  const refs = {
    regUserText: useRef<IbsTextFieldHandle>(null),
    regUserNameText: useRef<IbsTextFieldHandle>(null),
    deptNameText: useRef<IbsTextFieldHandle>(null),
    qmsNumberText: useRef<IbsTextFieldHandle>(null),
    issueTitleText: useRef<IbsTextFieldHandle>(null),
    productCombo: useRef<IbsComboboxHandle>(null),
    divisionCombo: useRef<IbsComboboxHandle>(null),
    departmentCombo: useRef<IbsComboboxHandle>(null),
    issueCombo: useRef<IbsComboboxHandle>(null),
    causeCombo: useRef<IbsComboboxHandle>(null),
    processCombo: useRef<IbsComboboxHandle>(null),
    supplierCombo: useRef<IbsComboboxHandle>(null),
    titleText: useRef<IbsTextFieldHandle>(null),
    productText: useRef<IbsTextFieldHandle>(null),
    issueSummaryText: useRef<IbsTextFieldHandle>(null),
    measuresSummaryText: useRef<IbsTextFieldHandle>(null),
    causeSummaryText: useRef<IbsTextFieldHandle>(null),
    productGridRef: useRef<GridApi<any>>(null),
  }

  const defaultRevisionNo = '01'

  const getProductList = () => {
    const data = {
      plant: 'SILICONMITUS',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.codeName,
        }))
        refs.productCombo.current?.setItems(newItems)
      })
    }

    axios
      .post('http://localhost:8081/api/common/product-list', data)
      .then(success)
  }

  const getViewSelect = () => {
    const data = {
      plant: plant,
      qmsNumber: qmsNumber,
      systemName: systemName,
    }

    const success = (response: any) => {
      console.log(response.data)
      refs.regUserText.current?.setValue(response.data.regUser)
      refs.regUserNameText.current?.setValue(response.data.regUserName)
      refs.deptNameText.current?.setValue(response.data.deptName)
      refs.qmsNumberText.current?.setValue(qmsNumber)
      refs.issueTitleText.current?.setValue(response.data.issueTitle)
      refs.divisionCombo.current?.setValue(response.data.division)
      refs.issueCombo.current?.setValue(response.data.issueCategory)
      refs.causeCombo.current?.setValue(response.data.cause)
      refs.supplierCombo.current?.setValue(response.data.supplier)
      refs.processCombo.current?.setValue(response.data.process)
      refs.issueSummaryText.current?.setValue(response.data.issueSummary)
      refs.causeSummaryText.current?.setValue(response.data.causeComment)
      refs.measuresSummaryText.current?.setValue(response.data.measures)
    }

    axios.post('http://localhost:8081/api/ls/view-select', data).then(success)
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
          value: e.codeName,
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
        refs.causeCombo.current?.setItems(newItems)
      })
    }

    axios
      .post(
        'http://localhost:8081/api/syscode/system-code-list/code-view/orderby',
        data,
      )
      .then(success)
  }
  const getSelectProductDieList = (product: string[]) => {
    const data = {
      plant: 'SILICONMITUS',
      products: product,
    }

    const success = (response: any) => {
      console.log(response.data)
      productGridApi?.setGridOption('rowData', response.data)
    }

    axios
      .post('http://localhost:8081/api/ls/product-die-list', data)
      .then(success)
  }
  const getProcessDetail = () => {
    const data = {
      plant: 'SILICONMITUS',
      order: 'QMS_PROCESS_TYPE',
      group: 'CODE_GROUP2',
      orderBy: 'CODE_SEQ',
    }

    const success = (response: any) => {
      if (response?.data) {
        const addColumnDefs = response.data.map((m: any) => ({
          headerName: m.description,
          field: m.codeName,
          cellRenderer: checkboxRenderer,
          valueGetter: (params: any) => params.data[m.codeName] || null,
        }))

        const newColumnDef = [
          {
            headerName: 'delete',
            field: 'delete',
            cellRenderer: (params: any) => (
              <DeleteButton api={params.api} node={params.node} />
            ),
          },
          { headerName: 'Checklist', field: 'CHECK_LIST', editable: true },
          { headerName: 'Keyword', field: 'KEY_WORD', editable: true },
          {
            headerName: '담당 부서',
            field: 'FUNCTION_TYPE',
            cellEditor: 'agSelectCellEditor',
            cellEditorParams: {
              values: functionType.map((ft: any) => ft.codeName), // 콤보박스에는 value만 표시
            },
            valueFormatter: (params: any) => {
              const found = functionType.find(
                (ft: any) => ft.codeName === params.value,
              )
              return found ? found.description : ''
            },

            editable: true,
          },
          {
            headerName: 'Affected Area',
            children: addColumnDefs,
          },
          { headerName: 'Remark', field: 'remark' },
        ]

        setDetailColumnDefs(newColumnDef)
        getDetail(response.data.map((m: any) => m.codeName))
      }
    }

    axios
      .post('http://localhost:8081/api/common/desc-code-group', data)
      .then(success)
  }
  const getFunctionType = () => {
    const data = {
      plant: 'SILICONMITUS',
      tableName: 'FUNCTION_TYPE',
    }

    const success = (response: any) => {
      console.log(response.data)
      setFunctionType(response.data)
    }

    axios
      .post('http://localhost:8081/api/syscode/system-code-list', data)
      .then(success)
  }
  const getDetail = (processDetail: string[]) => {
    const data = {
      plant: 'SILICONMITUS',
      systemName: 'LS',
      qmsNumber: qmsNumber,
      revisionNo: defaultRevisionNo,
      processList: processDetail,
    }

    const success = (response: any) => {
      setDetailRowData(response.data)
    }

    axios.post('http://localhost:8081/api/ls/detail', data).then(success)
  }
  const checkboxRenderer = (params: any) => {
    const checked = params.value === 'Y'
    return <Checkbox defaultChecked={checked} />
  }

  const onChangeProcessCombo = () => {
    getSupplierCombo(refs.processCombo.current?.getSelectedValues()[0] ?? '')
  }
  const onChangeProductComobo = () => {
    getSelectProductDieList(
      refs.productCombo.current?.getSelectedValues() ?? [],
    )
  }
  const addDetailGridRow = () => {
    setDetailRowData([
      ...detailRowData,
      {
        regDate: '',
      },
    ])
  }

  const DeleteButton = (api: any, node: any) => {
    const handleDelete = () => {
      const selectedIndex = api?.node.rowIndex
      let tempRowData = [...detailRowData]
      tempRowData.splice(selectedIndex, 1)
      setDetailRowData(tempRowData)
    }

    return <Button onClick={handleDelete}>Delete</Button>
  }

  const proudctColumnDefs: (ColDef | ColGroupDef)[] = [
    {
      headerName: 'Product Name',
      field: 'device',
      rowGroup: true,
    },
    { headerName: 'Die Information', field: 'dieType' },
    { headerName: 'Fab', field: 'fab' },
    { headerName: 'Tech', field: 'tech' },
    { headerName: 'PKG Type', field: 'pkgType' },
    { headerName: 'PKG Size', field: 'pkgSize' },
    { headerName: 'Application1', field: 'application1' },
    { headerName: 'Application2', field: 'application2' },
  ]

  const fileColumnDefs: ColDef[] = [
    { headerName: '삭제', field: 'qmsNumber' },
    { headerName: '파일명', field: 'regDate' },
    { headerName: '파일 설명', field: 'issueTitle' },
  ]
  const onProductGridReady = (params: any) => {
    setProductGridApi(params.api)
    setProductGridColumnApi(params.columnApi)
  }
  const onDetailGridReady = (params: any) => {
    setDetailGridApi(params.api)
    setDetailGridColumnApi(params.columnApi)
  }
  const onFileGridReady = (params: any) => {
    setFileGridApi(params.api)
    setFileGridColumnApi(params.columnApi)
  }

  useLayoutEffect(() => {
    const fetchData = async () => {
      try {
        await getProductList()
        await getDivisionCombo()
        await getissueCombo()
        await getProcessCombo()
        await getSupplierCombo('')
        await getCauseCombo()
        await getProcessDetail()
        await getFunctionType()
        await getViewSelect()
      } catch (error) {
        console.error('에러 발생:', error)
      }
    }

    fetchData()
  }, [])

  return (
    <Modal
      open={open}
      onClose={onClose}
      aria-labelledby='ls-manager-modal'
      aria-describedby='ls-manager-modal-description'
    >
      <Box sx={style}>
        <h2 id='ls-manager-modal-title'>LS Manager</h2>
        <div className='ls-manager div'>
          <IbsTextField
            label={'사번'}
            formControllStyle={{ width: '100%', margin: '10px 0' }}
            InputLabelProps={{ shrink: true }}
            className='ls-manager div'
            ref={refs.regUserText}
          />
          <IbsTextField
            label={'이름'}
            formControllStyle={{ width: '100%', margin: '10px 0' }}
            InputLabelProps={{ shrink: true }}
            className='ls-manager div'
            ref={refs.regUserNameText}
          />
          <IbsTextField
            label={'부서'}
            formControllStyle={{ width: '100%', margin: '10px 0' }}
            InputLabelProps={{ shrink: true }}
            className='ls-manager div'
            ref={refs.deptNameText}
          />
        </div>
        <div>
          <div>
            <IbsTextField
              label={'관리번호'}
              formControllStyle={{ width: '100%', margin: '10px 0' }}
              InputLabelProps={{ shrink: true }}
              className='ls-manager div'
              ref={refs.qmsNumberText}
            />
          </div>
          <div>
            <IbsTextField
              label={'제목'}
              formControllStyle={{ width: '100%', margin: '10px 0' }}
              InputLabelProps={{ shrink: true }}
              className='ls-manager div'
              ref={refs.issueTitleText}
            />
          </div>
          <div>
            <IbsTextField
              label={'Board Member'}
              formControllStyle={{ width: '100%', margin: '10px 0' }}
              InputLabelProps={{ shrink: true }}
            />
            <FormControl style={{ width: 120, marginTop: 15 }}>
              <Button variant='contained'>{'Select'}</Button>
            </FormControl>

            <IbsCombobox
              label={'제품명'}
              width={200}
              defaultItems={[{ displayValue: '', value: '' }]}
              blank
              multiple
              ref={refs.productCombo}
              onChangeCombobox={onChangeProductComobo}
            />
          </div>
        </div>
        <div
          className={'ag-theme-quartz'}
          style={{ width: '80%', height: '30%', margin: 5 }}
        >
          <AgGridReact
            onGridReady={onProductGridReady}
            rowData={productRowData}
            groupDisplayType={'singleColumn'}
            groupDefaultExpanded={-1}
            columnDefs={proudctColumnDefs}
          ></AgGridReact>
        </div>
        <div>
          <IbsCombobox
            label={'불량 스텝'}
            size='small'
            width={200}
            defaultItems={[{ displayValue: '', value: '' }]}
            className='ls-manager div'
            ref={refs.divisionCombo}
          />
          <IbsCombobox
            label={'불량 분류'}
            size='small'
            width={200}
            defaultItems={[{ displayValue: '', value: '' }]}
            className='ls-manager div'
            ref={refs.issueCombo}
          />
        </div>
        <div>
          <IbsCombobox
            label={'원인 공정'}
            size='small'
            width={200}
            defaultItems={[{ displayValue: '', value: '' }]}
            onChangeCombobox={onChangeProcessCombo}
            ref={refs.processCombo}
          />
          <IbsCombobox
            label={'외주처'}
            size='small'
            width={200}
            defaultItems={[{ displayValue: '', value: '' }]}
            ref={refs.supplierCombo}
          />
          <IbsCombobox
            label={'원인 분류'}
            size='small'
            width={200}
            defaultItems={[{ displayValue: '', value: '' }]}
            blank
            ref={refs.causeCombo}
          />
        </div>
        <div>
          <IbsTextField
            label={'불량 현상'}
            formControllStyle={{ width: '100%', margin: '10px 0' }}
            InputLabelProps={{ shrink: true }}
            multiline={true}
            width={500}
            rows={4}
            ref={refs.issueSummaryText}
          />
        </div>
        <div>
          <IbsTextField
            label={'원인'}
            formControllStyle={{ width: '100%', margin: '10px 0' }}
            InputLabelProps={{ shrink: true }}
            multiline={true}
            width={500}
            rows={4}
            ref={refs.causeSummaryText}
          />
        </div>
        <div>
          <IbsTextField
            label={'대책'}
            formControllStyle={{ width: '100%', margin: '10px 0' }}
            InputLabelProps={{ shrink: true }}
            multiline={true}
            width={500}
            rows={4}
            ref={refs.measuresSummaryText}
          />
        </div>
        <div>
          <FormControl style={{ width: 120, marginTop: 7 }}>
            <Button variant='contained' onClick={addDetailGridRow}>
              {'ADD'}
            </Button>
          </FormControl>
        </div>
        <div
          className={'ag-theme-quartz'}
          style={{ width: '80%', height: '30%', margin: 5 }}
        >
          <AgGridReact
            columnDefs={detailColumnDefs}
            onGridReady={onDetailGridReady}
            rowData={detailRowData}
          ></AgGridReact>
        </div>
        <div>
          <FormControl style={{ width: 120, marginTop: 7 }}>
            <Button variant='contained'>{'file'}</Button>
          </FormControl>
        </div>
        <div
          className={'ag-theme-quartz'}
          style={{ width: '80%', height: '30%', margin: 5 }}
        >
          <AgGridReact
            onGridReady={onFileGridReady}
            rowData={fileRowData}
            columnDefs={fileColumnDefs}
          ></AgGridReact>
        </div>
        <Button onClick={onClose}>Close</Button>
      </Box>
    </Modal>
  )
}

export default LsManager

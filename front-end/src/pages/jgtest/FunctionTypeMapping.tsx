import { Button, FormControl, Grid } from '@mui/material'
import { AgGridReact } from 'ag-grid-react'
import axios from 'axios'
import UserTreeView from './UserTreeView'
import { ColDef } from 'ag-grid-community'
import 'ag-grid-community/styles/ag-grid.css' // Core CSS
import 'ag-grid-community/styles/ag-theme-quartz.css' // Theme
import { useEffect, useState } from 'react'
import IbsButton from '@/components/common/IbsButton'
import IbsTypeButton from '@/components/common/IbsTypeButton'

interface Item {
  displayValue: string
  value: string
  description?: string
}

const FunctionTypeMapping = () => {
  const [columnDefs, setColumnDefs] = useState<ColDef[]>([
    { headerName: '부서 그룹', field: 'bu' },
    { headerName: '부서', field: 'deptName' },
    // 초기 컬럼 정의
  ])

  const [gridApi, setGridApi] = useState(null)
  const [gridColumnApi, setGridColumnApi] = useState(null)
  const [functionTypes, setFunctionTypes] = useState<Item[]>([
    {
      displayValue: '',
      value: '',
    },
  ])

  const handleNodeSelect = (event: React.ChangeEvent<{}>, nodeId: string) => {
    const data = {
      plant: 'SILICONMITUS',
      deptId: nodeId,
    }
    const success = (response: any) => {
      setRowData(response.data)
    }

    axios
      .post('http://localhost:8081/api/menu/dept/mapping-data', data)
      .then(success)
  }

  const onClickInit = () => {
    const data = {
      plant: 'SILICONMITUS',
    }
    const success = (response: any) => {
      setRowData(response.data)
    }

    axios
      .post('http://localhost:8081/api/menu/dept/mapping-data', data)
      .then(success)
  }

  const getFunctionType = () => {
    const data = {
      plant: 'SILICONMITUS',
    }
    const success = (response: any) => {
      if (response?.data) {
        let functionTypesTemp: [Item] = [{ displayValue: '', value: '' }]
        response.data.map((e: any) =>
          functionTypesTemp.push({
            displayValue: e.description,
            value: e.codeName,
          }),
        )

        setColumnDefs([
          { headerName: '부서 그룹', field: 'bu' },
          { headerName: '부서', field: 'deptName' },
          {
            headerName: '부서 기능',
            field: 'functionType',
            cellEditor: 'agSelectCellEditor',
            cellEditorParams: {
              values: functionTypesTemp.map((ft) => ft.value), // 콤보박스에는 value만 표시
            },
            valueFormatter: (params) => {
              const found = functionTypesTemp.find(
                (ft) => ft.value === params.value,
              )
              return found ? found.displayValue : ''
            },

            editable: true,
          },
        ])
      }
    }

    axios
      .post('http://localhost:8081/api/menu/dept/function-type', data)
      .then(success)
  }

  const [rowData, setRowData] = useState<any>([])

  const onGridReady = (params: any) => {
    setGridApi(params.api)
    setGridColumnApi(params.columnApi)
  }
  const onClickUpdate = () => {
    const data = {
      plant: 'SILICONMITUS',
      regUser: 'IBS',
      deptMappingDtoList: rowData,
    }
    const success = (response: any) => {
      onClickInit()
    }

    axios
      .post('http://localhost:8081/api/menu/dept/function-type/action', data)
      .then(success)
  }

  useEffect(() => {
    getFunctionType()
  }, [])

  return (
    <>
      <Grid container spacing={2}>
        <Grid item xs={3}>
          <UserTreeView handleNodeSelect={handleNodeSelect} />
        </Grid>
        <Grid item xs={9}>
          <div
            className={'ag-theme-quartz'}
            style={{ width: '100%', height: '95%' }}
          >
            <AgGridReact
              onGridReady={onGridReady}
              rowData={rowData}
              columnDefs={columnDefs}
            ></AgGridReact>
          </div>
          <div
            style={{
              display: 'flex',
              justifyContent: 'flex-end',
              flexWrap: 'wrap',
              marginRight: 30,
            }}
          >
            <IbsTypeButton
              buttontype='update'
              width={120}
              onClick={onClickUpdate}
            ></IbsTypeButton>
            <FormControl style={{ width: 120, marginTop: 7 }}>
              <Button variant='contained' onClick={onClickInit}>
                {'Init'}
              </Button>
            </FormControl>
          </div>
        </Grid>
      </Grid>
    </>
  )
}
export default FunctionTypeMapping

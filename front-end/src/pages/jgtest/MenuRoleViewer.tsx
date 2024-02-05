import Aggrid from '@/components/aggrid/Aggrid'
import IbsButton from '@/components/common/IbsButton'
import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@/components/common/IbsTextField'
import { Button, FormControl, Grid, Typography } from '@mui/material'
import { ColDef, RowDoubleClickedEvent } from 'ag-grid-community'
import { AgGridReact } from 'ag-grid-react'
import 'ag-grid-community/styles/ag-grid.css' // Core CSS
import 'ag-grid-community/styles/ag-theme-quartz.css' // Theme
import axios from 'axios'
import {
  forwardRef,
  useEffect,
  useImperativeHandle,
  useRef,
  useState,
} from 'react'

interface Item {
  displayValue: string
  value: string
  description?: string
}

interface MenuRoleViewerProps {
  onDoubleClickGrid?: (event: RowDoubleClickedEvent<any>) => void
}
const MenuRoleViewer = forwardRef((props: MenuRoleViewerProps, ref: any) => {
  useImperativeHandle(ref, () => ({}))
  const [searchPlantCombo, setSearchPlantCombo] = useState<Item[]>([])
  const [gridApi, setGridApi] = useState(null)
  const [gridColumnApi, setGridColumnApi] = useState(null)
  const [rowData, setRowData] = useState([])
  const refs = {
    grid: useRef<AgGridReact<any>>(null),
    searchCombo: useRef<IbsComboboxHandle>(null),
    searchPlantCombo: useRef<IbsComboboxHandle>(null),
    searchText: useRef<IbsTextFieldHandle>(null),
  }

  const searchCombo: Item[] = [
    {
      displayValue: '메뉴 권한',
      value: 'role_group',
    },
    {
      displayValue: '설명',
      value: 'DESCRIPTION',
    },
  ]

  const SearchWithEnter = (event: React.KeyboardEvent) => {
    if (event.key === 'Enter') {
      onClickSearchRoleMenuGroup()
    }
  }
  const getPlant = () => {
    const data = {
      activePlant: 'Y',
    }

    const success = (response: any) => {
      response.data.forEach((e: any) => {
        const newItems = response.data.map((e: any) => ({
          displayValue: e.description,
          value: e.plant,
        }))

        setSearchPlantCombo(newItems)
      })
    }

    axios.post('http://localhost:8081/api/role/plant-info', data).then(success)
  }

  const onClickSearchRoleMenuGroup = () => {
    const data = {
      plant: refs.searchPlantCombo.current?.getSelectedValues()[0],
      searchOrder: refs.searchCombo.current?.getSelectedValues()[0],
      likeKeyword: refs.searchText.current?.getValue(),
    }
    const success = (response: any) => {
      console.log(response.data)
      setRowData(response.data)
    }
    axios
      .post('http://localhost:8081/api/role/role-menu-group', data)
      .then(success)
  }
  const columnDefs: ColDef[] = [
    { headerName: 'PLANT', field: 'plant' },
    { headerName: 'MENU GROUP', field: 'menuGroup' },
    { headerName: 'DESCRIPTION', field: 'description' },
  ]

  const onGridReady = (params: any) => {
    setGridApi(params.api)
    setGridColumnApi(params.columnApi)
  }
  useEffect(() => {
    getPlant()
  }, [])
  useEffect(() => {
    refs.searchPlantCombo.current?.setItems([
      { displayValue: '', value: '' },
      ...searchPlantCombo,
    ])
  }, [searchPlantCombo])

  return (
    <>
      <div style={{ marginBottom: 10 }}>
        <div style={{ height: '100%' }}>
          <div className='title-container' style={{ paddingLeft: 0 }}>
            <div className='line'></div>
            <Typography variant='h6'>Role Menu Group</Typography>
          </div>
        </div>

        <IbsCombobox
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={150}
          defaultItems={searchCombo}
          ref={refs.searchCombo}
        />
        <IbsTextField
          formControllStyle={{ width: 210, marginTop: 0, marginLeft: 0 }}
          onKeyDown={SearchWithEnter}
        />
        <FormControl style={{ width: 120, marginTop: 7 }}>
          <Button onClick={onClickSearchRoleMenuGroup} variant='contained'>
            {'search'}
          </Button>
        </FormControl>
      </div>
      <div>
        <IbsCombobox
          ref={refs.searchPlantCombo}
          formControllStyle={{ marginTop: 0, marginLeft: 0 }}
          size='small'
          width={300}
          defaultItems={[{ displayValue: '', value: '' }]}
        />
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
})

export default MenuRoleViewer

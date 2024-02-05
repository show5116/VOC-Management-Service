import IbsButton from '@/components/common/IbsButton'
import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@/components/common/IbsTextField'
import { Button, FormControl, Grid } from '@mui/material'
import { RowDoubleClickedEvent } from 'ag-grid-community'
import axios from 'axios'
import { useEffect, useRef, useState } from 'react'
import MenuRoleViewer from './MenuRoleViewer'
import { defaultFormControllStyle } from '@/components/styles/shareStyle'
import IbsTypeButton from '@/components/common/IbsTypeButton'

interface Item {
  displayValue: string
  value: string
  description?: string
}
interface roleMenuGroupData {
  description: string
  menuGroup: string
  plant: string
}
const MenuRole = () => {
  const [plantCombo, setPlantCombo] = useState<Item[]>([])
  const [rowData, setRowData] = useState<roleMenuGroupData>()
  const [isData, setIsData] = useState<boolean>(false)
  const refs = {
    plantCombo: useRef<IbsComboboxHandle>(null),
    menuAuth: useRef<IbsTextFieldHandle>(null),
    description: useRef<IbsTextFieldHandle>(null),
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

        setPlantCombo(newItems)
      })
    }

    axios.post('http://localhost:8081/api/role/plant-info', data).then(success)
  }

  const onClickGridTest = () => {
    const testData = {
      description: 'test',
      menuGroup: 'test',
      plant: 'SILICON_TEST',
    }
    setRowData(testData)
  }
  const init = () => {
    setIsData(false)
    refs.plantCombo.current?.setValue('')
    refs.menuAuth.current?.setValue('')
    refs.description.current?.setValue('')
  }
  const handleInsert = () => {
    if (window.confirm('정말로 입력하시겠습니까?')) {
      const data = {
        dml: 'I',
        plant: refs.plantCombo.current?.getSelectedValues()[0],
        description: refs.description.current?.getValue(),
        roleGroup: refs.menuAuth.current?.getValue(),
        roleType: 'M',
        regUser: 'IBS',
      }
      const success = (response: any) => {
        init()
      }
      axios
        .post('http://localhost:8081/api/role/role-menu-group/action', data)
        .then(success)
    }
  }

  const handleUpdate = () => {
    if (window.confirm('정말로 수정하시겠습니까?')) {
      const data = {
        dml: 'U',
        plant: refs.plantCombo.current?.getSelectedValues()[0],
        description: refs.description.current?.getValue(),
        roleGroup: refs.menuAuth.current?.getValue(),
        roleType: 'M',
        regUser: 'IBS',
      }
      const success = (response: any) => {
        init()
      }
      axios
        .post('http://localhost:8081/api/role/role-menu-group/action', data)
        .then(success)
    }
  }

  const handleDelete = () => {
    if (window.confirm('정말로 삭제하시겠습니까?')) {
      const data = {
        dml: 'D',
        plant: refs.plantCombo.current?.getSelectedValues()[0],
        description: refs.description.current?.getValue(),
        roleGroup: refs.menuAuth.current?.getValue(),
        roleType: 'M',
        regUser: 'IBS',
      }
      const success = (response: any) => {
        init()
      }
      axios
        .post('http://localhost:8081/api/role/role-menu-group/action', data)
        .then(success)
    }
  }

  const onDoubleClickGrid = (event: RowDoubleClickedEvent<any>) => {}
  useEffect(() => {
    getPlant()
  }, [])
  useEffect(() => {
    if (rowData) {
      refs.plantCombo.current?.setValue(rowData.plant)
      refs.menuAuth.current?.setValue(rowData.menuGroup)
      refs.description.current?.setValue(rowData.description)
      setIsData(true)
    }
  }, [rowData])
  useEffect(() => {
    refs.plantCombo.current?.setItems([
      { displayValue: '', value: '' },
      ...plantCombo,
    ])
  }, [plantCombo])

  return (
    <>
      <Grid container spacing={2} style={{ height: '100%' }}>
        <Grid item xs={3}>
          <MenuRoleViewer onDoubleClickGrid={onDoubleClickGrid} />
        </Grid>
        <Grid item xs={9} style={{ height: '100%' }}>
          <div>
            <IbsCombobox
              required
              label={'COMPANY'}
              ref={refs.plantCombo}
              formControllStyle={{ marginTop: 0, marginLeft: 0 }}
              size='small'
              width={400}
              disabled={isData}
              defaultItems={[{ displayValue: '', value: '' }]}
            />
          </div>
          <div>
            <IbsTextField
              required
              ref={refs.menuAuth}
              label={'메뉴 권한'}
              width={400}
              formControllStyle={{ width: '100%', margin: '10px 0' }}
              disabled={isData}
              InputLabelProps={{ shrink: true }}
            />
          </div>
          <div>
            <IbsTextField
              ref={refs.description}
              label={'설명'}
              width={400}
              formControllStyle={{ width: '100%', margin: '10px 0' }}
              InputLabelProps={{ shrink: true }}
            />
          </div>
          <div
            style={{
              display: 'flex',
              justifyContent: 'flex-end',
              flexWrap: 'wrap',
              marginTop: 600,
              marginRight: 30,
            }}
          >
            <IbsTypeButton
              onClick={handleInsert}
              disabled={isData}
              buttontype='save'
              width={120}
            ></IbsTypeButton>

            <IbsTypeButton
              onClick={handleUpdate}
              disabled={!isData}
              buttontype='update'
              width={120}
            ></IbsTypeButton>
            <IbsTypeButton
              onClick={handleDelete}
              disabled={!isData}
              buttontype='delete'
              width={120}
            ></IbsTypeButton>

            <FormControl style={{ width: 120, marginTop: 7 }}>
              <Button onClick={init} variant='contained'>
                {'Init'}
              </Button>
            </FormControl>
          </div>
        </Grid>
      </Grid>
    </>
  )
}

export default MenuRole

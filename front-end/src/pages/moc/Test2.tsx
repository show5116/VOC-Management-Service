import Aggrid, { AggridHandle } from '@/components/aggrid/Aggrid'
import { formatNumber } from '@/components/aggrid/AggridFunction'
import GridLabel, { label, lableState } from '@/components/aggrid/GridLabel'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@/components/common/IbsTextField'
import { ColDef } from 'ag-grid-community'
import { useRef } from 'react'

const rowData: any[] = [
  {
    mission: 'Voyager',
    company: 'NASA',
    location: 'Cape Canaveral',
    date: '1977-09-05',
    rocket: 'Titan-Centaur ',
    price: 86580000,
    successful: 'completed',
  },
  {
    mission: 'Apollo 13',
    company: 'NASA',
    location: 'Kennedy Space Center',
    date: '1970-04-11',
    rocket: 'Saturn V',
    price: 3750000,
    successful: 'notStarted',
  },
  {
    mission: 'Falcon 9',
    company: 'SpaceX',
    location: 'Cape Canaveral',
    date: '2015-12-22',
    rocket: 'Falcon 9',
    price: 9750000,
    successful: 'blocked',
  },
  {
    mission: '9',
    company: 'a',
    location: 'a',
    date: '2015-12-22',
    rocket: '9',
    price: 99,
    successful: 'inProgress',
  },
]

const columns: ColDef[] = [
  {
    field: 'mission',
    filter: 'agTextColumnFilter',
    floatingFilter: true,
  },
  { field: 'company', filter: true, floatingFilter: true },
  { field: 'location' },
  {
    field: 'date',
    filter: 'agDateColumnFilter',
    floatingFilter: true,
    cellClass: 'text-center',
  },
  {
    field: 'price',
    valueFormatter: (params) => formatNumber(params.value),
    cellClass: 'text-right',
  },
  {
    field: 'successful',
    filter: true,
    floatingFilter: true,
    valueGetter: (params) => label[`${params.data.successful as lableState}`],
    cellRenderer: GridLabel,
  },
  { field: 'rocket' },
]

const Test2 = () => {
  const gridRef = useRef<AggridHandle>(null)
  const inputRef = useRef<IbsTextFieldHandle>(null)

  return (
    <div style={{ height: '100%' }}>
      <IbsTextField label={'lot number'} />
      <IbsTextField label={'device'} required />
      <IbsTextField label={'fab'} />

      <Aggrid
        columns={columns}
        ref={gridRef}
        defaultRowData={rowData}
        gridHeight={'60%'}
        gridOptions={{}}
      />

      <IbsTextField
        ref={inputRef}
        required
        multiline
        rows={3}
        limitCount={150}
        fullWidth
      />
      <button
        onClick={() => {
          console.log(inputRef.current?.isLimitValid())
        }}
      >
        test
      </button>
    </div>
  )
}

export default Test2

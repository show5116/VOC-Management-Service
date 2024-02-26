import {
  Dispatch,
  forwardRef,
  SetStateAction,
  useImperativeHandle,
  useState,
} from 'react'
import { AgGridReact, AgGridReactProps } from 'ag-grid-react'
import { ColDef, ColGroupDef } from 'ag-grid-community'

interface AggridProps extends AgGridReactProps {
  columns: ColDef<any, any>[] | ColGroupDef<any>[]
  defaultRowData: any[]
  gridHeight?: number | string
  gridWidth?: number | string
  gridRef?: React.RefObject<AgGridReact<any>>
}

export type AggridHandle = {
  setRowData: Dispatch<React.SetStateAction<any[]>>
  setColumnDefs: React.Dispatch<
    SetStateAction<ColDef<any, any>[] | ColGroupDef<any>[]>
  >
}

const Aggrid = forwardRef<AggridHandle, AggridProps>((agGridProps, ref) => {
  useImperativeHandle(ref, () => ({ setRowData, setColumnDefs }))
  const {
    columns,
    defaultRowData,
    rowSelection = 'multiple',
    gridHeight = '100%',
    gridWidth = '100%',
    gridRef = null,
    ...props
  } = agGridProps

  const [rowData, setRowData] = useState<any>()
  const [columnDefs, setColumnDefs] = useState<
    ColDef<any, any>[] | ColGroupDef<any>[]
  >(columns)

  return (
    <div style={{ height: gridHeight, width: gridWidth, margin: '10px 0px' }}>
      <div
        className={'ag-theme-quartz'}
        style={{ width: '100%', height: '100%' }}
      >
        <AgGridReact<any>
          {...props}
          ref={gridRef}
          rowData={defaultRowData}
          columnDefs={columnDefs}
          rowSelection={rowSelection}
        />
      </div>
    </div>
  )
})

export default Aggrid

Aggrid.defaultProps = {
  rowHeight: 30,
}

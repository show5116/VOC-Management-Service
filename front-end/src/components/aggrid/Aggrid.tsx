import { AgGridReact, AgGridReactProps } from 'ag-grid-react'
import { ColDef, ColGroupDef } from 'ag-grid-community'

interface AggridProps extends AgGridReactProps {
  columns: ColDef<any, any>[] | ColGroupDef<any>[]
  rowData: any[]
  gridHeight?: number | string
  gridWidth?: number | string
  gridRef?: React.RefObject<AgGridReact<any>>
}

const Aggrid = (props: AggridProps) => {
  return (
    <div
      style={{
        height: props.gridHeight,
        width: props.gridWidth,
        margin: '10px 0px',
      }}
    >
      <div
        className={'ag-theme-quartz'}
        style={{ width: '100%', height: '100%' }}
      >
        <AgGridReact
          {...props}
          ref={props.gridRef}
          rowData={props.rowData}
          columnDefs={props.columns}
          rowSelection={props.rowSelection}
        />
      </div>
    </div>
  )
}

export default Aggrid

Aggrid.defaultProps = {
  rowHeight: 30,
}

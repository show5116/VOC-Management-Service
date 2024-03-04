import { useEffect, useRef, useState } from 'react'

import Aggrid from '@components/aggrid/Aggrid'
import { ColDef } from 'ag-grid-community'

import * as am5 from '@amcharts/amcharts5'
import * as am5xy from '@amcharts/amcharts5/xy'
import am5themes_Animated from '@amcharts/amcharts5/themes/Animated'

import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
import IbsDatePicker, {
  IbsDatePickerHandel,
} from '@/components/common/IbsDatePicker'
import IbsTextField from '@/components/common/IbsTextField'
import IbsTypeButton from '@/components/common/IbsTypeButton'

import * as S from './RequirementsProcessingStatus.style'
import { AgGridReact } from 'ag-grid-react'

const dataMain: any[] = [
  {
    system: 'ITM',
    request: 2,
    complete: 2,
  },
  {
    system: 'ITM-VIET',
    request: 34,
    complete: 32,
  },
  {
    system: '파워로직스',
    request: 55,
    complete: 54,
  },
  {
    system: '실리콘 마이터스',
    request: 10,
    complete: 7,
  },
  {
    system: 'ABOV',
    request: 0,
    complete: 0,
  },
  {
    system: '동화ES',
    request: 5,
    complete: 4,
  },
]

const rowDataMainTest: any[] = [
  {
    classification: '요청건수',
    itm: 2,
    itmViet: 34,
    powerLogics: 55,
    slilconMitus: 10,
    abov: 0,
    donghwa: 5,
  },
  {
    classification: '처리건수',
    itm: 2,
    itmViet: 32,
    powerLogics: 54,
    slilconMitus: 7,
    abov: 0,
    donghwa: 4,
  },
]

const columnMain: ColDef[] = [
  {
    headerName: '',
    field: 'classification',
  },
  {
    headerName: 'ITM',
    field: 'itm',
  },
  {
    headerName: 'ITM-VIET',
    field: 'itmViet',
  },
  {
    headerName: '파워로직스',
    field: 'powerLogics',
  },
  {
    headerName: '실리콘 마이터스',
    field: 'slilconMitus',
  },
  {
    headerName: 'ABOV',
    field: 'abov',
  },
  {
    headerName: '동화ES',
    field: 'donghwa',
  },
]

const dataDeveloper: any[] = []

const columnDeveloper: ColDef[] = []

const RequirementsProcessingStatus = () => {
  const refs = {
    dateKindCombo: useRef<IbsComboboxHandle>(null),
    startDatePicker: useRef<IbsDatePickerHandel>(null),
    endDatePicker: useRef<IbsDatePickerHandel>(null),
    mainGridRef: useRef<AgGridReact>(null),
    developerGridRef: useRef<AgGridReact>(null),
  }

  const [rowDataMain, setRowDataMain] = useState<any[]>(rowDataMainTest)
  const [columnsMain, setColmnsMain] = useState<ColDef[]>(columnMain)
  const [rowDataDeveloper, setRowDataDeveloper] = useState<any[]>(dataDeveloper)
  const [columnsDeveloper, setColmnsDeveloper] =
    useState<ColDef[]>(columnDeveloper)

  useEffect(() => {
    const root = am5.Root.new('chartdiv')

    root.setThemes([am5themes_Animated.new(root)])

    const chart = root.container.children.push(
      am5xy.XYChart.new(root, {
        panY: false,
        layout: root.verticalLayout,
      }),
    )

    let yAxis = chart.yAxes.push(
      am5xy.ValueAxis.new(root, {
        renderer: am5xy.AxisRendererY.new(root, {}),
      }),
    )

    let xAxis = chart.xAxes.push(
      am5xy.CategoryAxis.new(root, {
        renderer: am5xy.AxisRendererX.new(root, {}),
        categoryField: 'system',
      }),
    )
    xAxis.data.setAll(dataMain)

    let series1 = chart.series.push(
      am5xy.ColumnSeries.new(root, {
        name: '요청건수',
        xAxis: xAxis,
        yAxis: yAxis,
        valueYField: 'request',
        categoryXField: 'system',
      }),
    )
    series1.data.setAll(dataMain)

    let series2 = chart.series.push(
      am5xy.ColumnSeries.new(root, {
        name: '처리건수',
        xAxis: xAxis,
        yAxis: yAxis,
        valueYField: 'complete',
        categoryXField: 'system',
      }),
    )
    series2.data.setAll(dataMain)

    let legend = chart.children.push(am5.Legend.new(root, {}))
    legend.data.setAll(chart.series.values)

    chart.set('cursor', am5xy.XYCursor.new(root, {}))

    return () => {
      root.dispose()
    }
  }, [dataMain])

  useEffect(() => {
    refs.mainGridRef.current?.api?.autoSizeAllColumns(false)
  }, [rowDataMain])

  return (
    <S.Container>
      <S.Menu>
        <S.SearchCondition>
          <IbsCombobox
            ref={refs.dateKindCombo}
            label='기간 종류'
            width='160px'
            defaultItems={[
              { value: 'new', displayValue: '요청일' },
              { value: 'error', displayValue: '납기 요청일' },
              {
                value: 'improvement',
                displayValue: '완료일',
              },
            ]}
          />
          <IbsDatePicker ref={refs.startDatePicker} type='date' />
          <span>-</span>
          <IbsDatePicker ref={refs.endDatePicker} type='date' />
          <IbsTextField label='System' />
        </S.SearchCondition>
        <S.Buttons>
          <IbsTypeButton buttontype={'search'} />
        </S.Buttons>
      </S.Menu>
      <div id='chartdiv' style={{ width: '100%', height: '300px' }}></div>
      <Aggrid
        gridRef={refs.mainGridRef}
        columns={columnsMain}
        rowData={rowDataMain}
        gridHeight={'30%'}
        gridOptions={{}}
      />
      {/*<Aggrid
        gridRef={refs.developerGridRef}
        columns={columnsDeveloper}
        rowData={rowDataDeveloper}
        gridHeight={'30%'}
        gridOptions={{}}
          />*/}
    </S.Container>
  )
}

export default RequirementsProcessingStatus

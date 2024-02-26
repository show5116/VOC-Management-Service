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

const columnMain: ColDef[] = [
  {
    headerName: '',
    field: 'classification',
  },
]

const dataDeveloper: any[] = []

const columnDeveloper: ColDef[] = []

const RequirementsProcessingStatus = () => {
  const refs = {
    dateKindCombo: useRef<IbsComboboxHandle>(null),
    startDatePicker: useRef<IbsDatePickerHandel>(null),
    endDatePicker: useRef<IbsDatePickerHandel>(null),
  }

  const [rowDataMain, setRowDataMain] = useState<any[]>(dataMain)
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
    series1.data.setAll(rowDataMain)

    let series2 = chart.series.push(
      am5xy.ColumnSeries.new(root, {
        name: '처리건수',
        xAxis: xAxis,
        yAxis: yAxis,
        valueYField: 'complete',
        categoryXField: 'system',
      }),
    )
    series2.data.setAll(rowDataMain)

    let legend = chart.children.push(am5.Legend.new(root, {}))
    legend.data.setAll(chart.series.values)

    chart.set('cursor', am5xy.XYCursor.new(root, {}))

    return () => {
      root.dispose()
    }
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
      <div id='chartdiv' style={{ width: '100%', height: '400px' }}></div>
      <Aggrid
        columns={columnsMain}
        rowData={rowDataMain}
        gridHeight={'30%'}
        gridOptions={{}}
      />
      <Aggrid
        columns={columnsDeveloper}
        rowData={rowDataDeveloper}
        gridHeight={'30%'}
        gridOptions={{}}
      />
    </S.Container>
  )
}

export default RequirementsProcessingStatus

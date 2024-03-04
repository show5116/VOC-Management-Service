import { useEffect } from 'react'

import * as am5 from '@amcharts/amcharts5'
import * as am5xy from '@amcharts/amcharts5/xy'
import am5themes_Animated from '@amcharts/amcharts5/themes/Animated'
import Aggrid from '@/components/aggrid/Aggrid'
import { ColDef } from 'ag-grid-community'

const chartData = [
  {
    month: '1월',
    mighty: 30,
    oms: 50,
  },
  {
    month: '2월',
    mighty: 40,
    oms: 70,
  },
  {
    month: '3월',
    mighty: 20,
    oms: 30,
  },
  {
    month: '4월',
    mighty: 40,
    oms: 20,
  },
  {
    month: '5월',
    mighty: 10,
    oms: 53,
  },
  {
    month: '6월',
    mighty: 20,
    oms: 40,
  },
  {
    month: '7월',
    mighty: 10,
    oms: 20,
  },
  {
    month: '8월',
    mighty: 30,
    oms: 40,
  },
  {
    month: '9월',
    mighty: 30,
    oms: 50,
  },
  {
    month: '10월',
    mighty: 30,
    oms: 50,
  },
  {
    month: '11월',
    mighty: 30,
    oms: 50,
  },
  {
    month: '12월',
    mighty: 30,
    oms: 50,
  },
]

const rowData = [
  {
    customer: 'mighty',
    jan: 30,
    feb: 40,
    mar: 20,
    apr: 40,
    may: 10,
    jun: 20,
    jul: 10,
    aug: 30,
    sep: 30,
    oct: 30,
    nov: 30,
    dec: 30,
  },
  {
    customer: 'oms',
    jan: 50,
    feb: 70,
    mar: 30,
    apr: 20,
    may: 53,
    jun: 40,
    jul: 20,
    aug: 40,
    sep: 50,
    oct: 50,
    nov: 50,
    dec: 50,
  },
]

const columns: ColDef[] = [
  {
    headerName: '',
    field: 'customer',
    width: 100,
  },
  {
    headerName: '1월',
    field: 'jan',
    width: 100,
  },
  {
    headerName: '2월',
    field: 'feb',
    width: 100,
  },
  {
    headerName: '3월',
    field: 'mar',
    width: 100,
  },
  {
    headerName: '4월',
    field: 'apr',
    width: 100,
  },
  {
    headerName: '5월',
    field: 'may',
    width: 100,
  },
  {
    headerName: '6월',
    field: 'jun',
    width: 100,
  },
  {
    headerName: '7월',
    field: 'jul',
    width: 100,
  },
  {
    headerName: '8월',
    field: 'aug',
    width: 100,
  },
  {
    headerName: '9월',
    field: 'sep',
    width: 100,
  },
  {
    headerName: '10월',
    field: 'oct',
    width: 100,
  },
  {
    headerName: '11월',
    field: 'nov',
    width: 100,
  },
  {
    headerName: '12월',
    field: 'dec',
    width: 100,
  },
]

const RequirementsByMontlyCustomer = () => {
  useEffect(() => {
    const root = am5.Root.new('chartdiv')

    root.setThemes([am5themes_Animated.new(root)])

    var chart = root.container.children.push(
      am5xy.XYChart.new(root, {
        panY: false,
        pinchZoomX: true,
        paddingLeft: 0,
      }),
    )

    var cursor = chart.set(
      'cursor',
      am5xy.XYCursor.new(root, {
        behavior: 'none',
      }),
    )
    cursor.lineY.set('visible', false)

    var xAxis = chart.xAxes.push(
      am5xy.CategoryAxis.new(root, {
        maxDeviation: 0.2,
        renderer: am5xy.AxisRendererX.new(root, {}),
        categoryField: 'month',
      }),
    )
    xAxis.data.setAll(chartData)

    let yAxis = chart.yAxes.push(
      am5xy.ValueAxis.new(root, {
        renderer: am5xy.AxisRendererY.new(root, {}),
      }),
    )

    function createSeries(name: string, field: string) {
      var series = chart.series.push(
        am5xy.LineSeries.new(root, {
          name: name,
          xAxis: xAxis,
          yAxis: yAxis,
          valueYField: field,
          categoryXField: 'month',
          tooltip: am5.Tooltip.new(root, {
            labelText: '{name}: {valueY}',
          }),
        }),
      )
      series.strokes.template.setAll({
        strokeWidth: 3,
        strokeDasharray: [10, 5],
      })

      series.data.setAll(chartData)
    }

    createSeries('mighty', 'mighty')
    createSeries('OMS', 'oms')

    let legend = chart.children.push(am5.Legend.new(root, {}))
    legend.data.setAll(chart.series.values)

    chart.appear(1000, 100)

    return () => {
      root.dispose()
    }
  }, [])

  return (
    <>
      <div id='chartdiv' style={{ width: '100%', height: '400px' }}></div>
      <Aggrid
        columns={columns}
        rowData={rowData}
        gridHeight={'30%'}
        gridOptions={{}}
      />
    </>
  )
}

export default RequirementsByMontlyCustomer

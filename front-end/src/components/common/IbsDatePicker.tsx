import { FormControl, InputAdornment, TextField } from '@mui/material'
import {
  CSSProperties,
  forwardRef,
  memo,
  useEffect,
  useImperativeHandle,
  useState,
} from 'react'
import DatePicker, { ReactDatePickerCustomHeaderProps } from 'react-datepicker'
import { FaRegCalendarDays } from 'react-icons/fa6'
import { getDay, getMonth, getYear } from 'date-fns'
import {
  PiCaretCircleDoubleLeftFill,
  PiCaretCircleLeftFill,
  PiCaretCircleDoubleRightFill,
  PiCaretCircleRightFill,
} from 'react-icons/pi'
import 'react-datepicker/dist/react-datepicker.css'
import '@/components/common/IbsDatePicker.scss'
import { enGB } from 'date-fns/locale'
import { defaultFormControllStyle } from '../styles/shareStyle'
import { IbsBaseProps } from '../share/model'

type DatePickerType = 'date' | 'week' | 'month'

interface IbsDatePickerProps extends IbsBaseProps {
  type?: DatePickerType
  label?: string
  startDate?: Date
  readOnly?: boolean
  onChangeIbsDatePicker?: (date: Date) => void
}

export type IbsDatePickerHandel = {
  getDate: () => { startDate: Date; endDate: Date }
}

const months = [
  '01',
  '02',
  '03',
  '04',
  '05',
  '06',
  '07',
  '08',
  '09',
  '10',
  '11',
  '12',
]

const IbsDatePicker = forwardRef<IbsDatePickerHandel, IbsDatePickerProps>(
  (datePickerProps, ref) => {
    useImperativeHandle(ref, () => ({ getDate }))
    const {
      type = 'date',
      label = '',
      startDate = new Date(),
      width = 140,
      formControllStyle,
      onChangeIbsDatePicker,
    } = datePickerProps

    const [targetDate, setTargetDate] = useState<Date>(startDate)
    //const [years, setYears] = useState<number[]>([])

    const getDate = () => {
      const year: number = targetDate.getFullYear()
      const month: number = targetDate.getMonth()
      switch (type) {
        case 'month':
          return {
            startDate: new Date(year, month, 1),
            endDate: new Date(year, month + 1, 0),
          }
        case 'week':
          const day: number = targetDate.getDate()
          return {
            startDate: new Date(year, month, day),
            endDate: new Date(year, month, day + 6),
          }
        default:
          return { startDate: targetDate, endDate: targetDate }
      }
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
    let years: number[] = []

    useEffect(() => {
      onChangeIbsDatePicker && onChangeIbsDatePicker(targetDate)
    })

    useEffect(() => {
      for (
        let i = targetDate.getFullYear();
        i >= targetDate.getFullYear() - 5;
        i--
      ) {
        years.push(i)
      }
    }, [targetDate, years])

    const readOnly: boolean =
      type === 'week' ? true : !!datePickerProps.readOnly

    const style: CSSProperties = type === 'week' ? { cursor: 'pointer' } : {}

    const dateFormat: string =
      type === 'month' ? 'yyyy-MM' : type === 'week' ? 'R-II' : 'yyyy-MM-dd'

    return (
      <FormControl
        size={'small'}
        sx={{ ...defaultFormControllStyle({ width: width }) }}
        style={formControllStyle}
      >
        <DatePicker
          locale={enGB}
          readOnly={datePickerProps.readOnly}
          renderCustomHeader={(params: ReactDatePickerCustomHeaderProps) =>
            CustomPickerHeader({ ...params }, years, months)
          }
          renderMonthContent={(monthIndex: number) => {
            return <>{monthIndex + 1}</>
          }}
          dateFormat={dateFormat}
          selected={targetDate}
          onChange={(date) => setTargetDate(date!)}
          dayClassName={(date) =>
            getDay(date) === 0 ? 'sun' : getDay(date) === 6 ? 'sat' : null
          }
          customInput={
            <TextField
              label={label}
              size={'small'}
              inputProps={{ style: style }}
              InputProps={{
                readOnly: readOnly,
                autoComplete: 'off',
                placeholder: dateFormat,
                endAdornment: (
                  <InputAdornment position={'end'}>
                    <FaRegCalendarDays style={{ cursor: 'pointer' }} />
                  </InputAdornment>
                ),
              }}
            />
          }
          showWeekNumbers={type === 'week' ? true : false}
          showWeekPicker={type === 'week' ? true : false}
          showMonthYearPicker={type === 'month' ? true : false}
        />
      </FormControl>
    )
  },
)

export default memo(IbsDatePicker)

const CustomPickerHeader = (
  {
    date,
    changeYear,
    changeMonth,
    decreaseMonth,
    increaseMonth,
    decreaseYear,
    increaseYear,
  }: ReactDatePickerCustomHeaderProps,
  years: number[],
  months: string[],
) => {
  return (
    <div
      style={{
        margin: 10,
        display: 'flex',
        justifyContent: 'center',
      }}
    >
      <div
        onClick={() => {
          if (years.indexOf(getYear(date) - 1) < 0)
            years.push(getYear(date) - 1)
          decreaseYear()
        }}
        style={{ margin: '0px 3px 0px 0px', cursor: 'pointer' }}
      >
        <PiCaretCircleDoubleLeftFill
          style={{ width: 20, height: 20, verticalAlign: 'middle' }}
        />
      </div>
      <div
        onClick={() => {
          if (getMonth(date) === 0 && years.indexOf(getYear(date) - 1) < 0)
            years.push(getYear(date) - 1)
          decreaseMonth()
        }}
        style={{ margin: '0px 15px 0px 0px', cursor: 'pointer' }}
      >
        <PiCaretCircleLeftFill
          style={{ width: 20, height: 20, verticalAlign: 'middle' }}
        />
      </div>

      <select
        style={{ cursor: 'pointer' }}
        value={getYear(date)}
        onChange={({ target: { value } }) => changeYear(Number(value))}
      >
        {years.map((year) => (
          <option key={year} value={year}>
            {year}
          </option>
        ))}
      </select>

      <select
        style={{ cursor: 'pointer' }}
        value={months[getMonth(date)]}
        onChange={({ target: { value } }) => changeMonth(months.indexOf(value))}
      >
        {months.map((month) => (
          <option key={month} value={month}>
            {month}
          </option>
        ))}
      </select>
      <div
        onClick={() => {
          console.log(getYear(date))
          if (getMonth(date) === 11 && years.indexOf(getYear(date) + 1) < 0)
            years.unshift(getYear(date) + 1)
          increaseMonth()
        }}
        style={{ margin: '0px 0px 0px 15px', cursor: 'pointer' }}
      >
        <PiCaretCircleRightFill
          style={{ width: 20, height: 20, verticalAlign: 'middle' }}
        />
      </div>
      <div
        onClick={() => {
          if (years.indexOf(getYear(date) + 1) < 0)
            years.unshift(getYear(date) + 1)
          increaseYear()
        }}
        style={{ margin: '0px 0px 0px 3px', cursor: 'pointer' }}
      >
        <PiCaretCircleDoubleRightFill
          style={{ width: 20, height: 20, verticalAlign: 'middle' }}
        />
      </div>
    </div>
  )
}

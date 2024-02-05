import IbsCombobox, { IbsComboboxHandle } from '@/components/common/IbsCombobox'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@/components/common/IbsTextField'
import IbsTypeButton from '@/components/common/IbsTypeButton'
import { useEffect, useRef } from 'react'
import IbsDatePicker, {
  IbsDatePickerHandel,
} from '@/components/common/IbsDatePicker'
import { format } from 'date-fns'
import IbsButton from '@/components/common/IbsButton'

const Test1 = () => {
  const testRef = useRef<IbsTextFieldHandle>(null)
  const selectRef = useRef<IbsComboboxHandle>(null)
  const dateRef = useRef<IbsDatePickerHandel>(null)
  const dateRef2 = useRef<IbsDatePickerHandel>(null)
  const dateRef3 = useRef<IbsDatePickerHandel>(null)

  const startDate = new Date()

  useEffect(() => {
    selectRef.current?.setItems([
      { value: '1', displayValue: 'QE', description: 'A' },
      { value: '2', displayValue: 'TE', description: 'B' },
      { value: '3', displayValue: 'FE', description: 'C' },
    ])
  }, [])

  console.log(format(startDate, 'yyyy-MM-dd'))
  return (
    <div>
      <IbsTextField multiline rows={3} required limitCount={30} />
      <br />
      <IbsTextField
        ref={testRef}
        label={'Device'}
        formControllStyle={{ marginRight: 10 }}
        limitCount={5}
        required
        onChangeTextField={() => {
          //console.log(testRef.current?.isLimitValid())
          //console.log(testRef.current?.isRequiredValid())
        }}
      />
      <IbsCombobox
        ref={selectRef}
        label={'Device'}
        formControllStyle={{ marginRight: 10 }}
        blank
        defaultItems={[
          { value: '1', displayValue: '가', description: 'A' },
          { value: '2', displayValue: '나', description: 'B' },
          { value: '3', displayValue: '다', description: 'C' },
        ]}
        required
      />
      <IbsCombobox
        label={'Device'}
        formControllStyle={{ marginRight: 10 }}
        blank
        defaultItems={[
          { value: '1', displayValue: '가', description: 'A' },
          { value: '2', displayValue: '나', description: 'B' },
          { value: '3', displayValue: '다', description: 'C' },
        ]}
        required
        defaultValue={'2'}
        multiple
      />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <br />
      <IbsDatePicker
        //startDate={new Date(startDate.setMonth(startDate.getMonth() + 1))}
        label='test date'
        ref={dateRef}
        formControllStyle={{ marginRight: 10 }}
        onChangeIbsDatePicker={(date: Date) => {
          console.log(format(date, 'yyyy-MM-dd'))
        }}
      />
      <IbsDatePicker
        ref={dateRef2}
        label='test month'
        type='month'
        formControllStyle={{ marginRight: 10 }}
      />
      <IbsDatePicker
        ref={dateRef3}
        label='test week'
        type='week'
        formControllStyle={{ marginRight: 10 }}
      />
      <br />
      <br />
      <IbsTypeButton
        buttontype={'save'}
        formControllStyle={{ marginRight: 10 }}
        onClick={() => {
          console.log(
            format(dateRef3.current!.getDate().startDate, 'yyyy-MM-dd'),
            format(dateRef3.current!.getDate().endDate, 'yyyy-MM-dd'),
          )
        }}
      />
      <IbsTypeButton
        buttontype={'update'}
        formControllStyle={{ marginRight: 10 }}
      />
      <IbsTypeButton
        buttontype={'delete'}
        formControllStyle={{ marginRight: 10 }}
      />
      <IbsTypeButton
        buttontype={'search'}
        formControllStyle={{ marginRight: 10 }}
      />
      <IbsTypeButton buttontype={'excel'} />
      <div className={'mt-20'}>
        <IbsButton variant='contained' width={100}>
          add
        </IbsButton>
      </div>
    </div>
  )
}

export default Test1

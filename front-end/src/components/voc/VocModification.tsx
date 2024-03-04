import {
  useState,
  useEffect,
  useRef,
  memo,
  forwardRef,
  useImperativeHandle,
} from 'react'

import IbsCombobox, { IbsComboboxHandle } from '@components/common/IbsCombobox'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@components/common/IbsTextField'
import IbsDatePicker, {
  IbsDatePickerHandel,
} from '@components/common/IbsDatePicker'
import IbsEditor from '@components/common/IbsEditor'
import IbsAttachment from '@components/common/IbsAttachment'

import { colors } from '@components/styles/colors'
import ibsAxios from '@/utils/ibsAxios'
import { daysFromToday, vocBaseFormat } from '@/utils/dateUtils'

interface VocModificationProps {
  data: any
}

export type VocModificationHandle = {
  getFormData: () => object
}

const VocModification = forwardRef<VocModificationHandle, VocModificationProps>(
  (props, ref) => {
    useImperativeHandle(ref, () => ({
      getFormData,
    }))

    const [content, setContent] = useState(props.data.requirement)

    const refs = {
      requestKindCombo: useRef<IbsComboboxHandle>(null),
      importanceCombo: useRef<IbsComboboxHandle>(null),
      personInChargeCombo: useRef<IbsComboboxHandle>(null),
      progress: useRef<IbsComboboxHandle>(null),
      menuText: useRef<IbsTextFieldHandle>(null),
      deliveryRequestDate: useRef<IbsDatePickerHandel>(null),
    }

    useEffect(() => {
      ibsAxios.get('/combo-box/person-in-charge').then((response: any) => {
        refs.personInChargeCombo.current!.setItems(response.data)
      })
    }, [])

    const getFormData = () => {
      const formData = new FormData()
      formData.append('systemName', props.data.systemName)
      formData.append('plant', props.data.plant)
      formData.append('revisionNo', props.data.revisionNo)
      formData.append('receptionDept', '')
      formData.append(
        'requiredResponseDate',
        vocBaseFormat(refs.deliveryRequestDate.current!.getDate().startDate),
      )
      formData.append(
        'personInCharge',
        refs.personInChargeCombo.current!.getSelectedValues()[0],
      )
      formData.append('requirement', content)
      //formData.append('menu', refs.menuText.current!.getValue())
      formData.append(
        'remark',
        refs.importanceCombo.current!.getSelectedValues()[0],
      )
      formData.append(
        'classification',
        refs.requestKindCombo.current!.getSelectedValues()[0],
      )

      //requestKind: refs.requestKindCombo.current!.getSelectedValues()[0],

      return formData
    }

    return (
      <>
        <IbsCombobox
          ref={refs.requestKindCombo}
          label='요청종류'
          width='130px'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[
            { value: 'new', displayValue: '신규', color: `${colors.yellow}` },
            { value: 'error', displayValue: '오류', color: `${colors.red}` },
            {
              value: 'improvement',
              displayValue: '개선',
              color: `${colors.green}`,
            },
          ]}
          defaultValue={props.data.classification}
        />
        <IbsTextField
          label='System'
          width='150px'
          formControllStyle={{ marginRight: '10px' }}
          defaultValue={props.data.systemName}
          disabled={true}
        />
        <IbsTextField
          ref={refs.menuText}
          label='메뉴'
          formControllStyle={{ marginRight: '10px' }}
          defaultValue={props.data.menu}
        />
        <IbsTextField
          label='처리번호'
          width='150px'
          formControllStyle={{ marginRight: '10px' }}
          defaultValue={props.data.qmsNumber}
          disabled={true}
        />
        <IbsDatePicker
          label='요청일'
          formControllStyle={{ marginRight: '10px' }}
          startDate={props.data.issueDate}
          readOnly={true}
        />
        <IbsDatePicker
          ref={refs.deliveryRequestDate}
          label='납기 요청일'
          formControllStyle={{ marginRight: '10px' }}
          startDate={props.data.requiredResponseDate}
        />
        <IbsCombobox
          ref={refs.importanceCombo}
          label='중요도'
          width='130px'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[
            {
              value: 'emergency',
              displayValue: '긴급',
              color: `${colors.red}`,
            },
            {
              value: 'fast',
              displayValue: '빠름',
              color: `${colors.yellow}`,
            },
            { value: 'normal', displayValue: '보통', color: `${colors.green}` },
          ]}
          defaultValue={props.data.remark}
        />
        <IbsCombobox
          ref={refs.personInChargeCombo}
          label='담당자'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[{ displayValue: '', value: '' }]}
          defaultValue={props.data.personInCharge}
        />
        <br />
        <br />
        <IbsCombobox
          ref={refs.progress}
          label='진행 상황'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[
            {
              value: 'notReceived',
              displayValue: '접수 대기',
              color: `${colors.orange}`,
            },
            {
              value: 'proceeding',
              displayValue: '진행중',
              color: `${colors.yellow}`,
            },
            {
              value: 'developmentComplete',
              displayValue: '개발 완료',
              color: `${colors.green}`,
            },
            {
              value: 'developmentApply',
              displayValue: '개발 적용',
              color: `${colors.blue}`,
            },
            {
              value: 'complete',
              displayValue: '완료',
              color: `${colors.skyBlue}`,
            },
            {
              value: 'drop',
              displayValue: 'DROP',
              color: `${colors.black}`,
            },
            {
              value: 'delay',
              displayValue: '지연',
              color: `${colors.blueGrren}`,
            },
          ]}
          defaultValue={props.data.progress}
        />
        <IbsDatePicker
          ref={refs.deliveryRequestDate}
          label='완료 예정일'
          formControllStyle={{ marginRight: '10px' }}
          startDate={props.data.expectedCompletionDate}
        />
        <IbsDatePicker
          ref={refs.deliveryRequestDate}
          label='완료일'
          formControllStyle={{ marginRight: '10px' }}
          startDate={props.data.closedDate}
        />
        <br />
        <br />
        <IbsEditor
          width='800px'
          height='300px'
          content={content}
          setContent={setContent}
        />
      </>
    )
  },
)

export default VocModification

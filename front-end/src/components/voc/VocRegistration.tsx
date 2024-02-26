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

import ibsAxios from '@/utils/ibsAxios'
import { colors } from '@components/styles/colors'
import IbsEditor from '@components/common/IbsEditor'
import IbsAttachment, {
  IbsAttachmentHandle,
} from '@components/common/IbsAttachment'

import { format } from 'date-fns'

interface VocRegistrationProps {}

export type VocRegistrationHandle = {
  getFormData: () => object
}

const VocRegistration = forwardRef<VocRegistrationHandle, VocRegistrationProps>(
  (vocRegistrationProps, ref) => {
    useImperativeHandle(ref, () => ({
      getFormData,
    }))

    const [content, setContent] = useState('')

    const refs = {
      requestKindCombo: useRef<IbsComboboxHandle>(null),
      importanceCombo: useRef<IbsComboboxHandle>(null),
      systemCombo: useRef<IbsComboboxHandle>(null),
      plantCombo: useRef<IbsComboboxHandle>(null),
      managerCombo: useRef<IbsComboboxHandle>(null),
      menuText: useRef<IbsTextFieldHandle>(null),
      deliveryRequestDate: useRef<IbsDatePickerHandel>(null),
      attachment: useRef<IbsAttachmentHandle>(null),
    }

    useEffect(() => {
      ibsAxios.get('/combo-box/plant').then((response: any) => {
        refs.plantCombo.current!.setItems(response.data)
      })
    }, [])

    const getFormData = () => {
      const formData = new FormData()
      formData.append('plant', refs.plantCombo.current!.getSelectedValues()[0])
      formData.append('systemName', 'voc')
      formData.append('revisionNo', '1')
      formData.append('receptionDept', '')
      formData.append(
        'requiredResponseDate',
        format(
          refs.deliveryRequestDate.current!.getDate().startDate,
          'yyyyMMddHHmmss',
        ),
      )
      formData.append(
        'personInCharge',
        refs.managerCombo.current!.getSelectedValues()[0],
      )
      formData.append('requirement', content)
      formData.append('file', refs.attachment.current!.getFile())
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
          required
        />
        <IbsCombobox
          ref={refs.systemCombo}
          label='System'
          width='150px'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[{ displayValue: '', value: '' }]}
          required
        />
        <IbsCombobox
          ref={refs.plantCombo}
          label='Plant'
          width='150px'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[{ displayValue: '', value: '' }]}
          required
        />
        <IbsTextField
          ref={refs.menuText}
          label='메뉴'
          formControllStyle={{ marginRight: '10px' }}
        />
        <IbsDatePicker
          ref={refs.deliveryRequestDate}
          label='납기 요청일'
          formControllStyle={{ marginRight: '10px' }}
        />
        <IbsCombobox
          ref={refs.importanceCombo}
          label='중요도'
          width='130px'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[
            { value: 'first', displayValue: '1순위', color: `${colors.red}` },
            {
              value: 'second',
              displayValue: '2순위',
              color: `${colors.yellow}`,
            },
            { value: 'third', displayValue: '3순위', color: `${colors.green}` },
          ]}
        />
        <IbsCombobox
          ref={refs.managerCombo}
          label='담당자'
          formControllStyle={{ marginRight: '10px' }}
          defaultItems={[{ displayValue: '', value: '' }]}
        />
        <br />
        <br />
        <IbsAttachment ref={refs.attachment} />
        <br />
        <br />
        <IbsEditor
          width='800px'
          height='270px'
          content={content}
          setContent={setContent}
        />
      </>
    )
  },
)

export default memo(VocRegistration)

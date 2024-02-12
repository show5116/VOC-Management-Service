import { forwardRef, useEffect, useRef, useImperativeHandle } from 'react'

import IbsCombobox, { IbsComboboxHandle } from '@components/common/IbsCombobox'
import IbsTextField, {
  IbsTextFieldHandle,
} from '@components/common/IbsTextField'
import IbsDatePicker, {
  IbsDatePickerHandel,
} from '@components/common/IbsDatePicker'

import ibsAxios from '@/utils/ibsAxios'
import { colors } from '@components/styles/colors'
import IbsEditor, { IbsEditorHandle } from '@components/common/IbsEditor'
import IbsAttachment, {
  IbsAttachmentHandle,
} from '@components/common/IbsAttachment'

interface VocRegistrationProps {}

export type VocRegistrationHandle = {
  getData: () => object
}

const VocRegistration = forwardRef<VocRegistrationHandle, VocRegistrationProps>(
  (vocRegistrationProps, ref) => {
    useImperativeHandle(ref, () => ({
      getData,
    }))

    const refs = {
      requestKindCombo: useRef<IbsComboboxHandle>(null),
      importanceCombo: useRef<IbsComboboxHandle>(null),
      plantCombo: useRef<IbsComboboxHandle>(null),
      managerCombo: useRef<IbsComboboxHandle>(null),
      menuText: useRef<IbsTextFieldHandle>(null),
      deliveryRequestDate: useRef<IbsDatePickerHandel>(null),
      attachment: useRef<IbsAttachmentHandle>(null),
      editor: useRef<IbsEditorHandle>(null),
    }

    useEffect(() => {
      ibsAxios.get('/combo-box/plant').then((response: any) => {
        refs.plantCombo.current!.setItems(response.data)
      })
    }, [])

    const getData = () => ({
      requestKind: refs.requestKindCombo.current!.getSelectedValues()[0],
      importance: refs.importanceCombo.current!.getSelectedValues()[0],
      plant: refs.plantCombo.current!.getSelectedValues()[0],
      manager: refs.managerCombo.current!.getSelectedValues()[0],
      menu: refs.menuText.current!.getValue(),
      deliveryRequestDate:
        refs.deliveryRequestDate.current!.getDate().startDate,
      attachment: refs.attachment.current!.getFile(),
      content: refs.editor.current!.getContent(),
    })

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
        <IbsAttachment ref={refs.attachment} />
        <br />
        <br />
        <IbsEditor ref={refs.editor} width='800px' height='500px' />
      </>
    )
  },
)

export default VocRegistration

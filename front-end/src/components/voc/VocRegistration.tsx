import IbsCombobox from '@components/common/IbsCombobox'

import { colors } from '@components/styles/colors'

interface IProps {}

const VocRegistration = (props: IProps) => {
  return (
    <>
      <IbsCombobox
        label='요청종류'
        formControllStyle={{}}
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
      <div>모달창</div>
    </>
  )
}

export default VocRegistration

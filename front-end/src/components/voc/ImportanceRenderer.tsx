import { memo } from 'react'
import { Colors, colors } from '../styles/colors'
import { TRequestKind } from '@components/voc/RequestKindRenderer'

export type TImportance = 'first' | 'second' | 'third' | 'none'

export const importanceArray = ['first', 'second', 'third', 'none']

interface ICellStyle {
  backgroundColor: (typeof colors)[Colors]
  value: string
}

const importance: Record<TImportance, ICellStyle> = {
  first: {
    backgroundColor: colors.red,
    value: '1순위',
  },
  second: {
    backgroundColor: colors.yellow,
    value: '신규',
  },
  third: {
    backgroundColor: colors.green,
    value: '신규',
  },
  none: {
    backgroundColor: colors.white,
    value: '',
  }
}

interface IProps {
  value: TImportance
}

const ImportanceRenderer = (props: IProps) => {
  return (
    <div
      style={{
        fontWeight: 500,
        width: 90,
        textAlign: 'center',
        backgroundColor: importance[props.value].backgroundColor,
      }}
    >
      {importance[props.value].value}
    </div>
  )
}

export default ImportanceRenderer

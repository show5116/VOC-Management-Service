import { memo } from 'react'
import { Colors, colors } from '../styles/colors'
import { TRequestKind } from '@components/voc/RequestKindRenderer'

export type TImportance = 'emergency' | 'fast' | 'normal'

export const importanceArray = ['emergency', 'fast', 'normal']

interface ICellStyle {
  backgroundColor: (typeof colors)[Colors]
  value: string
}

const importance: Record<TImportance, ICellStyle> = {
  emergency: {
    backgroundColor: colors.red,
    value: '긴급',
  },
  fast: {
    backgroundColor: colors.yellow,
    value: '빠름',
  },
  normal: {
    backgroundColor: colors.green,
    value: '보통',
  },
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
        backgroundColor: importance[props.value]?.backgroundColor,
      }}
    >
      {importance[props.value]?.value}
    </div>
  )
}

export default ImportanceRenderer

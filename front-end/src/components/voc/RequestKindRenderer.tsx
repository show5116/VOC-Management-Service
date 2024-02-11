import { memo } from 'react'

import { Colors, colors } from '@/components/styles/colors'

export type TRequestKind = 'new' | 'error' | 'improvement'

export const requestKindArray = ['new', 'error', 'improvement']

interface ICellStyle {
  backgroundColor: (typeof colors)[Colors]
  value: string
}

const requestKind: Record<TRequestKind, ICellStyle> = {
  new: {
    backgroundColor: colors.yellow,
    value: '신규',
  },
  error: {
    backgroundColor: colors.red,
    value: '오류',
  },
  improvement: {
    backgroundColor: colors.green,
    value: '개선',
  },
}

interface IProps {
  value: TRequestKind
}

const RequestKindRenderer = (props: IProps) => {
  return (
    <div
      style={{
        fontWeight: 500,
        width: 90,
        textAlign: 'center',
        backgroundColor: requestKind[props.value]?.backgroundColor,
      }}
    >
      {requestKind[props.value]?.value}
    </div>
  )
}

export default memo(RequestKindRenderer)

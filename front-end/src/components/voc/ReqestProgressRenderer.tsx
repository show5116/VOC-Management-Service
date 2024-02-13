import { memo } from 'react'

import { Colors, colors } from '@/components/styles/colors'

export type TRequestProgress =
  | 'notReceived'
  | 'proceeding'
  | 'developmentComplete'
  | 'developmentApply'
  | 'complete'
  | 'drop'
  | 'delay'

export const requestProgressArray = [
  'notReceived',
  'proceeding',
  'developmentComplete',
  'developmentApply',
  'complete',
  'drop',
  'delay',
]

interface ICellStyle {
  backgroundColor: (typeof colors)[Colors]
  color?: (typeof colors)[Colors]
  value: string
}

const requestProgress: Record<TRequestProgress, ICellStyle> = {
  notReceived: {
    backgroundColor: colors.orange,
    value: '접수 대기',
  },
  proceeding: {
    backgroundColor: colors.yellow,
    value: '진행중',
  },
  developmentComplete: {
    backgroundColor: colors.green,
    value: '개발 완료',
  },
  developmentApply: {
    backgroundColor: colors.blue,
    value: '개발 적용',
  },
  complete: {
    backgroundColor: colors.skyBlue,
    value: '완료',
  },
  drop: {
    backgroundColor: colors.black,
    color: colors.white,
    value: 'DROP',
  },
  delay: {
    backgroundColor: colors.blueGrren,
    color: colors.white,
    value: '지연',
  },
}

interface IProps {
  value: TRequestProgress
}

const RequestProgressRenderer = (props: IProps) => {
  return (
    <div
      style={{
        fontWeight: 500,
        width: 90,
        textAlign: 'center',
        backgroundColor: requestProgress[props.value]?.backgroundColor,
        color: requestProgress[props.value]?.color,
      }}
    >
      {requestProgress[props.value]?.value}
    </div>
  )
}

export default memo(RequestProgressRenderer)

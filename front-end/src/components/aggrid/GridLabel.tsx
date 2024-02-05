import { colors } from '../styles/colors'

//data state value
export type lableState = 'completed' | 'notStarted' | 'blocked' | 'inProgress'
/* data value : display value*/
export const label = {
  completed: 'Completed',
  blocked: 'Blocked',
  inProgress: 'In Progress',
  notStarted: 'Not Started',
}

const GridLabel = (params: any) => {
  const getStyle = (label: any) => {
    switch (label) {
      case //'completed'||
      'Completed':
        return {
          backgroundColor: colors.lightGreen,
          color: colors.green,
        }
      case //'blocked' ||
      'Blocked':
        return {
          backgroundColor: colors.lightRed,
          color: colors.red,
        }
      case //'inProgress' ||
      'In Progress':
        return {
          backgroundColor: colors.lightYellow,
          color: colors.darkGrey,
        }
      case // 'notStarted' ||
      'Not Started':
        return {
          backgroundColor: colors.lightGrey,
          color: colors.lightBrown,
        }
    }
  }

  return (
    <div
      style={{
        fontWeight: 500,
        width: 90,
        textAlign: 'center',
        ...getStyle(params.value),
      }}
    >
      {params.value}
    </div>
  )
}

export default GridLabel

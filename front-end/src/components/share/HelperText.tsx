import { memo } from 'react'
import { FormHelperTextProps } from '@mui/material'
import styled from '@emotion/styled'

interface HelperTextProps extends FormHelperTextProps {
  required?: boolean
  limitCount?: number
  valueCount?: number
}

const HelperText = (helperTextProps: HelperTextProps) => {
  const { required, limitCount, valueCount } = helperTextProps

  let message: string = ''
  let countStr: string = ''

  if (required) {
    // message = label
    //   ? `* ${label} 값은 필수 입력값 입니다. `
    //   : `* 필수 입력값 입니다. `
    message = `* 필수 입력값 입니다.`
  }

  if (limitCount && valueCount && valueCount > limitCount) {
    message = `입력값이 초과하였습니다.`
  }
  if (limitCount && limitCount > 0) {
    countStr = `${valueCount} / ${limitCount}`
  }

  return (
    <>
      <CustomSpan>{`${message}`}</CustomSpan>
      <CustomSpan>{`${countStr}`}</CustomSpan>
    </>
  )
}

export default memo(HelperText)

const CustomSpan = styled.span(
  (helperTextProps: HelperTextProps) => ({}),
  (required) => ({
    //fontWeight: 'bold',
    //color: 'red',
  }),
)

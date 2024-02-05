import { SxProps } from '@mui/material'
import { CSSProperties } from 'react'
import { css } from '@emotion/react'

export const helperTextStyle = ({ width }: { width?: number | string }) => {
  const style: CSSProperties = {
    margin: 0,
    padding: '4px 0px',
    width: width ? width : '100%',
    display: 'flex',
    justifyContent: 'space-between',
  }
  return style
}

export const defaultFormControllStyle = ({
  width = 200,
}: {
  width?: number | string | undefined
}): SxProps => ({
  width: width ? width : '100%',
  m: 0,
})

export const subjectFont = css`
  font-size: 23px;
  font-weight: 500;
`

export const contentRadius = '15px'

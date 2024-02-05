import React from 'react'
import { Button, ButtonProps, FormControl } from '@mui/material'
import { IbsBaseProps } from '../share/model'
import { defaultFormControllStyle } from '../styles/shareStyle'

interface IbsButtonProps extends ButtonProps, IbsBaseProps {}

const IbsButton = (ibsButtonProps: IbsButtonProps) => {
  const { children, width, formControllStyle, ...props } = ibsButtonProps
  return (
    <FormControl
      sx={{ ...defaultFormControllStyle({ width: width }) }}
      style={formControllStyle}
    >
      <Button {...props}>{children}</Button>
    </FormControl>
  )
}

export default IbsButton

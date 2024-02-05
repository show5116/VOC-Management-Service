import React from 'react'
import styled from '@emotion/styled'
import { Button, ButtonProps, FormControl } from '@mui/material'
import { IbsBaseProps } from '../share/model'
import { colors } from '../styles/colors'
import { defaultFormControllStyle } from '../styles/shareStyle'
import { FaRegSave } from 'react-icons/fa'
import { RxUpdate } from 'react-icons/rx'
import {
  RiFileExcel2Line,
  RiDeleteBinLine,
  RiSearch2Line,
} from 'react-icons/ri'

interface IbsTypeButtonProps extends ButtonProps, IbsBaseProps {
  buttontype?: ButtonType
}

const IbsTypeButton = ({
  buttontype = 'save',
  children,
  width = 100,
  formControllStyle,
  ...props
}: IbsTypeButtonProps) => {
  return (
    <FormControl
      sx={{
        ...defaultFormControllStyle({ width: width }),
      }}
      style={formControllStyle}
    >
      <TypeButton
        startIcon={ButtonIcon[buttontype]}
        variant='contained'
        buttontype={buttontype}
        {...props}
      >
        {buttontype}
      </TypeButton>
    </FormControl>
  )
}

export default IbsTypeButton

type ButtonType = 'save' | 'update' | 'delete' | 'excel' | 'search'

const ButtonIcon = {
  save: <FaRegSave />,
  update: <RxUpdate />,
  delete: <RiDeleteBinLine />,
  excel: <RiFileExcel2Line />,
  search: <RiSearch2Line />,
}

const TypeButton = styled(Button)(
  () => ({
    textTransform: 'none',
    fontFamily: 'Play',
  }),
  ({ buttontype }: IbsTypeButtonProps) =>
    buttontype === 'save'
      ? {
          backgroundColor: colors.lightBlue,
          color: colors.white,
          ':hover': { backgroundColor: colors.blue },
        }
      : buttontype === 'delete'
        ? {
            backgroundColor: colors.red,
            color: colors.white,
            ':hover': { backgroundColor: colors.darkRed },
          }
        : buttontype === 'excel'
          ? {
              backgroundColor: colors.green,
              color: colors.white,
              ':hover': { backgroundColor: colors.darkGreen },
            }
          : buttontype === 'search'
            ? {
                backgroundColor: colors.brown,
                color: colors.white,
                ':hover': { backgroundColor: colors.darkBrown },
              }
            : buttontype === 'update'
              ? {
                  backgroundColor: colors.skyBlue,
                  color: colors.white,
                  ':hover': { backgroundColor: colors.darkSkyBlue },
                }
              : { color: '#000' },
)

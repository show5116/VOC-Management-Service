import styled from '@emotion/styled'
import { FormControl, TextField } from '@mui/material'
import { TextFieldProps } from '@mui/material/TextField'
import {
  ChangeEventHandler,
  forwardRef,
  memo,
  useEffect,
  useImperativeHandle,
  useRef,
  useState,
} from 'react'
import HelperText from '../share/HelperText'
import { IbsBaseProps } from '../share/model'
import {
  defaultFormControllStyle,
  helperTextStyle,
} from '@components/styles/shareStyle'

type ToCaseType = 'upper' | 'lowwer'

interface IbsTextFieldProps
  extends Omit<TextFieldProps, 'variant'>,
    IbsBaseProps {
  toCaseType?: ToCaseType
  limitCount?: number
  onChangeTextField?: () => void
}

export type IbsTextFieldHandle = {
  setValue: (value: string) => void
  getValue: () => string
  focus: () => void
  isLimitValid: () => boolean
  isRequiredValid: () => boolean
}

const IbsTextField = forwardRef<IbsTextFieldHandle, IbsTextFieldProps>(
  (textFieldProps, ref) => {
    useImperativeHandle(ref, () => ({
      setValue,
      getValue,
      focus,
      isLimitValid,
      isRequiredValid,
    }))
    const {
      defaultValue = '',
      onChangeTextField,
      toCaseType,
      limitCount,
      required,
      label = '',
      fullWidth = false,
      width = !fullWidth ? 220 : 0,
      size = 'small',
      formControllStyle,
      ...props
    } = textFieldProps

    const [value, setValue] = useState<string>(defaultValue as string)
    const [isError, setIsError] = useState<boolean>(required ? true : false)
    const inputRef = useRef<HTMLInputElement>(null)

    useEffect(() => {
      if (onChangeTextField) onChangeTextField()
    })

    const getValue = () => value

    const focus = () => inputRef.current?.focus()

    const isLimitValid = () => (isError ? false : true)

    const isRequiredValid = () => (value.length > 0 ? true : false)

    const onchangeValue: ChangeEventHandler<HTMLInputElement> = (event) => {
      event.preventDefault()

      const {
        target: { value },
      } = event

      if ((limitCount && limitCount < value.length) || value.length === 0) {
        setIsError(true)
      } else {
        setIsError(false)
      }

      switch (toCaseType) {
        case 'upper':
          setValue(value.toUpperCase())
          break
        case 'lowwer':
          setValue(value.toLowerCase())
          break
        default:
          setValue(value)
          break
      }
    }

    return (
      <FormControl
        sx={{ ...defaultFormControllStyle({ width: width }) }}
        style={formControllStyle}
      >
        <CustomTextField
          {...props}
          size={size}
          label={label}
          required={required}
          error={isError}
          helperText={
            <HelperText
              required={required}
              limitCount={limitCount}
              valueCount={value.length}
            />
          }
          FormHelperTextProps={{
            style: helperTextStyle({ width }),
            required: required,
          }}
          value={value}
          inputRef={inputRef}
          inputProps={{ autoComplete: 'off' }}
          onChange={onchangeValue}
        />
      </FormControl>
    )
  },
)

export default memo(IbsTextField)

const CustomTextField = styled(TextField)({
  // '& .MuiOutlinedInput-notchedOutline': {
  //   borderColor: '#fff',
  // },
})

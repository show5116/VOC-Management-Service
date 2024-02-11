import {
  Checkbox,
  FormControl,
  FormHelperText,
  InputLabel,
  MenuItem,
  Select,
  SelectChangeEvent,
  SelectProps,
} from '@mui/material'
import {
  Dispatch,
  forwardRef,
  memo,
  SetStateAction,
  useEffect,
  useImperativeHandle,
  useState,
} from 'react'
import HelperText from '../share/HelperText'
import { IbsBaseProps } from '../share/model'
import { defaultFormControllStyle, helperTextStyle } from '../styles/shareStyle'
import { FaCheck, FaChevronDown, FaChevronUp } from 'react-icons/fa6'
import { Colors, colors } from '@components/styles/colors'

export interface Item {
  displayValue: string
  value: string
  description?: string
  color?: (typeof colors)[Colors]
}

interface IbsComboboxProps extends SelectProps, IbsBaseProps {
  defaultItems?: Item[]
  blank?: boolean
  checkbox?: boolean
  onChangeCombobox?: () => void
  defaultValue?: string
}

export type IbsComboboxHandle = {
  setItems: Dispatch<SetStateAction<Item[]>>
  getSelectedItems: () => Item[]
  getSelectedValues: () => string[]
  setValue: (value: string) => void
}

const IbsCombobox = forwardRef<IbsComboboxHandle, IbsComboboxProps>(
  (comboboxProps, ref) => {
    useImperativeHandle(ref, () => ({
      setItems,
      getSelectedItems,
      getSelectedValues,
      setValue,
    }))

    const {
      defaultItems = [],
      required,
      label,
      width,
      size = 'small',
      multiple,
      checkbox,
      onChangeCombobox,
      defaultValue = '',
      formControllStyle,
      ...props
    } = comboboxProps

    let { blank = false } = comboboxProps

    if (multiple) blank = false

    const [items, setItems] = useState<Item[]>(defaultItems)
    const [values, setValues] = useState<string[]>([
      defaultValue ? defaultValue : blank ? 'null' : defaultItems[0].value,
    ])
    const [isOpen, setIsOpen] = useState<boolean>(false)
    const [isError, setIsError] = useState<boolean>(false)

    useEffect(() => {
      if (
        required &&
        (values.length < 1 || values[0].length < 1 || values[0] === 'null')
      )
        setIsError(true)
      else setIsError(false)
      if (onChangeCombobox) onChangeCombobox()
    }, [onChangeCombobox, required, setIsError, values])

    const setValue = (value: string) => setValues([value])

    const getSelectedItems = () =>
      items.filter((item) => values.includes(item.value))

    const getSelectedValues = () => values

    const onChangeValue = (event: SelectChangeEvent<any>) => {
      if (!items) return false

      const {
        target: { value },
      } = event

      if (multiple) {
        setValues(typeof value === 'string' ? value.split(',') : value)
      } else {
        setValues([value])
      }
    }

    const render = (selected: unknown) => {
      const selectedValues = selected as string[]
      return items
        .filter((item) => selectedValues.includes(item.value))
        .map((item) => item.displayValue)
        .join(', ')
    }

    return (
      <FormControl
        size={size}
        sx={{ ...defaultFormControllStyle({ width: width }) }}
        style={formControllStyle}
      >
        <InputLabel error={isError}>
          {required ? `${label} *` : label}
        </InputLabel>
        <Select
          error={isError}
          {...props}
          label={required ? `${label} *` : label}
          size={size}
          IconComponent={() => {
            return isOpen ? (
              <FaChevronUp style={{ marginRight: 15, cursor: 'pointer' }} />
            ) : (
              <FaChevronDown style={{ marginRight: 15, cursor: 'pointer' }} />
            )
          }}
          onOpen={() => setIsOpen(true)}
          onClose={() => setIsOpen(false)}
          onChange={onChangeValue}
          value={values}
          multiple={multiple}
          renderValue={render}
        >
          {blank && <MenuItem value={'null'}>{<>&nbsp;</>}</MenuItem>}
          {items?.map((item) => (
            <MenuItem key={item.value} value={item.value}>
              {item.color && (
                <div
                  style={{
                    marginRight: '10px',
                    width: '10px',
                    height: '10px',
                    backgroundColor: `${item.color}`,
                  }}
                />
              )}
              {checkbox && (
                <Checkbox checked={values.indexOf(item.value) > -1} />
              )}
              {item.displayValue}
              {!checkbox && values.indexOf(item.value) > -1 && (
                <FaCheck
                  style={{ position: 'absolute', right: 15, opacity: 0.5 }}
                />
              )}
            </MenuItem>
          ))}
        </Select>
        {required && (
          <FormHelperText error={isError} style={helperTextStyle({})}>
            <HelperText required={required} />
          </FormHelperText>
        )}
      </FormControl>
    )
  },
)

export default memo(IbsCombobox)

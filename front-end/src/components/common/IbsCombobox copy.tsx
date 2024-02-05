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

export interface Item {
  displayValue: string
  value: string
  description?: string
}

interface IbsComboboxProps extends SelectProps, IbsBaseProps {
  defaultItems?: Item[]
  blank?: boolean
  checkbox?: boolean
  onChangeCombobox?: () => void
  defaultValue?: string
  includeAll?: boolean
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
      blank,
      required,
      label,
      width,
      size = 'small',
      multiple,
      checkbox,
      onChangeCombobox,
      defaultValue = '',
      includeAll,
      ...props
    } = comboboxProps
    const all: Item = { displayValue: 'All', value: 'All' }
    const [items, setItems] = useState<Item[]>(defaultItems)
    const [values, setValues] = useState<string[]>([
      defaultValue ? defaultValue : defaultItems[0].value,
    ])
    const [isOpen, setIsOpen] = useState<boolean>(false)

    useEffect(() => {
      if (onChangeCombobox) onChangeCombobox()
    })

    const setValue = (value: string) => setValues([value])

    const getSelectedItems = () =>
      items.filter((item) => values.includes(item.value))

    const getSelectedValues = () => {
      if (includeAll) {
        return values.filter((value: string) => value !== 'All')
      } else {
        return values
      }
    }

    const onChangeValue = (
      event: SelectChangeEvent<any>,
      selectedOptions: any,
    ) => {
      if (!items) return false

      const {
        target: { value },
      } = event
      const checkValue = selectedOptions.props.value
      console.log('checkValue', checkValue)
      console.log('value', value)
      // 'All' 항목을 체크/해제하는 로직
      if (includeAll) {
        if (value.includes('All')) {
          if (checkValue === 'All') {
            //All 클릭시 전체 선택
            setValues(
              value.includes('All') ? items.map((item) => item.value) : [],
            )
          } else {
            //All을 클릭하였지만, 다른 item 클릭시, All 체크 해제
            setValues(value.filter((value: string) => value !== 'All'))
          }
        } else {
          const newValues = typeof value === 'string' ? value.split(',') : value
          if (checkValue === 'All') {
            setValues([])
          } else {
            const allChecked = newValues.length === items.length - 1
            setValues(allChecked ? items.map((item) => item.value) : newValues)
          }
        }
      } else {
        // 기존 로직
        setValues(typeof value === 'string' ? value.split(',') : value)
      }
    }

    const render = (selected: unknown) => {
      const selectedValues = selected as string[]
      return items
        .filter((item) => selectedValues.includes(item.value))
        .map((item) => item.displayValue)
        .join(', ')
    }
    const customSetItems = (newItems: Item[]) => {
      if (includeAll && !newItems.find((item) => item.value === 'All')) {
        //All 아이템 추가
        setItems([all, ...newItems])
      } else {
        setItems(newItems)
      }
    }

    // items 상태가 변경될 때마다 실행됩니다.
    useEffect(() => {
      customSetItems(items)
    }, [items])

    return (
      <FormControl
        size={size}
        sx={{ ...defaultFormControllStyle({ width: width }) }}
      >
        <InputLabel>{required ? `${label} *` : label}</InputLabel>
        <Select
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
          multiple={includeAll ? true : multiple}
          renderValue={render}
        >
          {blank && <MenuItem value={'null'}>{'None'}</MenuItem>}
          {items?.map((item) => (
            <MenuItem key={item.value} value={item.value}>
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
          <FormHelperText style={helperTextStyle({})}>
            <HelperText required={required} />
          </FormHelperText>
        )}
      </FormControl>
    )
  },
)

export default memo(IbsCombobox)

import { Dispatch, SetStateAction } from 'react'

import { Modal as BaseModal, styled, css } from '@mui/material'

import { colors } from '@components/styles/colors'
import { FaWindowClose } from 'react-icons/fa'

interface IProps {
  open: boolean
  setOpen: Dispatch<SetStateAction<boolean>>
  width: string | number
  height: string | number
  title: string
  button?: JSX.Element
  children: JSX.Element
}

const IbsModal = (props: IProps) => {
  return (
    <Modal
      open={props.open}
      onClose={() => props.setOpen(false)}
      aria-labelledby='ls-manager-modal'
      aria-describedby='ls-manager-modal-description'
    >
      <ModalContent
        sx={{
          width: `${props.width}`,
          height: `${props.height}`,
        }}
      >
        <div className='modal-header'>
          <h2 className='modal-title'>{props.title}</h2>
          <div>
            {props.button}
            <FaWindowClose
              style={{
                width: '30px',
                height: '30px',
              }}
              cursor='pointer'
              onClick={() => props.setOpen(false)}
            />
          </div>
        </div>
        <p className='modal-description'>{props.children}</p>
      </ModalContent>
    </Modal>
  )
}

const Modal = styled(BaseModal)`
  position: fixed;
  z-index: 1300;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
`

const ModalContent = styled('div')(
  ({ theme }) => css`
    font-family: 'IBM Plex Sans', sans-serif;
    font-weight: 500;
    text-align: start;
    position: relative;
    display: flex;
    flex-direction: column;
    gap: 8px;
    overflow: auto;
    background-color: ${colors.white};
    border-radius: 8px;
    border: 1px solid ${colors.grey};
    box-shadow: 0 4px 12px rgb(0 0 0 / 0.2);
    padding: 24px;

    & .modal-header {
      display: flex;
      justify-content: space-between;
      border-bottom: 2px solid ${colors.lightGreen};
      margin-bottom: 8px;
    }
    & .modal-title {
      margin: 0;
      line-height: 1.5rem;
      margin-bottom: 8px;
    }
    & .modal-description {
      margin: 0;
      line-height: 1.5rem;
      font-weight: 400;
      margin-bottom: 4px;
    }
  `,
)

export default IbsModal

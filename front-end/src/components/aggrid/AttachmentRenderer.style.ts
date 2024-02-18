import { styled } from 'styled-components'
import { colors } from '@components/styles/colors'

export const PopupBody = styled.div`
  display: flex;
  flex-direction: row;
  gap: 10px;
  width: 100px;
  height: 50px;
  padding: 20px 16px;
  margin: 8px;
  border-radius: 8px;
  border: 1px solid ${colors.darkGrey};
  background-color: ${colors.white};
  box-shadow: 0px 4px 8px rgb(0 0 0 / 0.1);
  font-weight: 500;
  font-size: 0.875rem;
  z-index: 1;
`

export const CloseButton = styled.button`
  position: absolute;
  top: 15px;
  right: 15px;
  width: 15px;
  height: 15px;
  cursor: pointer;
`

export const AttachmentButton = styled.button`
  display: flex;
  flex-direction: column;
  cursor: pointer;
`

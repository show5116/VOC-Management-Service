import { styled } from 'styled-components'
import { colors } from '@components/styles/colors'

export const Container = styled.div`
  cursor: pointer;
`

export const PopupBody = styled.div`
  display: flex;
  flex-direction: column;
  gap: 14px;
  width: 120px;
  height: 70px;
  padding: 6px 10px;
  margin: 8px;
  border: 1px solid ${colors.darkGrey};
  border-radius: 6px;
  background-color: ${colors.white};
  box-shadow: 0px 4px 8px rgb(0 0 0 / 0.1);
  font-weight: 500;
  font-size: 0.875rem;
  z-index: 1;
`

export const PopupHeader = styled.div`
  display: flex;
  justify-content: space-between;
  color: ${colors.lightBlack};

  h3 {
    font-size: 0.8rem;
    font-weight: 700;
  }

  svg {
    cursor: pointer;
  }
`

export const ButtonGroup = styled.div`
  display: flex;
  flex-direction: row;
  gap: 14px;
`

export const AttachmentButton = styled.button`
  display: flex;
  flex-direction: column;
  cursor: pointer;
  align-items: center;

  svg {
    opacity: 1;
  }

  &:hover {
    svg {
      opacity: 0.7;
    }
  }

  span {
    color: ${colors.darkGrey};
    font-size: 0.9rem;
  }
`

export const NonFileSpan = styled.span`
  opacity: 0.7;
`

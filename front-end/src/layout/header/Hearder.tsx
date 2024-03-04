import { css } from '@emotion/react'

import { layoutHeights, layoutWidths } from '@/components/styles/layoutStyles'
import MenuList from './MenuList'
import { colors } from '@/components/styles/colors'
import Logo from '@/assets/img/ibs_logo.png'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react'
import styled from '@emotion/styled'

const HeaderContainer = styled.div(() => ({
  height: `${layoutHeights.header}`,
  borderBottom: `2px solid ${colors.deepBlueGreen}`,
  backgroundColor: `${colors.white}`,
  position: `relative`,
  display: `flex`,
}))

const logoWrapCss = css`
  width: ${layoutWidths.left};
  padding: 8px 15px;
  display: flex;
  > img {
    cursor: pointer;
  }
  > div {
    height: 100%;
    border-left: 0.5em solid ${colors.blueGrren};
    margin-right: 10px;
  }
`

const headerWarapCss = css`
  width: ${layoutWidths.content};
  padding: 15px;
  font-family: Play;
  display: flex;
  align-items: center;
`

const Hearder = () => {
  const [isActive, setIsActive] = useState<boolean>(false)

  const navigeter = useNavigate()
  const onClickLogo = () => {
    setIsActive(false)
    navigeter('/')
  }

  return (
    <HeaderContainer
      onMouseLeave={() => {
        setIsActive(false)
      }}
    >
      <div css={logoWrapCss}>
        <div></div>
        <img
          src={Logo}
          alt={'QMS logo'}
          onClick={onClickLogo}
          title={'Dashboard'}
        />
      </div>
      <div
        css={headerWarapCss}
        onMouseEnter={() => {
          setIsActive(true)
        }}
      >
        <MenuList isActive={isActive} setIsActive={setIsActive} />
      </div>
    </HeaderContainer>
  )
}

export default Hearder

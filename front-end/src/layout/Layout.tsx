import { colors } from '@/components/styles/colors'
import { layoutHeights } from '@/components/styles/layoutStyles'
import { css } from '@emotion/react'
import styled from '@emotion/styled'
import React from 'react'
import Contents from './contents/Contents'
import Hearder from './header/Hearder'
import WorkTabel from './left/WorkTabel'

export const FlexDiv = styled.div(
  ({ main }: { main?: boolean }) => ({
    display: 'flex',
  }),
  ({ main }) => (main ? { height: `${layoutHeights.body}` } : undefined),
)
const layoutCss = css`
  background-color: ${colors.white};
`
const Layout = ({ children }: { children: React.ReactNode }) => {
  return (
    <div css={layoutCss}>
      <Hearder />
      <FlexDiv main>
        <WorkTabel />
        <Contents children={children} />
      </FlexDiv>
    </div>
  )
}

export default Layout

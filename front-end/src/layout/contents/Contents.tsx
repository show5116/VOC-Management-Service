import { colors } from '@/components/styles/colors'
import { layoutWidths } from '@/components/styles/layoutStyles'
import { css } from '@emotion/react'

const contentsContainerCss = css`
  width: ${layoutWidths.content};
  padding: 15px;
`
const contentWrapCss = css`
  background-color: ${colors.white};
  height: 100%; //calc(100% - 30px);
`
const pagesCss = css`
  padding: 10px 0px;
  height: calc(100% - 20px);
  overflow: auto;
`

const Contents = ({ children }: { children: React.ReactNode }) => {
  return (
    <div css={contentsContainerCss}>
      <div css={contentWrapCss}>
        <div css={pagesCss}>{children}</div>
      </div>
    </div>
  )
}

export default Contents

import { css } from '@emotion/react'

export const layoutStyles = css`
  :root {
    --header-height: 70px;
    --left-width: 14%;
  }
`

export const layoutHeights = {
  header: 'var(--header-height)',
  body: 'calc(100vh - (var(--header-height) + 3px ))',
}

export const layoutWidths = {
  left: 'var(--left-width)',
  content: '100%',
}

export type LayoutHeights = keyof typeof layoutHeights
export type LayoutWidths = keyof typeof layoutWidths

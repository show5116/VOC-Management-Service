import { css } from '@emotion/react'
/**
  579590
  E1F1FD
  C1D8F0
  4663AC
  D2DEEB
*/
export const rootColor = css`
  :root {
    --red: #f44336;
    --light-red: #ffcfc9;
    --dark-red: #ab3128;
    --yellow: #f4de36;
    --light-yellow: #ffe59e;
    --green: #4caf50;
    --light-green: #d5edbb;
    --dark-green: #3a833d;
    --bule-green: #579590;
    --deep-blue-green: #3e736f;
    --blue: #4663ac;
    --light-blue: #6b93f9;
    --dark-brown: #784d4d;
    --skyblue: #8abae0;
    --dark-skyblue: #6c9fc8;
    --brown: #956666;
    --light-brown: #806d48;
    --white: #fff;
    --black: #212121;
    --dark-grey: #505050;
    --grey: #f0efef;
    --light-grey: #e6e6e6;
    --orange: #ffa500;
    --main-background: #fff;
    --menu-hover: #efefef;
  }
`
export const colors = {
  red: 'var(--red)',
  lightRed: 'var(--light-red)',
  darkRed: 'var(--dark-red)',
  yellow: 'var(--yellow)',
  lightYellow: 'var(--light-yellow)',
  green: 'var(--green)',
  darkGreen: 'var(--dark-green)',
  lightGreen: 'var(--light-green)',
  blueGrren: `var(--bule-green)`,
  deepBlueGreen: `var(--deep-blue-green)`,
  blue: 'var(--blue)',
  lightBlue: 'var(--light-blue)',
  skyBlue: 'var(--skyblue)',
  darkSkyBlue: 'var(--dark-skyblue)',
  brown: 'var(--brown)',
  darkBrown: 'var(--dark-brown)',
  lightBrown: 'var(--light-brown)',
  white: 'var(--white)',
  black: 'var(--black)',
  darkGrey: 'var(--dark-grey)',
  grey: 'var(--grey)',
  lightGrey: 'var(--light-grey)',
  orange: 'var(--orange)',
  mainBackground: 'var(--main-background)',
  menuHover: `var(--menu-hover)`,
}

export type Colors = keyof typeof colors

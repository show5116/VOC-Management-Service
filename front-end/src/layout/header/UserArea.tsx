import { css } from '@emotion/react'
import { Avatar } from '@mui/material'
import React from 'react'

const userAreaCss = css`
  display: flex;
  //position: fixed;
  //right: 20px;
  > div {
    margin: auto;
  }
  .user-name {
    text-align: center;
  }
`

const UserArea = () => {
  return (
    <div css={userAreaCss}>
      <Avatar alt='user' src='' style={{ marginRight: 10 }} />
      <div className='user-name'>
        <p style={{ marginBottom: 5 }}>{`sm_eunhee.park`}</p>
        <p>{`(박은희)`}</p>
        {/* <p>{`sm_eunhee.park (박은희)`}</p> */}
      </div>
    </div>
  )
}

export default UserArea

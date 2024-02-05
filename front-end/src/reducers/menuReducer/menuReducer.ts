import { createSlice } from '@reduxjs/toolkit'

export interface MenuProps {
  menuId: string
  displayDepth: number | undefined
  path: string | undefined
  displayMenuName: string
  displayNameKor: string | undefined
  displayNameEng: string | undefined
  displaySequence: string | undefined
  hasChild: string | undefined
  menuRootKey: string | undefined
  menuParentKey: string | undefined
  childMenu: MenuProps[]
}

interface MenuState {
  isActive: boolean
  menuList: MenuProps[]
}

const initialState: MenuState = {
  isActive: false,
  menuList: [],
}

const menuSlice = createSlice({
  name: 'menu',
  initialState: initialState,
  reducers: {
    activeMenu: (state, action) => {
      state.isActive = action.payload
    },
    setMeunList: (state, action) => {
      state.menuList = action.payload
    },
  },
})

export const { activeMenu, setMeunList } = menuSlice.actions

export default menuSlice.reducer

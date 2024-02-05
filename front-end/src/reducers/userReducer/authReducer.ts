import { createSlice } from '@reduxjs/toolkit'

export interface UserInfo {
  plant: string | undefined
  userId: string | undefined
  userName: string | undefined
  languageType: string | undefined
  department: string | undefined
  departmentName: string | undefined
  departmentAs: string | undefined
  email: string | undefined
  phoneNumber: string | undefined
  roleId: string | undefined
  subcontractorCode: string | undefined
  superUser: boolean | undefined
}

interface authState {
  userInfo: UserInfo
  authToken: string
}

const initialState: authState = {
  userInfo: {
    plant: undefined,
    userId: undefined,
    userName: undefined,
    languageType: undefined,
    department: undefined,
    departmentName: undefined,
    departmentAs: undefined,
    email: undefined,
    phoneNumber: undefined,
    roleId: undefined,
    subcontractorCode: undefined,
    superUser: undefined,
  },
  authToken: '',
}

const authSlice = createSlice({
  name: 'auth',
  initialState: initialState,
  reducers: {
    setUserInfo: (state, action) => {
      state.userInfo = action.payload
    },
    setAuthToken: (state, action) => {
      state.authToken = action.payload
    },
    setLogout: (state) => {
      state.userInfo = initialState.userInfo
      state.authToken = initialState.authToken
    },
  },
})

export const { setUserInfo, setAuthToken, setLogout } = authSlice.actions
export default authSlice.reducer

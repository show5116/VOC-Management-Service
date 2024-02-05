import { createSlice } from '@reduxjs/toolkit'

interface AlertState {
  message: string
  isOpen: boolean
  type: 'error' | 'info' | 'success' | 'warning'
}

const initialState: AlertState = {
  message: '',
  isOpen: false,
  type: 'error',
}

const alertSlice = createSlice({
  name: 'alert',
  initialState: initialState,
  reducers: {
    showAlert: (state, action) => {
      state.isOpen = action.payload.isOpen
      state.message = action.payload.message
      state.type = action.payload.type ? action.payload.type : 'error'
    },
  },
})

export const { showAlert } = alertSlice.actions

export default alertSlice.reducer

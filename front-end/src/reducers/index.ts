import { combineReducers } from 'redux'
import { PersistConfig, persistReducer } from 'redux-persist'
import storage from 'redux-persist/lib/storage'
import { logger } from 'redux-logger'
import { useDispatch } from 'react-redux'
import { configureStore } from '@reduxjs/toolkit'
import persistStore from 'redux-persist/es/persistStore'
import authReducer from './userReducer/authReducer'
import menuReducer from './menuReducer/menuReducer'
import alertReducer from './alertReducer/alertReducer'

const rootReducer = combineReducers({
  auth: authReducer,
  menu: menuReducer,
  alert: alertReducer,
})

const persistConfig: PersistConfig<any> = {
  key: 'root',
  storage: storage,
  blacklist: ['tabMenu', 'alert'],
}

const myPersistReducer = persistReducer(persistConfig, rootReducer)

export const store = configureStore({
  reducer: myPersistReducer,
  //middleware: [],
  middleware: [logger] as const,
})

export const persistor = persistStore(store)

export type RootState = ReturnType<typeof rootReducer>
export type AppDispatch = typeof store.dispatch

export const useAppDispatch: () => AppDispatch = useDispatch

export default store

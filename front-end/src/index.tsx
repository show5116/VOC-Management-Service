import React from 'react'
import App from './App'
import ReactDOM from 'react-dom/client'
import reportWebVitals from './reportWebVitals'
import { BrowserRouter } from 'react-router-dom'
import { PersistGate } from 'redux-persist/integration/react'
import { Global } from '@emotion/react'
import { Provider } from 'react-redux'
import '@locales'
import { persistor, store } from '@reducers/index'
import globalStyles from './components/styles/globalStyles'
const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement)

root.render(
  // <React.StrictMode>
  <>
    <Global styles={globalStyles} />
    <Provider store={store}>
      <PersistGate persistor={persistor}>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </PersistGate>
    </Provider>
  </>,

  // </React.StrictMode>,
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()

import { Suspense, lazy } from 'react'
import { Navigate, Outlet, Route, Routes } from 'react-router-dom'
import { ErrorBoundary } from 'react-error-boundary'
import DashBoard from '@pages/DashBoard'
import ErrorBoundaryComponent from '@components/errors/ErrorBoundaryComponent'
import Layout from '@layout'
import { menus } from '@/moc/menu'
import { menu } from '@/layout/header/MenuList'
import 'ag-grid-enterprise'
import './App.scss'

const getAllMenus = (menus: menu[]) => {
  const menuList: menu[] = []
  menus.forEach((menu) => {
    menuList.push(menu)
    if (menu.child) {
      menuList.push(...getAllMenus(menu.child))
    }
  })
  return menuList
}

function App() {
  const allMenus: menu[] = []
  menus.forEach((menu) => {
    allMenus.push(menu)
    if (menu.child) {
      allMenus.push(...getAllMenus(menu.child))
    }
  })
  //console.log(allMenus)
  const getComponent = () => {
    const getRending = (componentPath: string) => {
      return componentPath
        ? lazy(() => import(`./pages/${componentPath}`))
        : undefined
    }
    return (
      <Route
        element={
          <div>
            <Layout children={<Outlet />} />
          </div>
        }
      >
        <Route path='/' Component={DashBoard} />
        {allMenus.map((menu) => (
          <Route
            key={menu.menuName}
            path={menu.menuPath}
            Component={getRending(menu.componentPath)}
          />
        ))}

        <Route path={'*'} element={<Navigate replace to={'/'} />} />
      </Route>
    )
  }

  return (
    <ErrorBoundary
      FallbackComponent={ErrorBoundaryComponent}
      onReset={() => {}}
    >
      <Suspense>
        <Routes>{getComponent()}</Routes>
      </Suspense>
    </ErrorBoundary>
  )
}

export default App

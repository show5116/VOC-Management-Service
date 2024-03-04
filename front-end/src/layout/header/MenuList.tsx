import { Link } from 'react-router-dom'
import { menus } from '@/moc/menu'
import { subjectFont } from '@/components/styles/shareStyle'
import { css } from '@emotion/react'
import { Dispatch, SetStateAction } from 'react'
import { colors } from '@/components/styles/colors'
import { layoutWidths } from '@/components/styles/layoutStyles'

export interface menu {
  menuName: string
  menuDepth: number
  menuPath: string
  componentPath: string
  menuKey: number
  parentKey: number
  child?: menu[]
}

const navCss = css`
  @keyframes fadeIn {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }

  display: flex;
  justify-content: flex-start;
  .fade-in {
    opacity: 1;
    animation: fadeIn ease-in-out 0.2s;
  }
  > .subject :last-child {
    border-right: 0px;
  }

  font-family: Play;
  width: 100%;
`
const menuCss = css`
  width: 200px;
  text-align: center;
  overflow: hidden;
`

const depthMenuCss = css`
  background-color: ${colors.white};
  position: fixed;
  left: 0px;
  top: 72px;
  width: 100%;
  height: 50%;
  padding: 10px 0px;
  z-index: 11;
  box-shadow: 0px 6px 20px 0px ${colors.deepBlueGreen};
  overflow: auto;
  border-collapse: collapse;

  ul {
    width: 198px;
    text-align: center;
    border-left: 1px solid ${colors.lightGrey};
    border-right: 1px solid ${colors.lightGrey};
    li {
      :hover {
        background-color: ${colors.deepBlueGreen};
        color: ${colors.menuHover};
      }
    }
  }
`
const subSubjectCss = css`
  line-height: 1.4;
  letter-spacing: -0.04em;
  padding: 0.52em;
  font-family: Play;
`
const MenuList = ({
  isActive,
  setIsActive,
}: {
  isActive: boolean
  setIsActive: Dispatch<SetStateAction<boolean>>
}) => {
  const getSubDepthMenu = (menu: menu) => {
    return (
      <>
        {menu.child?.map((depthMenu) => (
          <li key={depthMenu.menuKey} css={subSubjectCss}>
            {depthMenu.child ? (
              <>
                <span>{depthMenu.menuName}</span>
                {/* <ul>{getSubDepthMenu(depthMenu)}</ul> */}
              </>
            ) : (
              <Link to={depthMenu.menuPath} onClick={onClickMenu}>
                {depthMenu.menuName}
              </Link>
            )}
          </li>
        ))}
      </>
    )
  }

  const onClickMenu = () => setIsActive(false)

  return (
    <nav css={navCss}>
      {menus.map((menu) => (
        <div key={menu.menuKey} css={menuCss}>
          <div
            className={'subject'}
            css={subjectFont}
            title={menu.menuName}
            //onMouseEnter={() => setIsActive(true)}
          >
            {menu.child ? (
              <span>{menu.menuName}</span>
            ) : (
              <span>
                <Link to={menu.menuPath} onClick={onClickMenu}>
                  {menu.menuName}
                </Link>
              </span>
            )}
          </div>
        </div>
      ))}
      {isActive && (
        <div className={'fade-in'} css={depthMenuCss}>
          <div
            style={{
              //marginLeft: `${layoutWidths.left}`,
              width: '100%',
              display: 'flex',
            }}
          >
            <div style={{ width: `${layoutWidths.left}`, padding: 15 }}>
              &nbsp;
            </div>
            <div
              style={{
                //marginLeft: `${layoutWidths.left}`,
                padding: '0px 15px',
                display: 'flex',
                width: '100%',
                justifyContent: 'flex-start',
              }}
            >
              {menus.map((menu) => (
                <ul key={menu.menuKey}>{getSubDepthMenu(menu)}</ul>
              ))}
            </div>
          </div>
        </div>
      )}
    </nav>
  )
}

export default MenuList

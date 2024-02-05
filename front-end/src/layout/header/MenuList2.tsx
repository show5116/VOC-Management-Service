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

  .fade-in {
    opacity: 1;
    animation: fadeIn ease-in-out 0.2s;
  }
  > .subject :last-child {
    border-right: 0px;
  }

  font-family: Play;
`
const menuCss = css`
  width: 150px;
  border-right: 1px solid ${colors.lightGrey};
`

const depthMenuCss = css`
  background-color: ${colors.white};
  position: fixed;
  left: 0px;
  top: 73px;
  width: 100%;
  padding: 10px 0px 10px 15px;
  z-index: 11;
  box-shadow: 0px 6px 20px 0px #4663accf;
`
const subSubjectCss = css`
  line-height: 1.4;
  letter-spacing: -0.04em;
  padding: 0.52em 1em;

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
            {!depthMenu.child && (
              <Link to={depthMenu.menuPath}>{depthMenu.menuName}</Link>
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
            onClick={onClickMenu}
            title={menu.menuName}
            onMouseEnter={() => setIsActive(true)}
          >
            {menu.child ? (
              <span>{menu.menuName}</span>
            ) : (
              <span>
                <Link to={menu.menuPath}>{menu.menuName}</Link>
              </span>
            )}
          </div>
        </div>
      ))}
      {isActive && (
        <div
          className={'fade-in'}
          css={depthMenuCss}
          onMouseLeave={() => {
            setIsActive(false)
          }}
        >
          <div
            style={{
              position: 'relative',
              left: `${layoutWidths.left}`,
              display: 'flex',
            }}
          >
            {menus.map((menu) => (
              <div
                key={menu.menuKey}
                style={{
                  borderRight: `1px solid ${colors.lightGrey}`,
                  borderBottom: `2px solid ${colors.blue}`,
                }}
              >
                <ul
                  style={{
                    width: 150,
                    textAlign: 'center',
                  }}
                >
                  {getSubDepthMenu(menu)}
                </ul>
              </div>
            ))}
          </div>
          <div
            style={{
              position: 'relative',
              left: `${layoutWidths.left}`,
              display: 'flex',
              marginTop: 10,
            }}
          >
            <ul style={{ width: 150 }}>
              <li>1</li>
              <li>2</li>
              <li>3</li>
            </ul>
          </div>
        </div>
      )}
    </nav>
  )
}

export default MenuList

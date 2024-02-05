import { colors } from '@/components/styles/colors'
import { layoutWidths } from '@/components/styles/layoutStyles'
import { contentRadius } from '@/components/styles/shareStyle'
import { css } from '@emotion/react'
import { HiOutlineDesktopComputer } from 'react-icons/hi'
import { Link } from 'react-router-dom'
//import SubjectTable from './SubjectTable'
import { RiBillLine, RiDraftLine, RiMenuFill } from 'react-icons/ri'
import {
  MdApproval,
  MdAssignmentAdd,
  MdCalendarMonth,
  MdConstruction,
} from 'react-icons/md'
import { useState } from 'react'
import { Avatar, Badge } from '@mui/material'

const workTabelContainerCss = css`
  //width: ${layoutWidths.left};
  padding: 15px;
  line-height: 1.4;
  letter-spacing: -0.04em;
  font-family: Play;
  color: ${colors.white};
`
const workTableWrapCss = css`
  background-color: ${colors.blueGrren};
  padding: 20px 20px;
  border-top-left-radius: ${contentRadius};
  border-top-right-radius: ${contentRadius};
  border-bottom-left-radius: ${contentRadius};
  border-bottom-right-radius: ${contentRadius};
  height: calc(100% - 40px);
  // box-shadow: 0px 0px 20px 0px #dacfcfcf;
  overflow: auto;
  font-size: 17px;
  li {
    margin: 0px 0px 10px 0px;
  }
`
const lineCss = css`
  border-bottom: 2px solid ${colors.deepBlueGreen};
  margin: 20px 0;
`

const workTabelSubjectCss = css`
  font-size: 19px;
  font-weight: 700;
  margin-bottom: 15px;
`

const workTabelDepthSubjectCss = css`
  display: flex;
  align-items: center;
  font-size: 16px;
  width: 100%;
  :hover {
    background-color: ${colors.menuHover};
    color: ${colors.deepBlueGreen};
  }

  .work-tabel-sub-title {
    padding-left: 10px;
  }
`
const center = css`
  justify-content: center;
`
const icon = css`
  font-size: 24px;
`

const userAreaCss = css`
  display: flex;
  //position: fixed;
  //right: 20px;

  > div {
    //margin: auto;
  }
  .user-name {
    text-align: center;
  }

  svg {
    font-size: 24px;
  }
`
//const workTableSubjectArr: string[] = ['REMINDERS', 'SETTINGS']

const WorkTabel = () => {
  const [isOpen, setIsOpen] = useState<boolean>(true)
  const onClickMenuIcon = () => setIsOpen(!isOpen)

  return (
    <div
      css={workTabelContainerCss}
      style={isOpen ? { width: `${layoutWidths.left}` } : {}}
    >
      <div css={workTableWrapCss}>
        <div css={userAreaCss}>
          <div style={isOpen ? {} : { marginLeft: 5 }}>
            <Avatar alt='user' src='' sx={{ width: 30, height: 30 }} />
          </div>
          {isOpen ? (
            <div className='user-name' style={{ marginLeft: 10 }}>
              <p
                style={{ marginBottom: 5, fontSize: 20 }}
              >{`sm_eunhee.park`}</p>
              {/* <p>{`(박은희)`}</p> */}
              {/* <p>{`sm_eunhee.park (박은희)`}</p> */}
            </div>
          ) : (
            <>&nbsp;</>
          )}
        </div>
        <div css={lineCss} />
        <p
          css={isOpen ? [workTabelSubjectCss] : [workTabelSubjectCss, center]}
          style={{
            display: 'flex',
            alignItems: 'center',
            marginBottom: 15,
          }}
        >
          <RiMenuFill
            title={'menu toggle'}
            css={icon}
            style={{ cursor: 'pointer' }}
            onClick={onClickMenuIcon}
          />
          {isOpen && <span style={{ paddingLeft: 10 }}>{'HOME'}</span>}
        </p>
        <ul>
          <li>
            <Link
              to={'/'}
              css={
                isOpen
                  ? workTabelDepthSubjectCss
                  : [workTabelDepthSubjectCss, center]
              }
            >
              <HiOutlineDesktopComputer css={icon} title={'Dashboard'} />
              {isOpen && (
                <span className={'work-tabel-sub-title'}>{'Dashboard'}</span>
              )}
            </Link>
          </li>
        </ul>
        <div css={lineCss} />
        <p css={workTabelSubjectCss}>
          <span>{isOpen ? 'REMINDERS' : <>&nbsp;</>}</span>
        </p>
        <ul>
          <li>
            <Link
              to={'/draft'}
              css={
                isOpen
                  ? workTabelDepthSubjectCss
                  : [workTabelDepthSubjectCss, center]
              }
            >
              <Badge
                color='error'
                badgeContent={7771}
                max={99}
                componentsProps={
                  isOpen
                    ? {
                        badge: {
                          style: {
                            position: 'relative',
                            top: 11.5,
                            right: -5,
                          },
                        },
                      }
                    : {}
                }
              >
                <RiDraftLine css={icon} title={'Draft'} />
                {isOpen && (
                  <span className={'work-tabel-sub-title'}>{`Draft`}</span>
                )}
              </Badge>
            </Link>
          </li>
          <li>
            <Link
              to={'/aprroval'}
              css={
                isOpen
                  ? workTabelDepthSubjectCss
                  : [workTabelDepthSubjectCss, center]
              }
            >
              <Badge
                color='error'
                badgeContent={3}
                max={99}
                componentsProps={
                  isOpen
                    ? {
                        badge: {
                          style: {
                            position: 'relative',
                            top: 11.5,
                            right: -5,
                          },
                        },
                      }
                    : {}
                }
              >
                <MdApproval css={icon} title={'Apploval'} />
                {isOpen && (
                  <span className={'work-tabel-sub-title'}>{'Apploval'}</span>
                )}
              </Badge>
            </Link>
          </li>
          <li>
            <Link
              to={'/share'}
              css={
                isOpen
                  ? workTabelDepthSubjectCss
                  : [workTabelDepthSubjectCss, center]
              }
            >
              <RiBillLine css={icon} title={'Share'} />
              {isOpen && (
                <span className={'work-tabel-sub-title'}>{'Share'}</span>
              )}
            </Link>
          </li>
        </ul>
        <div css={lineCss} />
        <p css={workTabelSubjectCss}>
          <span>{isOpen ? 'SETTINGS' : <>&nbsp;</>}</span>
        </p>
        <ul>
          <li>
            <Link
              css={
                isOpen
                  ? workTabelDepthSubjectCss
                  : [workTabelDepthSubjectCss, center]
              }
              to={'/setting/1'}
            >
              <MdAssignmentAdd css={icon} title={'setting1'} />
              {isOpen && (
                <span className={'work-tabel-sub-title'}>{`setting1`}</span>
              )}
            </Link>
          </li>
          <li>
            <Link
              css={
                isOpen
                  ? workTabelDepthSubjectCss
                  : [workTabelDepthSubjectCss, center]
              }
              to={'/setting/2'}
            >
              <MdCalendarMonth css={icon} title={'setting2'} />
              {isOpen && (
                <span className={'work-tabel-sub-title'}>{'setting2'}</span>
              )}
            </Link>
          </li>
          <li>
            <Link
              css={
                isOpen
                  ? workTabelDepthSubjectCss
                  : [workTabelDepthSubjectCss, center]
              }
              to={'/setting/3'}
            >
              <MdConstruction css={icon} title={'setting3'} />
              {isOpen && (
                <span className={'work-tabel-sub-title'}>{'setting3'}</span>
              )}
            </Link>
          </li>
        </ul>
        {/* {workTableSubjectArr.map((suject) => (
          <>
            <div css={lineCss} />
            <p css={workTabelSubjectCss}>
              <span>{suject}</span>
            </p>
            <SubjectTable title={suject} />
          </>
        ))} */}
      </div>
    </div>
  )
}

export default WorkTabel

import { menu } from '@/layout/header/MenuList'

export const menus: menu[] = [
  {
    menuDepth: 1,
    menuName: 'VocManage',
    menuPath: '/',
    componentPath: '/Voc',
    menuKey: 1,
    parentKey: 0,
    child: [
      {
        menuDepth: 2,
        menuName: 'DashBoard',
        menuPath: 'dashboard',
        componentPath: 'moc/Test1',
        menuKey: 1001,
        parentKey: 2,
      },
      {
        menuDepth: 2,
        menuName: 'VOC 관리',
        menuPath: 'voc',
        componentPath: 'moc/Voc',
        menuKey: 1002,
        parentKey: 2,
      },
      {
        menuDepth: 3,
        menuName: '서비스 관리',
        menuPath: 'voc',
        componentPath: 'moc/Voc',
        menuKey: 1002,
        parentKey: 2,
      },
    ],
  },
  {
    menuDepth: 1,
    menuName: 'VocReport',
    menuPath: '/',
    componentPath: '/Voc',
    menuKey: 1,
    parentKey: 0,
    child: [
      {
        menuDepth: 2,
        menuName: '요청자별 Report',
        menuPath: 'dashboard',
        componentPath: 'moc/Test1',
        menuKey: 1001,
        parentKey: 2,
      },
      {
        menuDepth: 2,
        menuName: '담당자별 Report',
        menuPath: 'voc',
        componentPath: 'moc/Voc',
        menuKey: 1002,
        parentKey: 2,
      },
    ],
  },
]

import Aggrid from '@/components/aggrid/Aggrid'
import { ColDef } from 'ag-grid-community'

import GridLabel, { label, lableState } from '@components/aggrid/GridLabel'
import IbsTextField from '@components/common/IbsTextField'
import IbsTypeButton from '@components/common/IbsTypeButton'
import RequestKindRenderer, {
  requestKindArray,
} from '@components/voc/RequestKindRenderer'
import IbsButton from '@components/common/IbsButton'
import { colors } from '@components/styles/colors'

const rowData: any[] = [
  {
    requestKind: 'new',
    service: 'oms',
    menu: 'lot 관리',
    vocNumber: '',
    content: 'a',
    requestUser: '2015-12-22',
    requestDate: '9',
    attachment: 99,
    manager: 'inProgress',
    progress: '',
    expectedCompletionDate: '',
    completionDate: '',
  },
  {
    requestKind: 'new',
    service: 'oms',
    menu: 'lot 관리',
    vocNumber: '',
    content: 'a',
    requestUser: '2015-12-22',
    requestDate: '9',
    attachment: 99,
    manager: 'inProgress',
    progress: '',
    expectedCompletionDate: '',
    completionDate: '',
    updateUser: '',
    updateDate: '',
  },
]

const columns: ColDef[] = [
  {
    headerName: '요청 종류',
    field: 'requestKind',
    filter: 'agTextColumnFilter',
    cellRenderer: RequestKindRenderer,
    cellEditor: 'agRichSelectCellEditor',
    editable: true,
    cellEditorParams: {
      values: requestKindArray,
      cellRenderer: RequestKindRenderer,
      cellEditorPopup: true,
    },
    floatingFilter: true,
  },
  {
    headerName: 'Service',
    field: 'service',
    filter: 'agTextColumnFilter',
    floatingFilter: true,
  },
  {
    headerName: '메뉴',
    field: 'menu',
  },
  {
    headerName: '처리 번호',
    field: 'vocNumber',
  },
  {
    headerName: '내용',
    field: 'content',
  },
  {
    headerName: '요청자',
    field: 'requestUser',
  },
  {
    headerName: '요청일',
    field: 'requestDate',
  },
  {
    headerName: '중요도',
    field: 'requestDate',
  },
  {
    headerName: '첨부파일',
    field: 'attachment',
  },
  {
    headerName: '담당자',
    field: 'manager',
  },
  {
    headerName: '진행 상황',
    field: 'progress',
    filter: true,
    floatingFilter: true,
  },
  {
    headerName: '완료 예정일',
    field: 'expectedCompletionDate',
  },
  {
    headerName: '완료일',
    field: 'completionDate',
  },
  {
    headerName: '수정자',
    field: 'updateUser',
  },
  {
    headerName: '수정일',
    field: 'updateDate',
  },
]

const Voc = () => {
  return (
    <div style={{ height: '100%' }}>
      <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
        <IbsTextField label='검색어' />
        <IbsTypeButton
          buttontype={'search'}
          formControllStyle={{ marginLeft: 10 }}
        />
        <IbsButton
          formControllStyle={{
            marginLeft: 10,
            color: `${colors.blue}`,
          }}
          width={70}
        >
          행 추가
        </IbsButton>
        <IbsButton
          formControllStyle={{
            marginLeft: 10,
            color: `${colors.blue}`,
          }}
          width={70}
        >
          저장
        </IbsButton>
      </div>
      <Aggrid
        columns={columns}
        defaultRowData={rowData}
        gridHeight={'90%'}
        gridOptions={{}}
      />
    </div>
  )
}

export default Voc

import { useState } from 'react'

import Aggrid from '@components/aggrid/Aggrid'
import { ColDef } from 'ag-grid-community'

import IbsTextField from '@components/common/IbsTextField'
import IbsTypeButton from '@components/common/IbsTypeButton'
import IbsButton from '@components/common/IbsButton'
import IbsModal from '@components/common/IbsModal'

import RequestKindRenderer, {
  requestKindArray,
} from '@components/voc/RequestKindRenderer'
import VocRegistration from '@components/voc/VocRegistration'

const rowData: any[] = [
  {
    requestKind: undefined,
    service: 'SILICONMITUS',
    menu: 'Lot Status',
    vocNumber: 'V0000001',
    content: 'Lot Status 신규 페이지 요청',
    requestUser: '김철수',
    requestDate: new Date('2024-02-06'),
    deliveryRequestDate: new Date('2024-04-01'),
    attachment: 'aa.png',
    manager: '육이슬',
    progress: '',
    expectedCompletionDate: '',
    completionDate: '',
  },
  {
    requestKind: 'error',
    service: 'OMS',
    menu: 'Lot History',
    vocNumber: 'V0000002',
    content: 'Lot 이력 검색 시 에러 발생',
    requestUser: '김민수',
    requestDate: new Date('2024-02-06'),
    deliveryRequestDate: new Date('2024-02-12'),
    attachment: 'bb.exl',
    manager: '유영진',
    progress: '',
    expectedCompletionDate: '',
    completionDate: '',
    updateUser: '',
    updateDate: '',
  },
]

const dateValueFormatter = (params: any) => {
  if (!params.value) {
    return ''
  }
  const month = params.value.getMonth() + 1
  const day = params.value.getDate()
  return `${params.value.getFullYear()}-${month < 10 ? '0' + month : month}-${
    day < 10 ? '0' + day : day
  }`
}

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
    cellEditor: 'agDateCellEditor',
    editable: true,
    valueFormatter: dateValueFormatter,
  },
  {
    headerName: '납기 요청일',
    field: 'deliveryRequestDate',
    cellEditor: 'agDateCellEditor',
    valueFormatter: dateValueFormatter,
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
    cellEditor: 'agDateCellEditor',
    valueFormatter: dateValueFormatter,
  },
  {
    headerName: '완료일',
    field: 'completionDate',
    cellEditor: 'agDateCellEditor',
    valueFormatter: dateValueFormatter,
  },
  {
    headerName: '수정자',
    field: 'updateUser',
  },
  {
    headerName: '수정일',
    field: 'updateDate',
    cellEditor: 'agDateCellEditor',
    valueFormatter: dateValueFormatter,
  },
]

const Voc = () => {
  const [isOpenRegistration, setIsRegistration] = useState(false)

  return (
    <div style={{ height: '100%' }}>
      <IbsModal
        open={isOpenRegistration}
        setOpen={setIsRegistration}
        width='1000px'
        height='500px'
        title='VoC 등록'
        button={
          <IbsButton formControllStyle={{ marginRight: 10 }} width={70}>
            등록
          </IbsButton>
        }
      >
        <VocRegistration />
      </IbsModal>
      <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
        <IbsTextField label='검색어' />
        <IbsTypeButton
          buttontype={'search'}
          formControllStyle={{ marginLeft: 10 }}
        />
        <IbsButton
          formControllStyle={{
            marginLeft: 10,
          }}
          width={70}
          onClick={() => setIsRegistration(true)}
        >
          등록
        </IbsButton>
        <IbsButton
          formControllStyle={{
            marginLeft: 10,
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

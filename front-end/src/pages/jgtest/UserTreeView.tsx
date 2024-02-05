import { TreeItem, TreeView } from '@mui/x-tree-view'
import { Button, Checkbox, FormControl, Typography } from '@mui/material'
import axios from 'axios'
import { forwardRef, useEffect, useImperativeHandle, useState } from 'react'
import { FiChevronRight, FiChevronDown } from 'react-icons/fi'
import IbsCombobox from '@/components/common/IbsCombobox'
import IbsTextField from '@/components/common/IbsTextField'
import { defaultFormControllStyle } from '@/components/styles/shareStyle'
interface UserTreeViewProps {
  handleNodeSelect?: (event: React.ChangeEvent<{}>, nodeId: string) => void
}

interface TreeNode {
  deptId: string
  deptName: string
  child?: TreeNode[]
}
interface UserTreeViewProps {
  handleNodeSelect?: (event: React.ChangeEvent<{}>, nodeId: string) => void
}

const UserTreeView = forwardRef((props: UserTreeViewProps, ref: any) => {
  useImperativeHandle(ref, () => ({
    getUserDepartment,
    resetUserDepartment,
  }))

  const [userData, setUserData] = useState<any[]>([])
  const [expandedNodes, setExpandedNodes] = useState<string[]>([])
  const [checked, setChecked] = useState<string[]>([])

  const resetUserDepartment = () => {
    setUserData([])
  }
  const getUserDepartment = () => {
    const success = (response: any) => {
      setUserData(response.data)
    }
    const fail = (response: any) => {}
    return axios
      .post('http://localhost:8081/api/menu/find/user-department')
      .then(success)
      .catch(fail)
  }

  const getAllNodeIds = (nodes: any): string[] => {
    let ids = [nodes.deptId]
    if (Array.isArray(nodes.child)) {
      for (let node of nodes.child) {
        ids = ids.concat(getAllNodeIds(node))
      }
    }
    return ids
  }

  useEffect(() => {
    getUserDepartment()
  }, [])
  useEffect(() => {
    if (userData) {
      setExpandedNodes(getAllNodeIds(userData))
    }
  }, [userData])
  const handleCheck = (
    event: React.ChangeEvent<HTMLInputElement>,
    nodeId: string,
  ) => {
    event.stopPropagation() // 이벤트 전파 방지

    setChecked((prevChecked) => {
      const newChecked = [...prevChecked]
      const currentIndex = newChecked.indexOf(nodeId)

      if (currentIndex === -1) {
        newChecked.push(nodeId)
        // 모든 하위 노드 추가
        addRemoveChildNodes(nodeId, newChecked, true)
      } else {
        newChecked.splice(currentIndex, 1)
        // 모든 하위 노드 제거
        addRemoveChildNodes(nodeId, newChecked, false)
      }

      return newChecked
    })
  }

  const addRemoveChildNodes = (
    nodeId: string,
    checkList: string[],
    add: boolean,
  ) => {
    const node = findNode(userData, nodeId)
    if (node && node.child) {
      node.child.forEach((childNode) => {
        const childIndex = checkList.indexOf(childNode.deptId)
        if (add && childIndex === -1) {
          checkList.push(childNode.deptId)
        } else if (!add && childIndex !== -1) {
          checkList.splice(childIndex, 1)
        }
        addRemoveChildNodes(childNode.deptId, checkList, add) // 재귀 호출
      })
    }
  }

  const findNode = (nodes: TreeNode[], id: string): TreeNode | null => {
    let result = null
    for (let node of nodes) {
      if (node.deptId === id) {
        return node
      } else if (node.child) {
        result = findNode(node.child, id)
        if (result) return result
      }
    }
    return result
  }

  const renderTree = (nodes: TreeNode) => (
    <TreeItem
      key={nodes.deptId}
      nodeId={nodes.deptId}
      label={
        <>
          <Checkbox
            edge='start'
            checked={checked.includes(nodes.deptId)}
            onChange={(e) => handleCheck(e, nodes.deptId)}
            onClick={(e) => e.stopPropagation()}
          />
          {nodes.deptName}
        </>
      }
    >
      {nodes.child && nodes.child.map((node) => renderTree(node))}
    </TreeItem>
  )

  return (
    <>
      <div style={{ marginBottom: 10 }}>
        <div style={{ height: '100%' }}>
          <div className='title-container' style={{ paddingLeft: 0 }}>
            <div className='line'></div>
            <Typography variant='h6'>Department</Typography>
          </div>
        </div>
      </div>
      <IbsCombobox
        formControllStyle={{ marginTop: 0, marginLeft: 0 }}
        size='small'
        width={150}
        defaultItems={[{ displayValue: '', value: '' }]}
      />
      <IbsTextField
        formControllStyle={{ width: 210, marginTop: 0, marginLeft: 0 }}
      />
      <FormControl style={{ width: 120, marginTop: 7 }}>
        <Button variant='contained'>{'search'}</Button>
      </FormControl>
      <div
        style={{
          overflow: 'auto',
          height: 'calc(100vh - 270px)',
          border: '1px solid lightgray',
          borderRadius: '5px',
          padding: '10px',
        }}
      >
        <TreeView
          defaultCollapseIcon={<FiChevronDown />}
          defaultExpandIcon={<FiChevronRight />}
          onNodeSelect={props.handleNodeSelect}
          expanded={expandedNodes}
          onNodeToggle={(event: any, nodes: any) => setExpandedNodes(nodes)}
        >
          {userData?.map((item: any) => renderTree(item))}
        </TreeView>
      </div>
    </>
  )
})

export default UserTreeView

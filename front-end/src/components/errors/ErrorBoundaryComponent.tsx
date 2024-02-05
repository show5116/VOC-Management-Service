import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from '@mui/material'
import { useEffect, useState } from 'react'
import { FallbackProps } from 'react-error-boundary'
import { useNavigate } from 'react-router-dom'
//import { lang } from 'utils/langUtil'

const ErrorBoundaryComponent = ({
  error,
  resetErrorBoundary,
}: FallbackProps) => {
  const [isOpen, toggleView] = useState(true)
  const handleClose = () => toggleView(!isOpen)
  const navigate = useNavigate()
  useEffect(() => {
    navigate(-1)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])
  return (
    <>
      <div>error발생</div>
      <Dialog open={isOpen} fullWidth={true} maxWidth={'sm'}>
        <DialogTitle>Page Loading Error</DialogTitle>
        <DialogContent>
          {/* {error.message} */}
          {/* <div>{lang('errorCommon')}</div> */}
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => {
              handleClose()
              resetErrorBoundary()
            }}
            variant='contained'
          >
            확인
          </Button>
        </DialogActions>
      </Dialog>
    </>
  )
}

export default ErrorBoundaryComponent

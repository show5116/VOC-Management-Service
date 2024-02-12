import parse from 'html-react-parser'

const LobRenderer = (props: any) => {
  return <div>{parse(props.value)}</div>
}

export default LobRenderer

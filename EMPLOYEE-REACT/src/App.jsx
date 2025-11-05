import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import EmployeeManager from './components/EmployeeManager'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <EmployeeManager></EmployeeManager>
    </>
  )
}

export default App



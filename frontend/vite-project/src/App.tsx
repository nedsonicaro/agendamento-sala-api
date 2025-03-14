import { BrowserRouter, Route, Routes } from 'react-router'

import CalendarioPage from './Page/CalendarioPage'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<CalendarioPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App

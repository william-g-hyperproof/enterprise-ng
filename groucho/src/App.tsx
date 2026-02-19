import { useCallback } from 'react'
import './App.css'

function App() {
  const onClickButton = useCallback(async () => {
    const rootEndpoint = new URL('/', import.meta.env.VITE_API_URL);

    try {
      const response = await fetch(rootEndpoint);
      const text = await response.text();
      alert(text);
    } catch (e: any) {
      alert(e.message)
    }
  }, [])

  return (
    <>
      <h1>Groucho</h1>
      <p>
        Harpo should be running at {import.meta.env.VITE_API_URL}
      </p>
      <button onClick={onClickButton}>
        Hello?
      </button>
    </>
  )
}

export default App

import type { ReactNode } from 'react'
import Header from './Header'

interface AppShellProps {
  children: ReactNode
}

function AppShell({ children }: AppShellProps) {
  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="sidebar-brand">
          <div className="brand-mark">FM</div>
          <div>
            <p className="brand-title">Fraud Monitor</p>
            <p className="brand-subtitle">Phase 1 Frontend</p>
          </div>
        </div>

        <nav className="sidebar-nav">
          <button className="nav-item active">Dashboard</button>
          <button className="nav-item">Transactions</button>
          <button className="nav-item">Alerts</button>
          <button className="nav-item">Rules</button>
          <button className="nav-item">MCP Tools</button>
        </nav>
      </aside>

      <main className="main-content">
        <Header />
        {children}
      </main>
    </div>
  )
}

export default AppShell
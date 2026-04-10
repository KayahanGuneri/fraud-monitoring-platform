import type { ReactNode } from 'react'

interface AppShellProps {
  children: ReactNode
}

function AppShell({ children }: AppShellProps) {
  return <main className="app-shell">{children}</main>
}

export default AppShell
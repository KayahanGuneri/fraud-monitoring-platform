function Header() {
  return (
    <header className="dashboard-header">
      <div>
        <p className="eyebrow">Fraud Monitoring Platform</p>
        <h1>Transaction Monitoring Dashboard</h1>
        <p className="header-subtitle">
          Portfolio-grade fraud analytics shell with mock operational data.
        </p>
      </div>

      <div className="header-actions">
        <div className="header-chip">Environment: Local</div>
        <div className="header-chip">Mode: Mock Data</div>
      </div>
    </header>
  )
}

export default Header
import type { TransactionRow } from '../types/dashboard'

interface DashboardTableProps {
  rows: TransactionRow[]
}

function DashboardTable({ rows }: DashboardTableProps) {
  return (
    <section className="panel table-panel">
      <div className="panel-header">
        <div>
          <p className="panel-eyebrow">Suspicious review queue</p>
          <h2>Transactions requiring attention</h2>
        </div>
      </div>

      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>Transaction</th>
              <th>User</th>
              <th>Amount</th>
              <th>Location</th>
              <th>Risk</th>
              <th>Status</th>
              <th>Time</th>
            </tr>
          </thead>
          <tbody>
            {rows.map((row) => (
              <tr key={row.id}>
                <td>{row.id}</td>
                <td>{row.userId}</td>
                <td>{row.amount}</td>
                <td>{row.location}</td>
                <td>
                  <span className={`risk-badge ${row.riskLevel.toLowerCase()}`}>
                    {row.riskLevel}
                  </span>
                </td>
                <td>
                  <span className={`status-badge ${row.status.toLowerCase()}`}>
                    {row.status}
                  </span>
                </td>
                <td>{row.timestamp}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </section>
  )
}

export default DashboardTable
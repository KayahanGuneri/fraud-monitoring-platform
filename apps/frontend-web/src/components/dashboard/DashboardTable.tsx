import type { Transaction } from '../../types/dashboard'
import SectionCard from '../common/SectionCard'
import RiskBadge from '../common/RiskBadge'
import StatusBadge from '../common/StatusBadge'

interface DashboardTableProps {
  transactions: Transaction[]
}

function DashboardTable({ transactions }: DashboardTableProps) {
  return (
    <SectionCard
      title="Recent Transactions"
      subtitle="Latest processed transactions and fraud outcomes"
    >
      <div className="table-wrapper">
        <table className="dashboard-table">
          <thead>
            <tr>
              <th>Transaction</th>
              <th>User</th>
              <th>Amount</th>
              <th>Status</th>
              <th>Decision</th>
              <th>Risk</th>
              <th>Location</th>
              <th>Time</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((transaction) => (
              <tr key={transaction.id}>
                <td>
                  <div className="table-primary">
                    <strong>{transaction.id}</strong>
                    <span>{transaction.paymentMethod}</span>
                  </div>
                </td>
                <td>
                  <div className="table-primary">
                    <strong>{transaction.userName}</strong>
                    <span>{transaction.userId}</span>
                  </div>
                </td>
                <td>
                  <span className="amount-cell">
                    {transaction.amount.toLocaleString()} {transaction.currency}
                  </span>
                </td>
                <td>
                  <StatusBadge status={transaction.status} />
                </td>
                <td>
                  <span
                    className={`decision-pill decision-pill--${transaction.fraudDecision.toLowerCase()}`}
                  >
                    {transaction.fraudDecision}
                  </span>
                </td>
                <td>
                  <RiskBadge level={transaction.riskLevel} />
                </td>
                <td>
                  {transaction.city}, {transaction.country}
                </td>
                <td>{transaction.createdAt}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </SectionCard>
  )
}

export default DashboardTable
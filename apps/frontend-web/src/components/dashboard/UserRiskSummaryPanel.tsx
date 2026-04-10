import type { UserRiskSummary } from '../../types/dashboard'
import RiskBadge from '../common/RiskBadge'
import SectionCard from '../common/SectionCard'

interface UserRiskSummaryPanelProps {
  users: UserRiskSummary[]
}

function UserRiskSummaryPanel({ users }: UserRiskSummaryPanelProps) {
  return (
    <SectionCard
      title="User Risk Summary"
      subtitle="Users currently requiring closer monitoring"
    >
      <div className="risk-summary-list">
        {users.map((user) => (
          <article key={user.userId} className="risk-summary-item">
            <div className="risk-summary-item__header">
              <div>
                <h3>{user.userName}</h3>
                <span>{user.userId}</span>
              </div>
              <RiskBadge level={user.riskLevel} />
            </div>

            <div className="risk-summary-item__stats">
              <div>
                <label>Suspicious</label>
                <strong>{user.suspiciousTransactions}</strong>
              </div>
              <div>
                <label>Last Activity</label>
                <strong>{user.lastActivity}</strong>
              </div>
            </div>

            <p>{user.flaggedReason}</p>
          </article>
        ))}
      </div>
    </SectionCard>
  )
}

export default UserRiskSummaryPanel
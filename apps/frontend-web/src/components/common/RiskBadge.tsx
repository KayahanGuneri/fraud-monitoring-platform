import type { RiskLevel } from '../../types/dashboard'

interface RiskBadgeProps {
  level: RiskLevel
}

function RiskBadge({ level }: RiskBadgeProps) {
  return <span className={`badge badge--risk badge--${level.toLowerCase()}`}>{level}</span>
}

export default RiskBadge
import type { TransactionStatus } from '../../types/dashboard'

interface StatusBadgeProps {
  status: TransactionStatus
}

function StatusBadge({ status }: StatusBadgeProps) {
  return (
    <span className={`badge badge--status badge--${status.toLowerCase()}`}>
      {status}
    </span>
  )
}

export default StatusBadge
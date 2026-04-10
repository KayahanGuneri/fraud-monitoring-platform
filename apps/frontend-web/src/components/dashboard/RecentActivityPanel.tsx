import type { RecentActivityItem } from '../../types/dashboard'
import RiskBadge from '../common/RiskBadge'
import SectionCard from '../common/SectionCard'

interface RecentActivityPanelProps {
  items: RecentActivityItem[]
}

function RecentActivityPanel({ items }: RecentActivityPanelProps) {
  return (
    <SectionCard
      title="Recent Activity"
      subtitle="Latest monitoring events across the platform"
    >
      <div className="activity-list">
        {items.map((item) => (
          <article key={item.id} className="activity-item">
            <div className="activity-item__top">
              <div className="activity-item__content">
                <span className="activity-item__type">{item.type}</span>
                <h3>{item.title}</h3>
                <p>{item.description}</p>
              </div>
              <RiskBadge level={item.severity} />
            </div>
            <span className="activity-item__time">{item.timestamp}</span>
          </article>
        ))}
      </div>
    </SectionCard>
  )
}

export default RecentActivityPanel
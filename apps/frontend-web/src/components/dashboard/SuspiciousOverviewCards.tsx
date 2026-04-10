import type { SuspiciousOverviewItem } from '../../types/dashboard'
import RiskBadge from '../common/RiskBadge'
import SectionCard from '../common/SectionCard'

interface SuspiciousOverviewCardsProps {
  items: SuspiciousOverviewItem[]
}

function SuspiciousOverviewCards({ items }: SuspiciousOverviewCardsProps) {
  return (
    <SectionCard
      title="Suspicious Overview"
      subtitle="Rule-based alerts grouped by primary fraud trigger"
    >
      <div className="suspicious-overview-grid">
        {items.map((item) => (
          <article key={item.title} className="overview-alert-card">
            <div className="overview-alert-card__top">
              <h3>{item.title}</h3>
              <RiskBadge level={item.severity} />
            </div>
            <strong className="overview-alert-card__count">{item.count}</strong>
            <p>{item.description}</p>
          </article>
        ))}
      </div>
    </SectionCard>
  )
}

export default SuspiciousOverviewCards
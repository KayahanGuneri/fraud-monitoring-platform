import type { ActivityItem } from '../types/dashboard'

interface RecentActivityPanelProps {
  items: ActivityItem[]
}

function RecentActivityPanel({ items }: RecentActivityPanelProps) {
  return (
    <section className="panel activity-panel">
      <div className="panel-header">
        <div>
          <p className="panel-eyebrow">Live operations feed</p>
          <h2>Recent activity</h2>
        </div>
      </div>

      <div className="activity-list">
        {items.map((item) => (
          <article key={item.id} className="activity-item">
            <div className={`activity-severity ${item.severity}`} />
            <div className="activity-content">
              <div className="activity-top">
                <h3>{item.title}</h3>
                <span>{item.time}</span>
              </div>
              <p>{item.description}</p>
            </div>
          </article>
        ))}
      </div>
    </section>
  )
}

export default RecentActivityPanel
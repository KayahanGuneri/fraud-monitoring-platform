import type { MetricCardData } from '../../types/dashboard'

interface MetricCardProps {
  metric: MetricCardData
}

function MetricCard({ metric }: MetricCardProps) {
  return (
    <article className="metric-card">
      <p className="metric-title">{metric.title}</p>
      <div className="metric-row">
        <h3>{metric.value}</h3>
        <span className={`trend-badge ${metric.trend}`}>{metric.delta}</span>
      </div>
    </article>
  )
}

export default MetricCard
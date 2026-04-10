import type { OverviewMetric } from '../../types/dashboard'

interface MetricCardProps {
  metric: OverviewMetric
}

function MetricCard({ metric }: MetricCardProps) {
  return (
    <article className="metric-card">
      <div className="metric-card__header">
        <span className="metric-card__label">{metric.title}</span>
        <span className={`metric-card__trend metric-card__trend--${metric.trend}`}>
          {metric.change}
        </span>
      </div>

      <strong className="metric-card__value">{metric.value}</strong>
      <p className="metric-card__description">{metric.description}</p>
    </article>
  )
}

export default MetricCard
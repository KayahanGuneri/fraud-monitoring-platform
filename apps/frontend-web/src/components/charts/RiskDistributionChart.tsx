import {
  Cell,
  Pie,
  PieChart,
  ResponsiveContainer,
  Tooltip,
} from 'recharts'
import type { RiskDistributionPoint } from '../../types/dashboard'

interface RiskDistributionChartProps {
  data: RiskDistributionPoint[]
}

const COLORS = ['#22c55e', '#f59e0b', '#ef4444']

function RiskDistributionChart({ data }: RiskDistributionChartProps) {
  return (
    <section className="panel chart-panel">
      <div className="panel-header">
        <div>
          <p className="panel-eyebrow">Risk profile</p>
          <h2>Risk distribution snapshot</h2>
        </div>
      </div>

      <div className="chart-wrapper">
        <ResponsiveContainer width="100%" height={300}>
          <PieChart>
            <Pie
              data={data}
              dataKey="value"
              nameKey="name"
              innerRadius={70}
              outerRadius={110}
              paddingAngle={3}
            >
              {data.map((entry, index) => (
                <Cell key={entry.name} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip />
          </PieChart>
        </ResponsiveContainer>
      </div>

      <div className="risk-legend">
        {data.map((item, index) => (
          <div key={item.name} className="risk-legend-item">
            <span
              className="risk-dot"
              style={{ backgroundColor: COLORS[index % COLORS.length] }}
            />
            <span>{item.name}</span>
            <strong>{item.value}%</strong>
          </div>
        ))}
      </div>
    </section>
  )
}

export default RiskDistributionChart
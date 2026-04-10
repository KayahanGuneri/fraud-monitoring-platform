import { Cell, Pie, PieChart, ResponsiveContainer, Tooltip } from 'recharts'
import type { RiskDistributionItem } from '../../types/dashboard'
import SectionCard from '../common/SectionCard'

interface RiskDistributionChartProps {
  data: RiskDistributionItem[]
}

const COLORS = ['#22c55e', '#facc15', '#fb923c', '#f87171']

function RiskDistributionChart({ data }: RiskDistributionChartProps) {
  const total = data.reduce((sum, item) => sum + item.value, 0)

  return (
    <SectionCard
      title="Risk Distribution"
      subtitle="Current distribution of monitored user risk levels"
    >
      <div className="chart-wrapper chart-wrapper--md">
        <ResponsiveContainer width="100%" height={300}>
          <PieChart>
            <Pie
              data={data}
              dataKey="value"
              nameKey="name"
              innerRadius={70}
              outerRadius={105}
              paddingAngle={4}
            >
              {data.map((entry, index) => (
                <Cell key={entry.name} fill={COLORS[index % COLORS.length]} />
              ))}
            </Pie>
            <Tooltip
              contentStyle={{
                backgroundColor: '#0f172a',
                border: '1px solid #243041',
                borderRadius: '12px',
                color: '#e5e7eb',
              }}
            />
          </PieChart>
        </ResponsiveContainer>

        <div className="chart-legend">
          <div className="chart-legend__total">
            <span>Total</span>
            <strong>{total}</strong>
          </div>

          <div className="chart-legend__items">
            {data.map((item, index) => (
              <div key={item.name} className="chart-legend__item">
                <span
                  className="chart-legend__dot"
                  style={{ backgroundColor: COLORS[index % COLORS.length] }}
                />
                <span>{item.name}</span>
                <strong>{item.value}%</strong>
              </div>
            ))}
          </div>
        </div>
      </div>
    </SectionCard>
  )
}

export default RiskDistributionChart
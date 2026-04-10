import { Cell, Pie, PieChart, ResponsiveContainer, Tooltip } from 'recharts'
import type { FraudDecisionChartItem } from '../../types/dashboard'
import SectionCard from '../common/SectionCard'

interface FraudDecisionChartProps {
  data: FraudDecisionChartItem[]
}

const COLORS = ['#22c55e', '#f87171']

function FraudDecisionChart({ data }: FraudDecisionChartProps) {
  return (
    <SectionCard
      title="Fraud Decision Summary"
      subtitle="Normal vs suspicious transaction decisions"
    >
      <div className="chart-wrapper chart-wrapper--sm">
        <ResponsiveContainer width="100%" height={260}>
          <PieChart>
            <Pie
              data={data}
              dataKey="value"
              nameKey="name"
              innerRadius={60}
              outerRadius={90}
              paddingAngle={6}
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

        <div className="fraud-decision-summary">
          {data.map((item, index) => (
            <div key={item.name} className="fraud-decision-summary__item">
              <span
                className="fraud-decision-summary__dot"
                style={{ backgroundColor: COLORS[index % COLORS.length] }}
              />
              <span>{item.name}</span>
              <strong>{item.value}</strong>
            </div>
          ))}
        </div>
      </div>
    </SectionCard>
  )
}

export default FraudDecisionChart
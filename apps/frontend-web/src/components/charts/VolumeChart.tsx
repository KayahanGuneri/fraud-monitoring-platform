import {
  Bar,
  CartesianGrid,
  Legend,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
  BarChart,
} from 'recharts'
import type { VolumeDataPoint } from '../../types/dashboard'

interface VolumeChartProps {
  data: VolumeDataPoint[]
}

function VolumeChart({ data }: VolumeChartProps) {
  return (
    <section className="panel chart-panel">
      <div className="panel-header">
        <div>
          <p className="panel-eyebrow">Weekly volume</p>
          <h2>Transactions vs suspicious flags</h2>
        </div>
      </div>

      <div className="chart-wrapper">
        <ResponsiveContainer width="100%" height={300}>
          <BarChart data={data}>
            <CartesianGrid strokeDasharray="3 3" vertical={false} stroke="#334155" />
            <XAxis dataKey="label" stroke="#94a3b8" />
            <YAxis stroke="#94a3b8" />
            <Tooltip
              contentStyle={{
                backgroundColor: '#131a2f',
                border: '1px solid #27314d',
                borderRadius: '12px',
                color: '#e5e7eb',
              }}
              labelStyle={{ color: '#e5e7eb' }}
            />
            <Legend />
            <Bar
              dataKey="transactions"
              name="Transactions"
              fill="#60a5fa"
              radius={[8, 8, 0, 0]}
            />
            <Bar
              dataKey="suspicious"
              name="Suspicious"
              fill="#ef4444"
              radius={[8, 8, 0, 0]}
            />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </section>
  )
}

export default VolumeChart
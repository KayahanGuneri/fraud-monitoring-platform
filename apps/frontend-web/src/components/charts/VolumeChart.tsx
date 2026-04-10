import {
  Bar,
  CartesianGrid,
  ComposedChart,
  Legend,
  Line,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from 'recharts'
import type { VolumeChartItem } from '../../types/dashboard'
import SectionCard from '../common/SectionCard'

interface VolumeChartProps {
  data: VolumeChartItem[]
}

function VolumeChart({ data }: VolumeChartProps) {
  return (
    <SectionCard
      title="Transaction Volume"
      subtitle="Weekly volume and suspicious transaction trend"
    >
      <div className="chart-wrapper chart-wrapper--lg">
        <ResponsiveContainer width="100%" height={320}>
          <ComposedChart data={data}>
            <CartesianGrid stroke="#243041" strokeDasharray="3 3" vertical={false} />
            <XAxis dataKey="label" tick={{ fill: '#94a3b8', fontSize: 12 }} axisLine={false} tickLine={false} />
            <YAxis tick={{ fill: '#94a3b8', fontSize: 12 }} axisLine={false} tickLine={false} />
            <Tooltip
              contentStyle={{
                backgroundColor: '#0f172a',
                border: '1px solid #243041',
                borderRadius: '12px',
                color: '#e5e7eb',
              }}
            />
            <Legend wrapperStyle={{ color: '#cbd5e1' }} />
            <Bar
              dataKey="total"
              name="Total Transactions"
              fill="#1d4ed8"
              radius={[8, 8, 0, 0]}
              maxBarSize={44}
            />
            <Line
              type="monotone"
              dataKey="suspicious"
              name="Suspicious"
              stroke="#f87171"
              strokeWidth={3}
              dot={{ r: 4, fill: '#f87171' }}
              activeDot={{ r: 6 }}
            />
          </ComposedChart>
        </ResponsiveContainer>
      </div>
    </SectionCard>
  )
}

export default VolumeChart
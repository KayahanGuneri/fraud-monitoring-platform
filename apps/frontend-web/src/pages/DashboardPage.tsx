import AppShell from '../components/layout/AppShell'
import MetricCard from '../components/cards/MetricCard'
import VolumeChart from '../components/charts/VolumeChart'
import RiskDistributionChart from '../components/charts/RiskDistributionChart'
import DashboardTable from '../components/DashboardTable'
import RecentActivityPanel from '../components/RecentActivityPanel'
import { useDashboardData } from '../hooks/useDashboardData'

function DashboardPage() {
  const data = useDashboardData()

  return (
    <AppShell>
      <section className="metrics-grid">
        {data.metrics.map((metric) => (
          <MetricCard key={metric.title} metric={metric} />
        ))}
      </section>

      <section className="charts-grid">
        <VolumeChart data={data.volumeSeries} />
        <RiskDistributionChart data={data.riskDistribution} />
      </section>

      <section className="content-grid">
        <DashboardTable rows={data.suspiciousTransactions} />
        <RecentActivityPanel items={data.recentActivity} />
      </section>
    </AppShell>
  )
}

export default DashboardPage
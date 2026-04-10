import MetricCard from '../components/cards/MetricCard'
import FraudDecisionChart from '../components/charts/FraudDecisionChart'
import RiskDistributionChart from '../components/charts/RiskDistributionChart'
import VolumeChart from '../components/charts/VolumeChart'
import EmptyState from '../components/common/EmptyState'
import ErrorState from '../components/common/ErrorState'
import LoadingState from '../components/common/LoadingState'
import DashboardTable from '../components/dashboard/DashboardTable'
import RecentActivityPanel from '../components/dashboard/RecentActivityPanel'
import SuspiciousOverviewCards from '../components/dashboard/SuspiciousOverviewCards'
import UserRiskSummaryPanel from '../components/dashboard/UserRiskSummaryPanel'
import AppShell from '../components/layout/AppShell'
import Header from '../components/layout/Header'
import { useDashboardData } from '../hooks/useDashboardData'

function DashboardPage() {
  const { data, isLoading, isError, errorMessage } = useDashboardData()

  if (isLoading) {
    return (
      <AppShell>
        <Header
          title="Fraud Monitoring Dashboard"
          subtitle="Loading monitoring metrics and risk insights..."
        />
        <LoadingState />
      </AppShell>
    )
  }

  if (isError || !data) {
    return (
      <AppShell>
        <Header
          title="Fraud Monitoring Dashboard"
          subtitle="An issue occurred while loading the dashboard."
        />
        <ErrorState description={errorMessage ?? 'Unexpected dashboard error.'} />
      </AppShell>
    )
  }

  return (
    <AppShell>
      <Header
        title="Fraud Monitoring Dashboard"
        subtitle={`Operational overview for fraud analysis and transaction monitoring • Last updated: ${data.lastUpdated}`}
      />

      <section className="dashboard-page">
        <div className="metrics-grid">
          {data.overviewMetrics.map((metric) => (
            <MetricCard key={metric.title} metric={metric} />
          ))}
        </div>

        {data.suspiciousOverview.length > 0 ? (
          <SuspiciousOverviewCards items={data.suspiciousOverview} />
        ) : (
          <EmptyState
            title="No suspicious alerts"
            description="There are currently no suspicious overview items to display."
          />
        )}

        <div className="charts-grid charts-grid--triple">
          <VolumeChart data={data.volumeData} />
          <RiskDistributionChart data={data.riskDistribution} />
          <FraudDecisionChart data={data.fraudDecisionData} />
        </div>

        <div className="dashboard-page__content">
          <div className="dashboard-page__main">
            {data.transactions.length > 0 ? (
              <DashboardTable transactions={data.transactions} />
            ) : (
              <EmptyState
                title="No transactions found"
                description="Transaction data will appear here when available."
              />
            )}
          </div>

          <div className="dashboard-page__side">
            {data.userRiskSummaries.length > 0 ? (
              <UserRiskSummaryPanel users={data.userRiskSummaries} />
            ) : (
              <EmptyState
                title="No risky users"
                description="There are no users currently under elevated monitoring."
              />
            )}

            {data.recentActivity.length > 0 ? (
              <RecentActivityPanel items={data.recentActivity} />
            ) : (
              <EmptyState
                title="No recent activity"
                description="Recent monitoring activity will appear here."
              />
            )}
          </div>
        </div>
      </section>
    </AppShell>
  )
}

export default DashboardPage
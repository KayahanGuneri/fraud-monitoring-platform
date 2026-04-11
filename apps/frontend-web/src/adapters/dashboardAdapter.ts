import type { DashboardApiResponse } from '../api/dashboardApi'
import type { DashboardData } from '../types/dashboard'

export function mapDashboardApiResponseToDashboardData(
  response: DashboardApiResponse,
): DashboardData {
  return {
    overviewMetrics: response.overviewMetrics,
    suspiciousOverview: response.suspiciousOverview,
    transactions: response.transactions,
    userRiskSummaries: response.userRiskSummaries,
    recentActivity: response.recentActivity,
    volumeData: response.volumeData,
    riskDistribution: response.riskDistribution,
    fraudDecisionData: response.fraudDecisionData,
    lastUpdated: response.lastUpdated,
  }
}
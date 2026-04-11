import { mockDashboardData } from '../data/mockDashboardData'
import type { DashboardData } from '../types/dashboard'

const MOCK_DELAY_MS = 400

export interface DashboardApiResponse {
  overviewMetrics: DashboardData['overviewMetrics']
  suspiciousOverview: DashboardData['suspiciousOverview']
  transactions: DashboardData['transactions']
  userRiskSummaries: DashboardData['userRiskSummaries']
  recentActivity: DashboardData['recentActivity']
  volumeData: DashboardData['volumeData']
  riskDistribution: DashboardData['riskDistribution']
  fraudDecisionData: DashboardData['fraudDecisionData']
  lastUpdated: string
}

async function simulateDelay(delayMs: number): Promise<void> {
  await new Promise((resolve) => setTimeout(resolve, delayMs))
}

async function getMockDashboardOverview(): Promise<DashboardApiResponse> {
  await simulateDelay(MOCK_DELAY_MS)


  return {
    overviewMetrics: mockDashboardData.overviewMetrics,
    suspiciousOverview: mockDashboardData.suspiciousOverview,
    transactions: mockDashboardData.transactions,
    userRiskSummaries: mockDashboardData.userRiskSummaries,
    recentActivity: mockDashboardData.recentActivity,
    volumeData: mockDashboardData.volumeData,
    riskDistribution: mockDashboardData.riskDistribution,
    fraudDecisionData: mockDashboardData.fraudDecisionData,
    lastUpdated: mockDashboardData.lastUpdated,
  }
}

export const dashboardApi = {
  async getDashboardOverview(): Promise<DashboardApiResponse> {
    return getMockDashboardOverview()
  },
}
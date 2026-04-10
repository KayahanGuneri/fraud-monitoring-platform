import { mockDashboardData } from '../data/mockDashboardData'
import type { DashboardData } from '../types/dashboard'

const MOCK_DELAY_MS = 400

export const dashboardService = {
  async getDashboardData(): Promise<DashboardData> {
    await new Promise((resolve) => setTimeout(resolve, MOCK_DELAY_MS))
    return mockDashboardData
  },
}
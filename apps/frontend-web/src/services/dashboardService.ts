import { dashboardApi } from '../api/dashboardApi'
import { mapDashboardApiResponseToDashboardData } from '../adapters/dashboardAdapter'
import type { DashboardData } from '../types/dashboard'

export const dashboardService = {
  async getDashboardData(): Promise<DashboardData> {
    const response = await dashboardApi.getDashboardOverview()
    return mapDashboardApiResponseToDashboardData(response)
  },
}
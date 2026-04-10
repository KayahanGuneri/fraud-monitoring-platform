import { useEffect, useState } from 'react'
import { dashboardService } from '../services/dashboardService'
import type { DashboardData } from '../types/dashboard'

interface UseDashboardDataResult {
  data: DashboardData | null
  isLoading: boolean
  isError: boolean
  errorMessage: string | null
}

export function useDashboardData(): UseDashboardDataResult {
  const [data, setData] = useState<DashboardData | null>(null)
  const [isLoading, setIsLoading] = useState(true)
  const [isError, setIsError] = useState(false)
  const [errorMessage, setErrorMessage] = useState<string | null>(null)

  useEffect(() => {
    let isMounted = true

    async function loadDashboardData() {
      try {
        setIsLoading(true)
        setIsError(false)
        setErrorMessage(null)

        const result = await dashboardService.getDashboardData()

        if (!isMounted) {
          return
        }

        setData(result)
      } catch {
        if (!isMounted) {
          return
        }

        setIsError(true)
        setErrorMessage('Dashboard data could not be loaded.')
      } finally {
        if (!isMounted) {
          return
        }

        setIsLoading(false)
      }
    }

    loadDashboardData()

    return () => {
      isMounted = false
    }
  }, [])

  return {
    data,
    isLoading,
    isError,
    errorMessage,
  }
}
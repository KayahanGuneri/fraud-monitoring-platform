import { useCallback, useEffect, useState } from 'react'
import { dashboardService } from '../services/dashboardService'
import type { DashboardData } from '../types/dashboard'
import { formatDashboardErrorMessage } from '../utils/dashboardFormatters'

interface UseDashboardDataResult {
  data: DashboardData | null
  isLoading: boolean
  isError: boolean
  errorMessage: string | null
  reload: () => Promise<void>
}

export function useDashboardData(): UseDashboardDataResult {
  const [data, setData] = useState<DashboardData | null>(null)
  const [isLoading, setIsLoading] = useState(true)
  const [isError, setIsError] = useState(false)
  const [errorMessage, setErrorMessage] = useState<string | null>(null)

  const loadDashboardData = useCallback(async () => {
    try {
      setIsLoading(true)
      setIsError(false)
      setErrorMessage(null)

      const result = await dashboardService.getDashboardData()
      setData(result)
    } catch (error) {
      setData(null)
      setIsError(true)
      setErrorMessage(formatDashboardErrorMessage(error))
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    let isActive = true

    async function initializeDashboardData() {
      try {
        setIsLoading(true)
        setIsError(false)
        setErrorMessage(null)

        const result = await dashboardService.getDashboardData()

        if (!isActive) {
          return
        }

        setData(result)
      } catch (error) {
        if (!isActive) {
          return
        }

        setData(null)
        setIsError(true)
        setErrorMessage(formatDashboardErrorMessage(error))
      } finally {
        if (!isActive) {
          return
        }

        setIsLoading(false)
      }
    }

    void initializeDashboardData()

    return () => {
      isActive = false
    }
  }, [])

  return {
    data,
    isLoading,
    isError,
    errorMessage,
    reload: loadDashboardData,
  }
}
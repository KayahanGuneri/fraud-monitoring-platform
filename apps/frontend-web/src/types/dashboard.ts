export type MetricTrend = 'up' | 'down' | 'neutral'

export interface MetricCardData {
  title: string
  value: string
  delta: string
  trend: MetricTrend
}

export interface TransactionRow {
  id: string
  userId: string
  amount: string
  location: string
  riskLevel: 'LOW' | 'MEDIUM' | 'HIGH'
  status: 'NORMAL' | 'SUSPICIOUS'
  timestamp: string
}

export interface ActivityItem {
  id: string
  title: string
  description: string
  time: string
  severity: 'info' | 'warning' | 'critical'
}

export interface VolumeDataPoint {
  label: string
  transactions: number
  suspicious: number
}

export interface RiskDistributionPoint {
  name: string
  value: number
}

export interface DashboardData {
  metrics: MetricCardData[]
  volumeSeries: VolumeDataPoint[]
  riskDistribution: RiskDistributionPoint[]
  suspiciousTransactions: TransactionRow[]
  recentActivity: ActivityItem[]
}
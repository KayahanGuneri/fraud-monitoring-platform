export type TransactionStatus = 'APPROVED' | 'PENDING' | 'REVIEW' | 'DECLINED'
export type FraudDecision = 'NORMAL' | 'SUSPICIOUS'
export type RiskLevel = 'LOW' | 'MEDIUM' | 'HIGH' | 'CRITICAL'

export interface Transaction {
  id: string
  userId: string
  userName: string
  amount: number
  currency: string
  status: TransactionStatus
  fraudDecision: FraudDecision
  riskLevel: RiskLevel
  country: string
  city: string
  paymentMethod: string
  createdAt: string
}

export interface OverviewMetric {
  title: string
  value: string
  change: string
  trend: 'up' | 'down' | 'neutral'
  description: string
}

export interface SuspiciousOverviewItem {
  title: string
  count: number
  description: string
  severity: RiskLevel
}

export interface UserRiskSummary {
  userId: string
  userName: string
  riskLevel: RiskLevel
  suspiciousTransactions: number
  lastActivity: string
  flaggedReason: string
}

export interface RecentActivityItem {
  id: string
  title: string
  description: string
  timestamp: string
  type: 'transaction' | 'fraud' | 'user'
  severity: RiskLevel
}

export interface VolumeChartItem {
  label: string
  total: number
  suspicious: number
}

export interface RiskDistributionItem {
  name: string
  value: number
}

export interface FraudDecisionChartItem {
  name: string
  value: number
}

export interface DashboardData {
  overviewMetrics: OverviewMetric[]
  suspiciousOverview: SuspiciousOverviewItem[]
  transactions: Transaction[]
  userRiskSummaries: UserRiskSummary[]
  recentActivity: RecentActivityItem[]
  volumeData: VolumeChartItem[]
  riskDistribution: RiskDistributionItem[]
  fraudDecisionData: FraudDecisionChartItem[]
  lastUpdated: string
}
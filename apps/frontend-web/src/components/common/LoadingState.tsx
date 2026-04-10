interface LoadingStateProps {
  title?: string
  description?: string
}

function LoadingState({
  title = 'Loading data...',
  description = 'Dashboard metrics are being prepared.',
}: LoadingStateProps) {
  return (
    <div className="state-card">
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  )
}

export default LoadingState
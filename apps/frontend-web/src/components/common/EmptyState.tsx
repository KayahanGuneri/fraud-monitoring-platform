interface EmptyStateProps {
  title?: string
  description?: string
}

function EmptyState({
  title = 'No data available',
  description = 'There is currently no data to display in this section.',
}: EmptyStateProps) {
  return (
    <div className="state-card">
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  )
}

export default EmptyState
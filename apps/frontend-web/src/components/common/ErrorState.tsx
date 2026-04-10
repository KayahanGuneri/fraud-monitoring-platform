interface ErrorStateProps {
  title?: string
  description?: string
}

function ErrorState({
  title = 'Something went wrong',
  description = 'Dashboard data could not be loaded.',
}: ErrorStateProps) {
  return (
    <div className="state-card state-card--error">
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  )
}

export default ErrorState
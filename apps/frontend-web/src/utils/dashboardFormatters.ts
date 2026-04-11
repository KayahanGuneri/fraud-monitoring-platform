export function formatDashboardErrorMessage(error: unknown): string {
  if (error instanceof Error) {
    const trimmedMessage = error.message.trim()

    if (trimmedMessage.length > 0) {
      return trimmedMessage
    }
  }

  return 'Dashboard data could not be loaded.'
}
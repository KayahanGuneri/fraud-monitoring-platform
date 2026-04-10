interface HeaderProps {
  title: string
  subtitle: string
}

function Header({ title, subtitle }: HeaderProps) {
  return (
    <header className="page-header">
      <div>
        <h1 className="page-header__title">{title}</h1>
        <p className="page-header__subtitle">{subtitle}</p>
      </div>
    </header>
  )
}

export default Header
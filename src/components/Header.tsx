import { Link } from "@tanstack/react-router";
import { Coffee, Menu, X } from "lucide-react";
import { useState } from "react";
import { useAuth } from "@/hooks/useAuth";

const links = [
  { to: "/", label: "Home" },
  { to: "/sobre-nos", label: "Sobre Nós" },
  { to: "/historia-do-cafe", label: "História do Café" },
  { to: "/produtos", label: "Produtos" },
  { to: "/contato", label: "Contato" },
] as const;

export function Header() {
  const [open, setOpen] = useState(false);
  const { user, signOut } = useAuth();

  return (
    <header className="fixed inset-x-0 top-0 z-50 border-b border-border/60 bg-background/85 backdrop-blur-md">
      <div className="mx-auto flex h-18 max-w-6xl items-center justify-between gap-4 px-5 py-4">
        <Link to="/" className="flex items-center gap-2.5" onClick={() => setOpen(false)}>
          <span className="flex size-10 items-center justify-center rounded-full bg-primary text-primary-foreground">
            <Coffee className="size-5" />
          </span>
          <span className="leading-tight">
            <span className="block font-display text-lg font-bold text-primary">Bom Café</span>
            <span className="eyebrow text-[0.6rem]">Origem &amp; Sabor</span>
          </span>
        </Link>

        <nav className="hidden items-center gap-7 lg:flex">
          {links.map((link) => (
            <Link
              key={link.to}
              to={link.to}
              className="text-sm font-medium text-muted-foreground transition-colors hover:text-primary"
              activeProps={{ className: "text-primary" }}
              activeOptions={{ exact: link.to === "/" }}
            >
              {link.label}
            </Link>
          ))}
        </nav>

        <div className="hidden items-center gap-3 lg:flex">
          {user ? (
            <>
              <span className="max-w-40 truncate text-sm text-muted-foreground">{user.email}</span>
              <button
                onClick={() => signOut()}
                className="rounded-full border border-primary/30 px-4 py-2 text-sm font-medium text-primary transition-colors hover:bg-secondary"
              >
                Sair
              </button>
            </>
          ) : (
            <>
              <Link
                to="/login"
                className="rounded-full border border-primary/30 px-4 py-2 text-sm font-medium text-primary transition-colors hover:bg-secondary"
              >
                Login
              </Link>
              <Link
                to="/cadastro"
                className="rounded-full bg-primary px-4 py-2 text-sm font-medium text-primary-foreground shadow-soft transition-colors hover:bg-espresso"
              >
                Cadastro
              </Link>
            </>
          )}
        </div>

        <button
          className="rounded-md p-2 text-primary lg:hidden"
          onClick={() => setOpen((v) => !v)}
          aria-label="Abrir menu"
        >
          {open ? <X className="size-6" /> : <Menu className="size-6" />}
        </button>
      </div>

      {open && (
        <div className="border-t border-border bg-background px-5 pb-6 pt-3 lg:hidden">
          <nav className="flex flex-col gap-1">
            {links.map((link) => (
              <Link
                key={link.to}
                to={link.to}
                onClick={() => setOpen(false)}
                className="rounded-md px-2 py-2.5 text-sm font-medium text-muted-foreground transition-colors hover:bg-secondary hover:text-primary"
                activeProps={{ className: "text-primary" }}
                activeOptions={{ exact: link.to === "/" }}
              >
                {link.label}
              </Link>
            ))}
          </nav>
          <div className="mt-4 flex gap-3">
            {user ? (
              <button
                onClick={() => {
                  setOpen(false);
                  signOut();
                }}
                className="flex-1 rounded-full border border-primary/30 px-4 py-2.5 text-center text-sm font-medium text-primary"
              >
                Sair
              </button>
            ) : (
              <>
                <Link
                  to="/login"
                  onClick={() => setOpen(false)}
                  className="flex-1 rounded-full border border-primary/30 px-4 py-2.5 text-center text-sm font-medium text-primary"
                >
                  Login
                </Link>
                <Link
                  to="/cadastro"
                  onClick={() => setOpen(false)}
                  className="flex-1 rounded-full bg-primary px-4 py-2.5 text-center text-sm font-medium text-primary-foreground"
                >
                  Cadastro
                </Link>
              </>
            )}
          </div>
        </div>
      )}
    </header>
  );
}

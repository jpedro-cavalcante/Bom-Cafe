import { Link } from "@tanstack/react-router";
import { Coffee, Instagram, Facebook, Youtube, Mail, MapPin, Phone } from "lucide-react";

export function Footer() {
  return (
    <footer className="mt-24 bg-espresso text-cream">
      <div className="mx-auto grid max-w-6xl gap-10 px-5 py-16 sm:grid-cols-2 lg:grid-cols-4">
        <div>
          <div className="flex items-center gap-2.5">
            <span className="flex size-10 items-center justify-center rounded-full bg-gold text-accent-foreground">
              <Coffee className="size-5" />
            </span>
            <span className="font-display text-lg font-bold">Bom Café</span>
          </div>
          <p className="mt-4 text-sm text-cream/70">
            Mais do que uma bebida. Uma história em cada xícara.
          </p>
          <div className="mt-5 flex gap-3">
            {[Instagram, Facebook, Youtube].map((Icon, i) => (
              <span
                key={i}
                className="flex size-9 items-center justify-center rounded-full border border-cream/20 text-cream/80 transition-colors hover:border-gold hover:text-gold"
              >
                <Icon className="size-4" />
              </span>
            ))}
          </div>
        </div>

        <div>
          <h4 className="text-sm font-semibold uppercase tracking-widest text-gold">Navegação</h4>
          <ul className="mt-4 space-y-2.5 text-sm text-cream/75">
            <li>
              <Link to="/" className="transition-colors hover:text-gold">
                Home
              </Link>
            </li>
            <li>
              <Link to="/sobre-nos" className="transition-colors hover:text-gold">
                Sobre Nós
              </Link>
            </li>
            <li>
              <Link to="/historia-do-cafe" className="transition-colors hover:text-gold">
                História do Café
              </Link>
            </li>
            <li>
              <Link to="/produtos" className="transition-colors hover:text-gold">
                Produtos
              </Link>
            </li>
          </ul>
        </div>

        <div>
          <h4 className="text-sm font-semibold uppercase tracking-widest text-gold">Conta</h4>
          <ul className="mt-4 space-y-2.5 text-sm text-cream/75">
            <li>
              <Link to="/login" className="transition-colors hover:text-gold">
                Entrar
              </Link>
            </li>
            <li>
              <Link to="/cadastro" className="transition-colors hover:text-gold">
                Criar conta
              </Link>
            </li>
            <li>
              <Link to="/contato" className="transition-colors hover:text-gold">
                Fale com a gente
              </Link>
            </li>
          </ul>
        </div>

        <div>
          <h4 className="text-sm font-semibold uppercase tracking-widest text-gold">Contato</h4>
          <ul className="mt-4 space-y-3 text-sm text-cream/75">
            <li className="flex items-start gap-2.5">
              <Mail className="mt-0.5 size-4 shrink-0 text-gold" />
              contato@bomcafe.com.br
            </li>
            <li className="flex items-start gap-2.5">
              <Phone className="mt-0.5 size-4 shrink-0 text-gold" />
              (11) 4000-0000
            </li>
            <li className="flex items-start gap-2.5">
              <MapPin className="mt-0.5 size-4 shrink-0 text-gold" />
              São Paulo, SP — Brasil
            </li>
          </ul>
        </div>
      </div>

      <div className="border-t border-cream/10 px-5 py-6 text-center text-xs text-cream/50">
        © {new Date().getFullYear()} Bom Café. Feito com respeito por quem cultiva cada grão.
      </div>
    </footer>
  );
}

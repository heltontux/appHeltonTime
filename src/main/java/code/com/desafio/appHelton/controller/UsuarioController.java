package code.com.desafio.appHelton.controller;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;

	import code.com.desafio.appHelton.model.domain.Time;
import code.com.desafio.appHelton.model.domain.Usuario;
import code.com.desafio.appHelton.model.service.TimeService;
import code.com.desafio.appHelton.model.service.UsuarioService;

	@Controller
	public class UsuarioController {
		
		@Autowired
		public UsuarioService usuarioService;
		
		@GetMapping(value = "/usuario")
		public String telaCadastro() {
			return "usuario/cadastro";
		}
		
		@GetMapping(value = "/usuario/lista")
		public String obterLista(Model model) {
			model.addAttribute("usuarios", usuarioService.obterLista());
			return "usuario/lista";
		}
		
		@PostMapping(value = "/usuario/incluir")
		public String incluir(Model model, Usuario usuario) {
			usuarioService.incluir(usuario);
			model.addAttribute("mensagem", usuario.getNome()+" foi cadastrado com sucesso!!!");
			return obterLista(model);
		}
		
		@GetMapping(value = "/usuario/{id}/detalhar")
		public String detalhar(Model model, @PathVariable Integer id) {
			Usuario usuario = usuarioService.obterPorId(id);
			model.addAttribute("meuUsuario", usuario);
			return telaCadastro();
		}
		
		@GetMapping(value = "/usuarioVoltar")
		public String voltar() {
			return "redirect:/usuario/lista";
		}
		
		@GetMapping(value = "/usuario/{id}/excluir")
		public String excluir(Model model, @PathVariable Integer id) {
			Usuario usuarioExcluido = usuarioService.obterPorId(id);
			usuarioService.excluir(id);
			model.addAttribute("mensagem", "Usuário "+usuarioExcluido.getNome()+" excluido com sucesso!!!");
			return obterLista(model);
		}
		
		@PostMapping(value = "/usuario/ordenar")
		public String ordenar(Model model, @RequestParam String sortBy) {
			model.addAttribute("usuarios", usuarioService.obterLista(sortBy));
			return "usuario/lista";
		}
		
	}

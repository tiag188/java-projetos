package com.br.crm.controller;

import com.br.crm.model.Membro;
import com.br.crm.model.Projeto;
import com.br.crm.model.enums.ClassificacaoRisco;
import com.br.crm.model.enums.StatusProjeto;
import com.br.crm.repository.MembroRepository;
import com.br.crm.repository.ProjetoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.validation.BindingResult;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private MembroRepository membroRepository;

    @GetMapping("/")
    public String listarProjetos(Model model) {
        List<Projeto> projetos = (List<Projeto>) projetoRepository.findAll();
        model.addAttribute("projetos", projetos);
        return "projeto/list";
    }

    @GetMapping("/novo")
    public String novoProjetoForm(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("classificacoesRisco", ClassificacaoRisco.values());
        model.addAttribute("statusProjetos", StatusProjeto.values());
        return "projeto/form";
    }

    @PostMapping("/novo")
    public String salvarProjeto(@Valid @ModelAttribute("projeto") Projeto projeto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "projeto/form";
        }

        projetoRepository.save(projeto);
        return "redirect:/projetos/";
    }

    @GetMapping("/{id}/editar")
    public String editarProjetoForm(@PathVariable Long id, Model model) {
        Projeto projeto = projetoRepository.findById(id).orElse(null);
        model.addAttribute("projeto", projeto);
        model.addAttribute("classificacoesRisco", ClassificacaoRisco.values());
        model.addAttribute("statusProjetos", StatusProjeto.values());
        return "projeto/form";
    }

    @PostMapping("/{id}/editar")
    public String atualizarProjeto(@PathVariable Long id, @Valid @ModelAttribute("projeto") Projeto projeto,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "projeto/form";
        }

        projetoRepository.save(projeto);
        return "redirect:/projetos/";
    }

    @GetMapping("/{id}/excluir")
    public String excluirProjeto(@PathVariable Long id) {
        Projeto projeto = projetoRepository.findById(id).orElse(null);

        if (projeto != null) {
            if (projeto.getStatus() == StatusProjeto.INICIADO ||
                projeto.getStatus() == StatusProjeto.EM_ANDAMENTO ||
                projeto.getStatus() == StatusProjeto.ENCERRADO) {
                // Projeto não pode ser excluído se estiver em um desses estados
                return "redirect:/projetos/";
            }
            projetoRepository.deleteById(id);
        }

        return "redirect:/projetos/";
    }

    @GetMapping("/{id}/associar-membro")
    public String associarMembroForm(@PathVariable Long id, Model model) {
        Projeto projeto = projetoRepository.findById(id).orElse(null);
        List<Membro> membros = (List<Membro>) membroRepository.findAll();
        model.addAttribute("projeto", projeto);
        model.addAttribute("membros", membros);
        return "projeto/associar-membro";
    }

    @PostMapping("/{id}/associar-membro")
    public String associarMembro(@PathVariable Long id, @RequestParam Long membroId) {
        Projeto projeto = projetoRepository.findById(id).orElse(null);
        Membro membro = membroRepository.findById(membroId).orElse(null);

        if (projeto != null && membro != null) {
            projeto.getMembros().add(membro);
            projetoRepository.save(projeto);
        }

        return "redirect:/projetos/";
    }
}

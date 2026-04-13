package com.geraldo.finan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geraldo.finan.dto.OrcamentoDTO;
import com.geraldo.finan.dto.Response;
import com.geraldo.finan.services.OrcamentoService;

@RestController
@RequestMapping("/api/orcamento")
public class OrcamentoController extends BaseController {

    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping("")
    public Response obterOrcamento(@RequestParam(required = false) Integer mes,
                                 @RequestParam(required = false) Integer ano) {
        OrcamentoDTO orcamento = orcamentoService.obterOrcamento(mes, ano);
        return success(orcamento);
    }

}

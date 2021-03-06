package br.com.casadocodigo.loja.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Relatorio;


@RestController
public class RelatorioProdutosController {
	
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping(value="/relatorio-produtos", method=RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public Relatorio listaProdutosCadastrados(@RequestParam(value = "data", required = false) String dataLancamento) throws ParseException {
		//Lista de livros com data e sem data
		if(dataLancamento != null) {
			SimpleDateFormat formatadorDeData = new SimpleDateFormat("yyyy-MM-dd");
	        Date dataFormatada = formatadorDeData.parse(dataLancamento);
	        Calendar data = Calendar.getInstance();
	        data.setTime(dataFormatada);
			return dao.selecionaProdutosPorData(data);
		} else {
			return dao.selecionaTodosProdutos();
		}
	}

}

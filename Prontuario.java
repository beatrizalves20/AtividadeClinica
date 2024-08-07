package com.mycompany.exercicioclinica;

import com.mycompany.exercicioclinica.Procedimento;
import com.mycompany.exercicioclinica.Internacao;
import com.mycompany.exercicioclinica.ContaPrinter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prontuario {

	private String nomePaciente;
	private Internacao internacao;
	private Set<Procedimento> procedimentos = new HashSet<>();

	public Prontuario(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setInternacao(Internacao internacao) {
		this.internacao = internacao;
	}

	public Internacao getInternacao() {
		return this.internacao;
	}

	public void addProcedimento(Procedimento procedimento) {
		this.procedimentos.add(procedimento);
	}

	public Set<Procedimento> getProcedimentos() {
		return this.procedimentos;
	}

	public String imprimaConta() {
            ContaPrinter contaPrinter = new ContaPrinter();
            return contaPrinter.imprimaConta(this);
	}

	boolean b = false;

	public Prontuario carregueProntuario(String arquivoCsv) throws IOException {
		Prontuario prontuario = new Prontuario(null);

		Path path = Paths.get(arquivoCsv);

		Stream<String> linhas = Files.lines(path);

		linhas.forEach((str) -> {
			if (b == false) {
				b = true;
			} else {
				System.out.println(str);

				String[] dados = str.split(",");

				String nomePaciente = dados[0].trim();

				TipoLeito tipoLeito = dados[1] != null && !dados[1].trim().isEmpty() ? TipoLeito.valueOf(dados[1].trim()) : null;

				int qtdeDiasInternacao = dados[2] != null && !dados[2].trim().isEmpty() ? Integer.parseInt(dados[2].trim()) : -1;

				TipoProcedimento tipoProcedimento = dados[3] != null && !dados[3].trim().isEmpty() ? TipoProcedimento.valueOf(dados[3].trim()) : null;

				int qtdeProcedimentos = dados.length == 5 && dados[4] != null && !dados[4].trim().isEmpty() ? Integer.parseInt(dados[4].trim()) : -1;

				prontuario.nomePaciente = nomePaciente;

				if (tipoLeito != null && qtdeDiasInternacao > 0) {
					prontuario.internacao = new Internacao(tipoLeito, qtdeDiasInternacao);
				}

				if (tipoProcedimento != null && qtdeProcedimentos > 0) {
					while (qtdeProcedimentos > 0) {
						prontuario.addProcedimento(new Procedimento(tipoProcedimento));
						qtdeProcedimentos--;
					}
				}
			}
		});

		return prontuario;
	}

	List<String> l = new ArrayList<>();

	public String salveProntuario() throws IOException {

		l.add("nome_paciente,tipo_leito,qtde_dias_internacao,tipo_procedimento,qtde_procedimentos");

		String l1 = nomePaciente + ",";

		if (internacao != null) {
			l1 += internacao.getTipoLeito() + "," + internacao.getQtdeDias() + ",,";
			l.add(l1);
		}

		if (!procedimentos.isEmpty()) {
			Map<TipoProcedimento, Long> procedimentosAgrupados = procedimentos.stream().collect(
					Collectors.groupingBy(Procedimento::getTipoProcedimento, Collectors.counting()));

			List<TipoProcedimento> procedimentosOrdenados = new ArrayList<>(procedimentosAgrupados.keySet());
			Collections.sort(procedimentosOrdenados);

			for (TipoProcedimento chave : procedimentosOrdenados) {
				String l2 = nomePaciente + ",,," + chave + "," + procedimentosAgrupados.get(chave);
				l.add(l2);
			}
		}

		if (l.size() == 1) {
			l1 += ",,,";
			l.add(l1);
		}

		Path path = Paths.get(nomePaciente.replaceAll(" ", "_").concat(String.valueOf(System.currentTimeMillis())).concat(".csv"));

		Files.write(path, l);

		return path.toString();
	}
}

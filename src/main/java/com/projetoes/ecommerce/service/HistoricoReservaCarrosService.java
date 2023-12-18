package com.projetoes.ecommerce.service;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.projetoes.ecommerce.model.Carro;
import com.projetoes.ecommerce.model.HistoricoReservaCarro;
import com.projetoes.ecommerce.respository.CarroDAO;
import com.projetoes.ecommerce.respository.HistoricoReservaCarroDAO;
import com.projetoes.ecommerce.util.StringExtensions;
import com.projetoes.ecommerce.util.Transacional;

public class HistoricoReservaCarrosService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HistoricoReservaCarroDAO historicoReservaCarros;
	
	@Inject
	private CarroDAO carros;
	
	@Inject
	private StringExtensions stringExtensions;
	
	@Transacional
	public void salvar(HistoricoReservaCarro historico, Carro carro) {
		try {
			carros.guardar(carro);
			historicoReservaCarros.guardar(historico);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transacional
	public void atualizar(HistoricoReservaCarro historico, Carro carro) {
		try {
			carros.guardar(carro);
			historicoReservaCarros.guardar(historico);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transacional
	public void excluir(long id) {
		historicoReservaCarros.remover(id);
	}
	
	public void criarPDFPorListaHistoricoReservaCarro(List<HistoricoReservaCarro> listaHistoricoReserva,
					ByteArrayOutputStream byteArrayOutputStream) {
		
		// Criar pdf
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        // Adiciona título ao documento
        Paragraph paragraph = new Paragraph("Histórico reserva", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        // Adiciona cabeçalho
        PdfPTable table = new PdfPTable(6); // Número de colunas

        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Cabeçalho
        addTableHeader(table, "Usuário", "Telefone", "Carro", "Valor", "Data Reserva", "Data Liberação");

        // Adiciona dados as linhas
        for (HistoricoReservaCarro historicoReserva : listaHistoricoReserva) {
            addTableRow(table,
                    historicoReserva.getUsuario().getLogin(),
                    stringExtensions.getFormattedTelefone(historicoReserva.getUsuario().getTelefone()),
                    historicoReserva.getCarro().getMarca() + " " + historicoReserva.getCarro().getModelo(),
                    stringExtensions.getFormatteMoney(historicoReserva.getCarro().getValor()),
                    stringExtensions.getFormattedFullDate(historicoReserva.getDataReserva()),
                    stringExtensions.getFormattedFullDate(historicoReserva.getDataLiberacao()));
        }

        // Adiciona tabela ao documento
        document.add(table);

        document.close();
	}
	
	private void addTableHeader(PdfPTable table, String... headers) {
	    for (String header : headers) {
	        PdfPCell cell = new PdfPCell();
	        cell.setPhrase(new Paragraph(header));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(cell);
	    }
	}

	private void addTableRow(PdfPTable table, String... values) {
	    for (String value : values) {
	        PdfPCell cell = new PdfPCell();
	        cell.setPhrase(new Paragraph(value));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(cell);
	    }
	}
}

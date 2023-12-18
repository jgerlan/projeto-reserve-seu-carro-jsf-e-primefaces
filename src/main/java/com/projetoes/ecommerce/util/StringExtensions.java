package com.projetoes.ecommerce.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

public class StringExtensions implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String getFormattedTelefone(String telefone) {
        if (!telefone.isEmpty()) {
            // Remove non-digit characters
            String cleaned = telefone.replaceAll("\\D", "");

            // Apply the custom phone number format (99) 9 9999-9999
            return MessageFormat.format("({0}) {1} {2}-{3}",
                    cleaned.substring(0, 2),
                    cleaned.substring(2, 3),
                    cleaned.substring(3, 7),
                    cleaned.substring(7));
        }
        return "";
    }
	
	public String getFormattedFullDate(Date date) {
		return Optional.ofNullable(date)
        .map(dateAux -> new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(dateAux))
        .orElse("");
	}
	
	public String getFormatteMoney(Double valor) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(valor);
	}
}

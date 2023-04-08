package com.ufcg.psoft.mercadofacil.service;

public class ValidaCodigoDeBarrasServiceImpl implements ValidaCodigoDeBarrasService {

	@Override
    public Boolean isValid(String codigoBarras) {
        boolean codigoIsValid = false;

        if (codigoBarras != null && codigoBarras.length() == 13) {
            char[] caracteres = codigoBarras.toCharArray();

            int somaDasPosicoesImpares = 0;
            int somaDasPosicoesPares = 0;
            for (int i = 11; i >= 0; i--) {
                if (i % 2 != 0) {
                    somaDasPosicoesImpares += Character.getNumericValue(caracteres[i]);
                } else {
                    somaDasPosicoesPares += Character.getNumericValue(caracteres[i]);
                }

            }

            somaDasPosicoesImpares *= 3;
            int somaTotal = somaDasPosicoesImpares + somaDasPosicoesPares;

            if ((somaTotal + Character.getNumericValue(caracteres[12])) % 10 == 0) {
                codigoIsValid = true;
            }
        }

        return codigoIsValid;
	}
}

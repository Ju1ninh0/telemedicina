package org.telemedicina.domain.valueobjects;

public class Email {
    private final String valor;

    public Email(String valor){
        if(!isValido(valor)){
            throw new IllegalArgumentException("Email inválido");
        }
        this.valor=valor;
    }
    private boolean isValido(String email){
        if(email==null){
            return false;
        }
        if(!email.contains("@")){
            return false;
        }
        if(email.startsWith("@") || email.endsWith("@")){
            return false;
        }
        int posicaoDoArroba= email.indexOf("@");
        String depoisDoArroba = email.substring(posicaoDoArroba+ 1);
        if(!depoisDoArroba.contains(".")){
            return false;
        }
        return true;
    }
    public String getValor(){
        return valor;
    }

}

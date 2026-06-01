package org.telemedicina.domain.valueobjects;

public class Cpf {
    private final String valor;

    public Cpf(String valor){
       if(!isValido(valor)){
           throw new IllegalArgumentException("Cpf inválido");
       }
       this.valor=valor;
    }
    private boolean isValido(String cpf){
        if(cpf == null){
            return false;
        }
        if(cpf.length()!= 11){
            return false;
        }
        for(int i=0; i< cpf.length();i++){
            if(!Character.isDigit(cpf.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public String getValor(){
        return valor;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;
import abstracto.Instruccion;
import Simbolo.*;
import excepciones.Errores;
/**
 *
 * @author manue
 */
public class Relacionales extends Instruccion{
     private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;

    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        var condIzq = this.cond1.interpretar(arbol, tabla);
        if (condIzq instanceof Errores) {
            return condIzq;
        }

        var condDer = this.cond2.interpretar(arbol, tabla);
        if (condDer instanceof Errores) {
            return condDer;
        }

        return switch (relacional) {
            case EQUALS ->
                this.equals(condIzq, condDer);
            case NOTEQUALS ->
                this.notequals(condIzq, condDer);
            case MENORQ ->
                this.menorq(condIzq, condDer);
            case MENORIGUALQ ->
                this.menorIgualq(condIzq, condDer);
            case MAYORQ ->
                this.mayorq(condIzq, condDer);
            case MAYORIGUALQ ->
                this.mayorIgualq(condIzq, condDer);
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }

    public Object equals(Object comp1, Object comp2) {
        var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

         switch (comparando1) {
            case ENTERO ->{
                 switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 == (int) comp2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 == (double) comp2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 == (int)((Character) comp2);
                    }
                    default ->{
                       return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                }
            }
            }
            case DECIMAL ->{
                switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 == (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 == (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (double) comp1 == (double)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case CARACTER ->{
            switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int)((Character) comp1) == (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) ((Character)comp1) == (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (int)((Character) comp1) == (int)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case BOOLEANO ->{
                 switch(comparando2){
                     case BOOLEANO->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (boolean)comp1 == (boolean)comp2;
                     }
                     default ->{
                      return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                     }
                 }
            }
            case CADENA ->{
                switch (comparando2) {
                    case CADENA ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO);
                       return comp1.toString().equalsIgnoreCase(comp2.toString());
            }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
            }
            }
            default ->{
             return   new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }
    public Object notequals(Object comp1, Object comp2){
     var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

         switch (comparando1) {
            case ENTERO ->{
                 switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 != (int) comp2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 != (double) comp2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 != (int)((Character) comp2);
                    }
                    default ->{
                       return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                }
            }
            }
            case DECIMAL ->{
                switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 != (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 != (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (double) comp1 != (double)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case CARACTER ->{
            switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int)((Character) comp1) != (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) ((Character)comp1) != (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (int)((Character) comp1) != (int)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case CADENA ->{
                switch (comparando2) {
                    case CADENA ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO);
                       return !comp1.toString().equalsIgnoreCase(comp2.toString());
            }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
            }
            }
            default ->{
             return   new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }
    public Object menorq(Object comp1, Object comp2) {
         var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

         switch (comparando1) {
            case ENTERO ->{
                 switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 < (int) comp2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 < (double) comp2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 < (int)((Character) comp2);
                    }
                    default ->{
                       return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                }
            }
            }
            case DECIMAL ->{
                switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 < (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 < (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (double) comp1 < (double)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case CARACTER ->{
            switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int)((Character) comp1) < (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) ((Character)comp1) < (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (int)((Character) comp1) < (int)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            default ->{
             return   new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }
    public Object menorIgualq(Object comp1, Object comp2){
     var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

         switch (comparando1) {
            case ENTERO ->{
                 switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 <= (int) comp2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 <= (double) comp2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 <= (int)((Character) comp2);
                    }
                    default ->{
                       return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                }
            }
            }
            case DECIMAL ->{
                switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 <= (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 <= (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (double) comp1 <= (double)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case CARACTER ->{
            switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int)((Character) comp1) <= (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) ((Character)comp1) <= (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (int)((Character) comp1) <= (int)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            default ->{
             return   new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }
    public Object mayorq(Object  comp1, Object comp2){
     var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

         switch (comparando1) {
            case ENTERO ->{
                 switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 > (int) comp2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 > (double) comp2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 > (int)((Character) comp2);
                    }
                    default ->{
                       return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                }
            }
            }
            case DECIMAL ->{
                switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 > (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 > (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (double) comp1 > (double)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case CARACTER ->{
            switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int)((Character) comp1) > (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) ((Character)comp1) > (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (int)((Character) comp1) > (int)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            default ->{
             return   new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }
    public Object mayorIgualq(Object comp1, Object comp2){
         var comparando1 = this.cond1.tipo.getTipo();
        var comparando2 = this.cond2.tipo.getTipo();

         switch (comparando1) {
            case ENTERO ->{
                 switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 >= (int) comp2;
                    }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 >= (double) comp2;
                    }
                    case CARACTER ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                       return (int) comp1 >= (int)((Character) comp2);
                    }
                    default ->{
                       return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
                }
            }
            }
            case DECIMAL ->{
                switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 >= (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) comp1 >= (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (double) comp1 >= (double)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            case CARACTER ->{
            switch (comparando2) {
                    case ENTERO ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int)((Character) comp1) >= (int) comp2;
            }
                    case DECIMAL ->{
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) ((Character)comp1) >= (double) comp2;
            }
                    case CARACTER ->{
                       this.tipo.setTipo(tipoDato.BOOLEANO); 
                       return (int)((Character) comp1) >= (int)((Character)comp2); 
                    }
                    default ->{
                        return new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
                }
            }
            default ->{
             return   new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
            }
        }
    }
}
